<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ page language="java" contentType="text/html" import="java.util.*"%>
<html>
<head>
<link rel="stylesheet" href="./includes/testverticalnavigation.css" type="text/css" media="screen" />
<title>Insert Image URLs Here</title>
	 
	 
</head>

<body>
<div class="dialogpage">
    <s:form action="/images.action" method="POST">
      <s:hidden name="id" />
    
      <s:hidden name="formsubmit" value="true" />
      <b>Front Image:</b><s:textfield name="front" size="80"/><br>
      <b>Back Image:</b><s:textfield name="back" size="80"/><br>
     
      <b>Top Image:</b><s:textfield name="imagetop" size="80"/><br>
      <b>Side A Image:</b><s:textfield name="sidea" size="80"/><br>
      <b>Side B Image:</b><s:textfield name="sideb" size="80"/><br>
      
      <b>Bottom Image:</b><s:textfield name="bottom" size="80"/><br>
     
         <s:submit value="Submit" align="center"/>
    </s:form>
</div>
</body>
</html>