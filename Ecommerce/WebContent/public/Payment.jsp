<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>



<%@ page language="java" contentType="text/html" import="java.util.*"%>

<html>
<head>
<sj:head jqueryui="true" jquerytheme="redmond" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment Information</title>

	
	<link rel="stylesheet" href="./includes/testverticalnavigation.css" type="text/css" media="screen" />
	<script type='text/javascript' src='./includes/verticalnavigation.js'></script>
	
</head>
<body>
	<div class="center">
	 <div class="leftwhitepanel">
	<b>Payment Information</b><br />
	<br /><br />
	<s:if test="cardIssues != null">
	<br /><br /><br /><br /><br />
	The following errors were found with your payment information:<br />
		<s:iterator value="cardIssues" var="issue">
			<b><s:property value="issue"/></b><br />
		</s:iterator>
	</s:if>
	
	
	<s:form action ="./payment.action">
	<s:fielderror>
    <s:param>cardnumber</s:param>
	</s:fielderror>
	<b>Card Number:</b><s:textfield name="cardnumber" label="Credit Card Number"/>
	<br />
	 <b>Card Type:</b><s:select label="cardtype" 
		headerKey="0" headerValue="Visa"
		list="#{'1':'Mastercard', '2':'American Express'}" 
		name="cardtype" 
		value="0" />
	<br />
	<b>Expiration Month:</b><s:select label="Expiration Month" 
		headerKey="01" headerValue="January"
		list="#{'02':'February', '03':'March','02':'February', '03':'March','04':'April', '05':'May','06':'June', '07':'July','08':'August', '09':'September','10':'October', '11':'November', '12':'December'}" 
		name="expmonth" 
		value="0" />
	<br />
	 <b>Expiration Year:</b><s:select label="Expiration Year" 
		headerKey="2010" headerValue="2010"
		list="#{'2011':'2011', '2012':'2012','2013':'2013','2014':'2014','2015':'2015','2016':'2016','2017':'2017','2018':'2018','2019':'2019','2020':'2020'}" 
		name="expyear" 
		value="0" />
	<br />
	<s:fielderror>
    <s:param>cvv</s:param>
	</s:fielderror>
	<b>CVV Number:</b><s:textfield name="cvv" label="Credit Card CVV Value"/>
	<br />
	<s:fielderror>
    <s:param>firstname</s:param>
	</s:fielderror>
	<b>First Name:</b><s:textfield name="firstname" label="First Name"/>
	<br />
	<s:fielderror>
    <s:param>lastname</s:param>
	</s:fielderror>
	<b>Last Name:</b><s:textfield name="lastname" label="Last Name"/>
	<br />
	<s:fielderror>
    <s:param>emailaddress</s:param>
	</s:fielderror>
	<b>Email Address:</b><s:textfield name="emailaddress" label="Email Address" />
	<br />
	<s:fielderror>
    <s:param>address</s:param>
	</s:fielderror>
	<b>Address:</b><s:textfield name="address" label="Address" />
	<br />
	<s:fielderror>
    <s:param>city</s:param>
	</s:fielderror>
	<b>City:</b><s:textfield name="city" label="City"/>
	<br />
	 <b>State:</b><s:select label="state" 
		list="stateList" 
		 listKey="symbol"
       listValue="state"
		
		name="state" 
		value="0" />
	
<br />
<s:fielderror>
    <s:param>zipcode</s:param>
	</s:fielderror>
	<b>ZipCode:</b><s:textfield name="zipcode" label="Zipcode"/>
	<br />
	 <b>Country:</b><s:select label="Country" 
		list="#{'US':'U.S.A.'}" 
		name="country" 
		value="0" />
	<br />
	<s:fielderror>
    <s:param>phonenumber</s:param>
	</s:fielderror>
	<b>Phone Number:</b><s:textfield name="phonenumber" label="Phone Number"/>
	<br /><br />
	<s:submit name="finalize" value="Finalize Order" />
	</s:form>
	
	</div>
	</div>
		<br /><br /><br />
</body>
</html>