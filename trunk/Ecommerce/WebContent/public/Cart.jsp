<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>



<%@ page language="java" contentType="text/html" import="java.util.*"%>

<html>
<head>
<sj:head jqueryui="true" jquerytheme="redmond" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cart Page</title>

	
</head>
<body>
<br />
<br />
<br />
<br />
<br />
<div class="leftsuckerpanel">
	<s:property escape="false" value="catbuilt"/>
	 
	
</div>
	<br /><br />
<div class="center">
	<br />
	<br />
	<br />
	<br />
	<br />
	
	<b>Items In Your Cart</b><br />
	<br />
	<br />
	<br />
	<s:if test="cartItems != null">

		<s:iterator value="cartItems" var="publicproduct">
			<s:form action="cart">
			<s:if test="#publicproduct.front != null">
				<img src="<s:property value="%{#publicproduct.front}"/>" width="75" height="75" border="0"/>
			</s:if>
			<br />
			<s:url action="product.action" var="urlTag" >
	    		<s:param name="id"><s:property value="%{#publicproduct.id}"></s:property></s:param>
	    		<s:param name="name"><s:property value="%{#publicproduct.name}"></s:property></s:param>
			</s:url>
			<s:a href="%{urlTag}"><s:property value="%{#publicproduct.name}"/></s:a>
			<br />
			
			
				<s:hidden name="id" value="%{#publicproduct.id}"/>
				Price:<s:property value="%{#publicproduct.formattedprice}"/><br />
				Quantity:<input type="text" name="quantity"  value="<s:property value='cartMap[#publicproduct.id]'/>"/>
			    <input type="submit" name="updateProduct" value="Update" />
			    <input type="submit" name="deleteProduct" value="Delete" />
    		</s:form>
    		
    		
			
		</s:iterator>
		
	</s:if>
	<br />
	<br />
	
	Total: <s:property value="totalString" />
	<br />
	<br />
	<s:if test="cartMap != null">
	<a href="./shippinginput.action" >Check Out</a>
	</s:if>

	<br />
	<br />
	</div>
   	
	 
	
		
		
		<br /><br /><br />
</body>
</html>