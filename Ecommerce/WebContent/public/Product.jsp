<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>




<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

 

 <script type="text/javascript">
if (document.images) {
	a_1 = new Image;
	a_1.src = "<s:property value='publicProduct.front'/>";
	a_2 = new Image;
	a_2.src = "<s:property value='publicProduct.back'/>";
	a_3 = new Image;
	a_3.src = "<s:property value='publicProduct.sidea'/>";
	a_4 = new Image;
	a_4.src = "<s:property value='publicProduct.sideb'/>";
	a_5 = new Image;
	a_5.src = "<s:property value='publicProduct.top'/>";
	a_6 = new Image;
	a_6.src = "<s:property value='publicProduct.bottom'/>";

	b_1 = new Image;
	b_1.src = "./includes/Front.jpg";
	b_2 = new Image;
	b_2.src = "./includes/Back.jpg";
	b_3 = new Image;
	b_3.src = "./includes/SideA.jpg";
	b_4 = new Image;
	b_4.src = "./includes/SideB.jpg";
	b_5 = new Image;
	b_5.src = "./includes/Top.jpg";
	b_6 = new Image;
	b_6.src = "./includes/Bottom.jpg";
}

function showImg(imgname, rollname)
{
	if (document.images) {
		window.onerror = null;
		document.getElementById(rollname).src=eval(imgname + ".src");
	}
}

</script>

  

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Page</title>

	
	
	 
	
	
		

</head>
<body>
 


<br />
<br />
<br />
<br />


	
	
<div id="leftsuckerpanel">
	<s:property escape="false" value="catbuilt"/>
	 
	
</div>
	
	

	
	

	 
	 
	
	 
	 
	 
  <br />
   <br />
    <br />
     <br />
      <br />
	 
	 <div class="center2">
	 
	 <div align="center">
	<table border="0" cellpadding="0" cellspacing="0" width="85%">
		<tr>
			<td valign="top" width="74">
				<table border="0" cellpadding="3" cellspacing="0" width="100%" id="rolllist">
				
					<s:if test="publicProduct.front != '' && publicProduct.front != null">
					<tr>
						<td><a href="#" onmouseover="showImg('a_1','roll_a');showImg('b_1','roll_b')"><br /><img src="<s:property value='publicProduct.front'/>" width="50" height="50" border="0"></a></td>
					</tr>
					</s:if>
					<s:if test="publicProduct.back != ''  && publicProduct.back != null">
					<tr>
						<td><a href="#" onmouseover="showImg('a_2','roll_a');showImg('b_2','roll_b')"><img src="<s:property value='publicProduct.back'/>" width="50" height="50" border="0"></a></td>
					</tr>
					</s:if>
					<s:if test="publicProduct.sidea != ''  && publicProduct.sidea != null">
					<tr>
						<td><a href="#" onmouseover="showImg('a_3','roll_a');showImg('b_3','roll_b')"><img src="<s:property value='publicProduct.sidea'/>" width="50" height="50" border="0"></a></td>
					</tr>
					</s:if>
					<s:if test="publicProduct.sideb != ''  && publicProduct.sideb != null">
					<tr>
						<td><a href="#" onmouseover="showImg('a_4','roll_a');showImg('b_4','roll_b')"><img src="<s:property value='publicProduct.sideb'/>" width="50" height="50" border="0"></a></td>
					</tr>
					</s:if>
					
					<s:if test="publicProduct.top != ''  && publicProduct.top != null">
					<tr>
						<td><a href="#" onmouseover="showImg('a_5','roll_a');showImg('b_5','roll_b')"><img src="<s:property value='publicProduct.top'/>" width="50" height="50" border="0"></a></td>
					</tr>
					</s:if>
					<s:if test="publicProduct.bottom != ''  && publicProduct.bottom != null">
					<tr>
						<td><a href="#" onmouseover="showImg('a_6','roll_a');showImg('b_6','roll_b')"><img src="<s:property value='publicProduct.bottom'/>" width="50" height="50" border="0"></a></td>
					</tr>
					</s:if>
				</table>
			</td>
			<s:if test="publicProduct.front != '' && publicProduct.front != null">
			<td align="center" width="426" valign="center" align="center">
				<img src="<s:property value='publicProduct.front'/>" width="250" height="250" id="roll_a">
			</td>
			<td valign="top" valign="center" align="left">
				<br><img src="./includes/Front.jpg" id="roll_b">
			</td>
			</s:if>
		</tr>
	</table>
</div>

	 	<b>Product Name:</b><s:property escape="false" value="publicProduct.name"></s:property><br />
		<b>Price:</b><s:property escape="false" value="publicProduct.formattedprice"></s:property><br />
		<b>Description:</b><s:property escape="false" value="publicProduct.description"></s:property><br />
		
		
		
		<div class="cartbutton">
<s:if test="productShipping.weight != null">
		
	<s:form action="product">
	<s:hidden name="id"/>
		
     <s:select label="Quantity" 
		headerKey="1" headerValue="1"
		list="#{'2':'2', '3':'3', '4':'4', '5':'5', '6':'6', '7':'7', '8':'8', '9':'9', '10':'10'}" 
		name="quantity" 
		value="1" />
     
     
      <s:submit name="addProduct" value="Add To Cart" />
     
    </s:form>
    </s:if>
    <s:else>
    <br /><b>Product Not Currently Available</b><br />
    
    </s:else>
	
</div>
	
 </div>
	 
	
	
		<div id="newsbar">
	<br />
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
	

	
		
		
</body>
</html>