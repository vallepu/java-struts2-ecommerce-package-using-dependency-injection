<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>



<%@ page language="java" contentType="text/html" import="java.util.*"%>

<html>
<head>
<sj:head jqueryui="true" jquerytheme="redmond" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shipping Location</title>

	
	<link rel="stylesheet" href="./includes/testverticalnavigation.css" type="text/css" media="screen" />
	<script type='text/javascript' src='./includes/verticalnavigation.js'></script>
	
	
	
</head>
<body>
	
	<s:fielderror>
    <s:param>zipcode</s:param>
	</s:fielderror>
	<s:fielderror>
    <s:param>phonenumber</s:param>
	</s:fielderror>
	
	<s:fielderror>
    <s:param>fullname</s:param>
	</s:fielderror>
	<s:fielderror>
    <s:param>address</s:param>
	</s:fielderror>
	<s:fielderror>
    <s:param>city</s:param>
	</s:fielderror>
	
	
	
	
	<b>Shipping Location</b><br />
	
	<s:property value="formerror"/>
		<s:if test="errorList != null">
	<br /><br /><br /><br /><br />
	The following errors were found with your shipping information:<br />
		<s:iterator value="errorList" var="error">
			<b><s:property value="%{#error.errorDescription}"/></b><br />
		</s:iterator>
	</s:if>
	
	<br />
	<br />
	 <div class="center">
	 <div class="leftwhitepanel">
	<s:form action ="./shipping.action">
	<b>Full Name:</b><s:textfield name="fullname" label="Fullname"/>
	<br />
	<br />
	<b>Address:</b><s:textfield name="address" label="Address" />
	<br />
	<br />
	<b>City:</b><s:textfield name="city" label="City"/>
	<br />
	<br />
	<b>State:</b><s:select label="state" 
		list="stateList" 
		listKey="symbol"
        listValue="state"
		name="state" 
		 />
	<br />
	<br />
	<b>ZipCode:</b><s:textfield name="zipcode" label="Zipcode"/>
	<br />
	<br />
	 <b>Country:</b><s:select label="Country" 
		list="#{'US':'U.S.A.'}" 
		name="country" 
		value="0" />
	<br />
	<br />
	<b>Phone Number:</b><s:textfield name="phonenumber" label="Phone Number"/>
	<br />
	<br />
	<s:submit name="carrier" value="Next Step" />
	</s:form>
	</div>
	</div>
	
	
	


	
	
   	
	 
	
		
		
		<br /><br /><br />
</body>
</html>