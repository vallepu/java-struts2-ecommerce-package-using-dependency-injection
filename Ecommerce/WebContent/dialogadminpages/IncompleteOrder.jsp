<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ page language="java" contentType="text/html" import="java.util.*"%>
<html>
<head>
<link rel="stylesheet" href="./includes/testverticalnavigation.css" type="text/css" media="screen" />
<script type="text/javascript">

function openProductDialog(productid) 
{
	$("#product_details").load("<%= request.getContextPath() %>/productinformation.action?id=" + productid);
	$("#product_details").dialog('open');
}

 var formSubmitted=false;  
 function preventDoubleSubmit() 
 {  
    if(formSubmitted) 
    {  
       return false;  
    }
    var submitHistoryButton = document.getElementById("history");  
    submitHistoryButton.disabled=true; 
    
    var submitButton = document.getElementById("capture");  
    submitButton.disabled=true;  
       
    formSubmitted = true;  
    return true;  
 }  
    
</script>
<title>Insert Image URLs Here</title>
	 
	 
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
	<b>Email:</b> <s:property value="orderPayment.email"/><br />
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
	<b>Shipping Cost: </b>$<s:property value="orderCarrier.totalshippingcost"/>
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
	<b>Receipt Id:</b><s:property value="gettransactiondetailsResponse.receiptId"/><br />
	<br />
	<br />
	<br />
	<br />
	 <s:form action="/incompleteorders.action" method="POST" onsubmit="preventDoubleSubmit()">
      <s:hidden name="orderpaymentId" />
      <s:hidden name="orderId" />
        <s:hidden name="submithistory" value="Submit History Note" />
      <s:hidden name="orderpaymentId" />
       <s:hidden name="transactionId" />
        <s:hidden name="total" />
      <s:hidden name="formsubmit" value="true" />
      <b>History Note:</b><s:textfield name="historynote" label="History Note"/><br>
      
		 <s:submit id="history" key="submithistory" value="Submit History Note" align="center"/>
		 <br />
		 <br />
		 
	</s:form> 
	 <s:form action="/incompleteorders.action" method="POST" onsubmit="preventDoubleSubmit()">
	  <b>Finalizing Order Information</b>
     <br />
    <b>Shipping Cost:<br />
     Whole Number<b/> 
    <s:select label="Shipping Cost: Whole Number" 
		list="shippingwholenumberList" 
		listKey="wholeNumber"
        listValue="wholeNumber"
		name="shippingwholeNumber" 
		 />
	<b>Change</b>
	<s:select label="Shipping Cost: Change"
		list="shippingchangeList" 
		listKey="change"
        listValue="change"
		name="shippingChange" 
		 />
	<br />
	<br />
	<br />
	
	  <s:hidden name="orderpaymentId" />
      <s:hidden name="orderId" />
       
      <s:hidden name="orderpaymentId" />
       <s:hidden name="transactionId" />
        <s:hidden name="total" />
      <s:hidden name="formsubmit" value="true" />
     <s:hidden name="submitcapture" value="Capture Funds" />
     <s:hidden name="serviceName"/>
      <s:hidden name="carrierName"/>
      <s:hidden name="email"/>
  		  <b>Tracking Number:</b><s:textfield name="trackingnumber" label="Tracking Number"/><br>
  		<br /><br />
  		<b>Confirm Correct Shipping Cost:</b> <s:checkbox label="Confirm Correct Shipping Cost" name="confirmcapture" value="aBoolean" fieldValue="true"/>
        <br /><br />
         <s:submit id="capture" name="submitcapture" key="submitcapture" value="Capture Funds" align="center"/>

   </s:form>
   </div>
</body>
</html>