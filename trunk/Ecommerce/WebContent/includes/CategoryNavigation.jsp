<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>



<%@ page language="java" contentType="text/html" import="java.util.*"%>

<html>
<head>
<sj:head jqueryui="true" jquerytheme="redmond" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Category Navigational Page</title>

	<link rel="stylesheet" href="./includes/project.css" type="text/css" media="screen" />
	 <link rel="stylesheet" href="./includes/verticalnavigationn.css" type="text/css" media="screen" />
		<script type='text/javascript' src='./includes/verticalnavigation.js'></script>
		

</head>
<body>test
	
   
	 <s:property escape="false" value="catbuilt"/>
	 
	<s:if test="publicproductList != null">
		<s:iterator value="publicproductList" var="publicproduct">
		
			<s:property value="%{#publicproduct.name}"/>
			 
			 <s:url action="product.action" var="urlTag" >
    		<s:param name="id"><s:property value="%{#publicproduct.id}"></s:property></s:param>
    		<s:param name="name"><s:property value="%{#publicproduct.name}"></s:property></s:param>
		</s:url>
		<s:a href="%{urlTag}"><s:property value="%{#publicproduct.name}"/></s:a>
			 
			 
		</s:iterator>
	</s:if>
			<s:url value="editGadget.action">
    			<s:param name="id" value="%{selected}" />
			</s:url>
		<s:text name="your a nerd"></s:text>

	 <s:property escape="false" value="pagination"/>	
</body>
</html>