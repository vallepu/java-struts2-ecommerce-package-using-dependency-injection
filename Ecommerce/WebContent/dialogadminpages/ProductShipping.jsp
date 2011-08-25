<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ page language="java" contentType="text/html" import="java.util.*"%>
<html>
<head>

<title>Insert Product Shipping Specifications Here</title>
	 
	<link rel="stylesheet" href="./includes/testverticalnavigation.css" type="text/css" media="screen" /> 
</head>

<body>
<div class="dialogpage">
    <s:form action="/productshipping.action" method="POST">
      <s:hidden name="id" />
    
      <s:hidden name="formsubmit" value="true" />
      <b>Weight(Lbs):</b><s:textfield name="productWeight" label="Weight"/><br>
      <b>Length(Inches):</b><s:textfield name="length" label="Length"/><br>
     
      <b>Width(Inches):</b><s:textfield name="width" label="Width"/><br>
      <b>Height(Inches):</b><s:textfield name="height" label="Height"/><br>
    
         <s:submit value="Submit" align="center"/>
    </s:form>
</div>
</body>
</html>