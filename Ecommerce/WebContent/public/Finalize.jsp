<%@ taglib prefix="s" uri="/struts-tags" %>




<%@ page language="java" contentType="text/html" import="java.util.*"%>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shipping Location</title>

	
	<link rel="stylesheet" href="./includes/testverticalnavigation.css" type="text/css" media="screen" />
	<script type='text/javascript' src='./includes/verticalnavigation.js'></script>
	
<script type="text/javascript">

var formSubmitted=false;  
function preventDoubleSubmit() 
{  
   if(formSubmitted) 
   {  
      return false;  
   }
   var submitHistoryButton = document.getElementById("submitfinalizationid");  
   submitHistoryButton.disabled=true; 
   
  
   formSubmitted = true;  
   return true;  
}  

</script>

</head>
<body>
	
	 <div class="leftwhitepanel">
		
	<s:if test="finalize != null">
		<b>Finalize Order</b><br /><br />
		<s:if test="cartItems != null">
			<b>Items You Are Purchasing:</b><br /><br />
			<s:iterator value="cartItems" var="publicproduct">
					
					<b>Product Name:</b><s:property value="%{#publicproduct.name}"/><br />
					<b>Quantity:</b><s:property value='cartMap[#publicproduct.id]'/><br />
					<b>Price:</b><s:property value="%{#publicproduct.formattedprice}"/><br />
					<br />
					<br />
					
			</s:iterator>
					<b>Total For Your Items:</b><s:property value="itemtotalString"/>
					<s:form action="cart.action">
					
					<s:submit id="submit" value="Go To Cart"/>
					
					</s:form>
		</s:if>		
		<br />
		<br />
	</s:if>
		
		
		<s:if test="finalize != null">
			<b>Shipping Information</b><br /><br />
			<b>Ship To:</b> <s:property value='shipping.get("fullname")'/><br />
			<b>Address:</b> <s:property value='shipping.get("address")'/><br />
			<b>City:</b> <s:property value='shipping.get("city")'/><br />
			<b>State:</b> <s:property value='shipping.get("state")'/><br />
			<b>ZipCode:</b> <s:property value='shipping.get("zipcode")'/><br />
			<b>Phone Number:</b> <s:property value='shipping.get("phonenumber")'/><br />
			
			<b>Shipping Carrier:</b> <s:property value="shippingcarrier"/><br />
			<b>Shipping Service:</b> <s:property value="shippingservice"/><br />
			<b>Shipping Total: </b>$<s:property value="shippingtotal"/><br />
		
			<s:form action="shippinginput.action">
					
					<s:submit value="Go To Shipping"/>
					
			</s:form>
		
		<br />
		<b>Your Finalized Total:</b><s:property value="totalString"/>
		<br />
		<br />
		<b>Payment Information</b><br />
		<b>First Name:</b> <s:property value="firstname"/><br />
		<b>Last Name:</b> <s:property value="lastname"/><br />
		<b>Card Number:</b> <s:property value="cardnumber"/><br />
		<b>Card Type:</b> <s:property value="cardName"/><br />
		<b>Expiration Month:</b> <s:property value="expmonth"/><br />
		<b>Expiration Year:</b> <s:property value="expyear"/><br />
		<b>CVV Value:</b> <s:property value="cvv"/><br />
		<b>Address:</b> <s:property value="address"/><br />
		<b>City:</b> <s:property value="city"/><br />
		<b>State:</b> <s:property value="state"/><br />
		<b>ZipCode:</b> <s:property value="zipcode"/><br />
		<b>Email:</b> <s:property value="emailaddress"/><br />
	
		<b>Country:</b> <s:property value="country"/><br />
		<b>Phone Number:</b> <s:property value="phonenumber"/><br />
		
		<s:form action="paymentinput.action">
					
					<s:submit value="Go To Payment"/>
					
			</s:form>
		
		<s:form action ="./finalize.action" method="POST" onsubmit="preventDoubleSubmit()">
		<s:hidden name="submitfinalization" value="submit"/>
		
		<br /><br />
		
		<s:submit id="submitfinalizationid" name="submitfinalization" value="Submit Order" />
		</s:form>
		</s:if>
		<s:if test="responseCode != null">
			
			<s:if test="responseCode == 'Failure'">
				
				<s:iterator value="publicList" var="error">
					<s:property value="%{#error.longMessage}"/>
				  
				</s:iterator>
				<s:form action="paymentinput.action">
					
					<s:submit value="Fix Payment Information"/>
					
			</s:form>
			
			</s:if>
			<s:if test="responseCode == 'Success'">
			
				Thank you for your order. You will recieve an email shortly with your order information.
			
			</s:if>
			
		
		</s:if>
		<s:else>
			<s:if test="publicList != null">
				
				<s:iterator value="publicList" var="error">
					<s:property value="%{#error.longMessage}"/>
				  
				</s:iterator>
			
			</s:if>
			
		
		</s:else>
	
	
	
	


	
	
   	</div>
	 
	
		
		
		<br /><br /><br />
</body>
</html>