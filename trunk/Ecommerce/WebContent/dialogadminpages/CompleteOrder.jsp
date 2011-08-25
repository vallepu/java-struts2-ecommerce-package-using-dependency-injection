<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ page language="java" contentType="text/html" import="java.util.*"%>
<html>
<head>
<link rel="stylesheet" href="./includes/testverticalnavigation.css" type="text/css" media="screen" />
<script type="text/javascript">

		function cancelButton(event)
		{
			alert("test");
		}

		function openProductDialog(productid) 
		{
			$("#product_details").load("<%= request.getContextPath() %>/productinformation.action?id=" + productid);
			$("#product_details").dialog('open');
			
        }
		
</script>
<title>Completed Order Information</title>
	 
	 
</head>

<body>
	
	<sj:dialog 
		id="product_details" 
	
		title="Product Details" 
	
		autoOpen="false" 
		modal="true"
		width="400"	
	>
	
	</sj:dialog>
	
	
<div class="dialogpage">
	<b>Order Id: </b><s:property value="orderPayment.orderid"/>
	<br />
	<br />
	<br />
	
	<b>Order Items</b><br />
	<s:iterator value="orderitemsList" var="orderitem">
	
		<b>Order Item:</b><a href='#' onClick='javascript:openProductDialog(<s:property value="%{#orderitem.productid}"/>)'><s:property value="%{#orderitem.productname}"/></a><br />
		<b>Price/Item: </b>$<s:property value="%{#orderitem.price}"/><br />
		<b>Quantity:</b> <s:property value="%{#orderitem.quantity}"/><br />
	
	</s:iterator>
	<br />
	<br />
	

	<b>Order History</b><br />
	<s:iterator value="orderhistoryList" var="orderhistory">
		<b>Order Status:</b> <s:property value="%{#orderhistory.status}"/><br />
		<b>Time Of History Record:</b> <s:property value="%{#orderhistory.datetime}"/><br />
		<b>History Note:</b> <s:property value="%{#orderhistory.note}"/><br />
	<br />
	</s:iterator>
	<br />
	<b>Order Payment Information</b><br />
	<b>Time Of Payment:</b><s:property value="orderPayment.orderdate"/><br />
	<b>PayPal Transaction Id:</b><s:property value="orderPayment.transactionid"/><br />
	<b>Payment Total:</b> $<s:property value="orderPayment.total"/><br />
	<b>First Name:</b><s:property value="orderPayment.firstname"/><br />
	<b>Last Name:</b><s:property value="orderPayment.lastname"/><br />
	<b>Last 4 Digits on Card:</b><s:property value="orderPayment.cardnumber"/><br />
	<b>Card Type:</b><s:property value="orderPayment.cardtype"/><br />
	<b>Expiration Date: </b><s:date name="orderPayment.expdate" format="MMM/yyyy" /><br />
	<b>Address:</b><s:property value="orderPayment.address"/><br />
	<b>City:</b><s:property value="orderPayment.city"/><br />
	<b>State:</b><s:property value="orderPayment.state"/><br />
	<b>ZipCode:</b> <s:property value="orderPayment.zipcode"/><br />
	<br />
	<br />
	<b>Order Shipping</b>
	<br /><br />
	<b>Full Name: </b><s:property value="orderShipping.fullname"/><br />
	<b>Address: </b><s:property value="orderShipping.address"/><br />
	<b>City: </b><s:property value="orderShipping.city"/><br />
	<b>State: </b><s:property value="orderShipping.state"/><br />
	<b>ZipCode: </b><s:property value="orderShipping.zipcode"/><br />
	<b>Country: </b><s:property value="orderShipping.country"/><br />
	<b>Phone Number: </b><s:property value="orderShipping.phonenumber"/><br />
	<br /><br />
	<b>Shipping Method</b>
	<br /><br />
	<b>Shipping Carrier: </b> <s:property value="shippingCarrier.carrier"/><br />
	<b>Shipping Service: </b><s:property value="shippingService.servicename"/><br />
	<b>Estimated Shipping Cost: </b>$<s:property value="orderCarrier.totalshippingcost"/><br />
	<b>Actual Shipping Cost: </b>$<s:property value="orderfinalizedShipping.finalshippingcost"/><br />
	<br />
	<br />
	<b>Current Payment Merchant Exchange Status</b>
	<br />
	<br />
	<b>Payment Status:</b><s:property value="gettransactiondetailsResponse.paymentStatus"/><br />
	<b>Pending Reason:</b><s:property value="gettransactiondetailsResponse.pendingReason"/><br />
	<b>Protection Eligibility:</b><s:property value="gettransactiondetailsResponse.protectionEligibility"/><br />
	<b>Currency Code:</b><s:property value="gettransactiondetailsResponse.currencyCode"/><br />
	<b>Fee Amount:</b><s:property value="gettransactiondetailsResponse.feeAmt"/><br />
	<b>Settlement Amount:</b><s:property value="gettransactiondetailsResponse.settlementAmt"/><br />
	<b>Receipt Id:</b><s:property value="gettransactiondetailsResponse.receiptId"/><br />
	<br />
	<br />
	<br />
	<br />
		<s:if test="paymentStatus != 'Refunded'">
	
		<s:form action="/completeorders.action" method="POST">
		      <s:hidden name="orderpaymentId" />
		      <s:hidden name="orderId" />
		      <s:hidden name="orderpaymentId" />
		      <s:hidden name="transactionId" />
		      <s:hidden name="total" />
		      <s:hidden name="formsubmit" value="true" />
		      <b>History Note:</b><s:textfield name="historynote" label="History Note"/><br>
		      
			  <s:submit id="history" key="submithistory" value="Submit History Note" align="center" onclick="alert('Hello from JavaScript!')"/>
				 <br />
				 <br />
			  <b>Refund Order</b>
		      <b>Refund Note:</b><s:textfield name="refundnote" label="Refund Note"/><br>
		     <b>Confirm Refund By Checking Box:</b><s:checkbox label="Confirm Refund By Checking Box" name="confirmrefund" value="aBoolean" fieldValue="true"/>
		     
		      <s:submit id="submitrefund" key="submitrefund" value="Refund Order" align="center"/>
	    </s:form>
    </s:if>
   </div>
</body>
</html>