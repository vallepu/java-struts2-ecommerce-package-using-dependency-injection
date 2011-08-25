<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>



<%@ page language="java" contentType="text/html" import="java.util.*"%>

<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
   
  <script type='text/javascript' src='jquery-1.4.3.js'></script>
  <script type='text/javascript' src='jquery.gallery.js'></script>  
  
	

  

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Page</title>

	 <script type='text/javascript' src='jquery-1.4.3.js'></script>
  <script type='text/javascript' src='jquery.gallery.js'></script>    


  <script type="text/javascript">
     $(document).ready(function(){ 
       $("#gallery-wrapper").gallery({
       background: '#ffc',
       foreground: '#000'                          
       });
     });
     
   </script>
	
	
	
	
		

</head>
<body>
 


<br />
<br />
<br />
<br />
<br />
<br />

	
	
<div class="leftsuckerpanel">
	<s:property escape="false" value="catbuilt"/>
	 
	
</div>
	
	
	
	
	
	
	<div class="newsbar">
	<b>Items In Your Cart</b></br>
	<s:if test="cartItems != null">
		<s:iterator value="cartItems" var="publicproduct">
			
			<img src="<s:property value="%{#publicproduct.front}"/>" width="25" height="25" border="0"/>
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
	
	

	 
	 
	
	 
	 
	  <div id="gallery-wrapper">
  <h1 class="gallery-title">Product Image</h1>
  <div id="gallery-image" style="height:300px; width: 400px;"> <img src="<s:property value='publicProduct.front'/>" width="400" height="300" title="Image Front" alt="Image Front" /> </div>
  <div class="gallery-more">
    <ul>
      <li><a href="<s:property value="publicProduct.front"/>" title="Product Front" class="t1 active"><img src="<s:property value="publicProduct.front"/>" width="60" height="50" alt="Product Front" /><span>Product Front</span></a></li>
   	  <li><a href="<s:property value="publicProduct.back"/>" title="Product Back" class="t1 active"><img src="<s:property value="publicProduct.back"/>" width="60" height="50" alt="Product Back" /><span>Product Back</span></a></li>
      <li><a href="<s:property value="publicProduct.sidea"/>" title="Product Side A" class="t1 active"><img src="<s:property value="publicProduct.sidea"/>" width="60" height="50" alt="Product Side A" /><span>Product Side A</span></a></li>
      <li><a href="<s:property value="publicProduct.sideb"/>" title="Image Side B" class="t1 active"><img src="<s:property value="publicProduct.sideb"/>" width="60" height="50" alt="Image Side B" /><span>Product Side B</span></a></li>
      <li><a href="<s:property value="publicProduct.top"/>" title="Image Top" class="t1 active"><img src="<s:property value="publicProduct.top"/>" width="60" height="50" alt="Image Top" /><span>Image Top</span></a></li>
      <li><a href="<s:property value="publicProduct.bottom"/>" title="Image Bottom" class="t1 active"><img src="<s:property value="publicProduct.bottom"/>" width="60" height="50" alt="Image Bottom" /><span>Image Bottom</span></a></li>
   
    </ul>
  </div>
</div>
  
	 

	 
	 

	 
		
		<b>Description:</b><s:property value="publicProduct.description"></s:property><br />
		
		
	<s:form action="product">
	<s:hidden name="id"/>
		
     <s:select label="Quantity" 
		headerKey="1" headerValue="1"
		list="#{'2':'2', '3':'3', '4':'4', '5':'5', '6':'6', '7':'7', '8':'8', '9':'9', '10':'10'}" 
		name="quantity" 
		value="1" />
     
     
      <s:submit name="addProduct" value="Add To Cart" />
     
    </s:form>
	
		
</body>
</html>