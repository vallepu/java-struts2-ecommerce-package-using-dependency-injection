<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>



<%@ page language="java" contentType="text/html" import="java.util.*"%>

<html>
<head>


<sj:head jqueryui="true" jquerytheme="redmond" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Category Navigation</title>
</head>
<body>
 


<br />
<br />
<br />
<br />
<br />
<br />

	
	
<div id="leftsuckerpanel">
	<s:property escape="false" value="catbuilt"/>
	 
	
</div>
	
		
	
   
	 <div class="center">
	   
	<s:if test="publicproductList != null">
	
		<s:iterator value="publicproductList" var="publicproduct">
	
		
	<s:if test="#publicproduct.front != null">
		<img src="<s:property value="%{#publicproduct.front}"/>" width="250" height="250" border="0"/>
	</s:if>
		
		<br />
		<b>
		<s:url action="product.action" var="urlTag" >
    		<s:param name="id"><s:property value="%{#publicproduct.id}"></s:property></s:param>
    		<s:param name="name"><s:property escape="false" value="%{#publicproduct.name}"></s:property></s:param>
		</s:url>
		</b>
		<b><s:a href="%{urlTag}"><s:property escape="false" value="%{#publicproduct.name}"/></s:a></b>
		<br />
		<b>Price:</b><s:property value="%{#publicproduct.formattedprice}"></s:property>
		<br />
		<b>Description:</b><s:property escape="false" value="%{#publicproduct.summary}"></s:property>
		<br /><br />
			 
		
		<br />
		</s:iterator>
		
	</s:if>
	<s:else>
	
	<b>Please Choose A Category</b>
	</s:else>
	<s:if test="listCount == 0">
	
		<b>No Items In Category</b>
	
	</s:if>
	</div>
	
	
	
	<div id="newsbar">
	<b>Items In Your Cart</b></br>
	<s:if test="cartItems != null">
		<s:iterator value="cartItems" var="publicproduct">
		<s:if test="#publicproduct.front != null">
			<img src="<s:property value="%{#publicproduct.front}"/>" width="25" height="25" border="0"/>
		</s:if>
			<br />
			<s:url action="product.action" var="urlTag" >
	    		<s:param name="id"><s:property value="%{#publicproduct.id}"></s:property></s:param>
	    		<s:param name="name"><s:property value="%{#publicproduct.name}"></s:property></s:param>
			</s:url>
			<s:a href="%{urlTag}"><s:property value="%{#publicproduct.name}"/></s:a>
			<br/>Quantity:<s:property value='cartMap[#publicproduct.id]'/><br/>
			<br />
			
			
		</s:iterator>
		<a href="./cart.action"/>Go To Cart</a>
	</s:if>


</div>
		<s:if test="pagination != '' && pagination != null">	
		<br /><br/><br/><br/>
		<div class="footer">
		<s:property escape="false" value="pagination"/>	
		</div>
		</s:if>
	
		<br /><br /><br />
</body>
</html>