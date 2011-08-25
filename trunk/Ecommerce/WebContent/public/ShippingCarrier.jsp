<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>



<%@ page language="java" contentType="text/html" import="java.util.*"%>

<html>
<head>
<sj:head jqueryui="true" jquerytheme="redmond" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shipping Carrier</title>

	
	<link rel="stylesheet" href="./includes/testverticalnavigation.css" type="text/css" media="screen" />
	<script type='text/javascript' src='./includes/verticalnavigation.js'></script>
	

</head>
<body>
	<div class="center">
	 <div class="leftwhitepanel">
	<b>Shipping Option</b><br />
	
	<br />
	<br />

	<s:iterator value="totalservicesList" var="service">
		<s:form action ="./paymentinput.action">
			<b><s:property value='carrierMap[#service.carrierid]'/>: </b><s:property value="%{#service.servicename}"/>
			<br />
			<b>Cost:</b>$<s:property value='servicescostMap[#service.id]'/><br />
			<input type="hidden" name="serviceId" value="<s:property value="%{#service.id}"/>"> 
			<input type="hidden" name="totalshippingCost" value="<s:property value="%{servicescostMap[#service.id]}"/>">
			<s:submit name="carrier" value="Choose This Option" /><br /><br />
		</s:form>	
	</s:iterator>
	</div>
	</div>
		
	


	
	
	


	
	
   	
	 
	
		
		
		<br /><br /><br />
</body>
</html>