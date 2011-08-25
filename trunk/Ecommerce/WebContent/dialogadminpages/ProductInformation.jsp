<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>



<%@ page language="java" contentType="text/html" import="java.util.*"%>

<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

 <link rel="stylesheet" href="./includes/testverticalnavigation.css" type="text/css" media="screen" />

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
<title>Product Information</title>

	
	
	 
	
	
		

</head>
<body>
 




<s:if test="publicProduct != null">
		<div class="center">
			 
			 <div align="center">
				<table border="0" cellpadding="0" cellspacing="0" width="85%">
					<tr>
						<td valign="top" width="74">
							<table border="0" cellpadding="3" cellspacing="0" width="100%" id="rolllist">
							
								<s:if test="publicProduct.front != ''">
								<tr>
									<td><a href="#" onmouseover="showImg('a_1','roll_a');showImg('b_1','roll_b')"><br /><img src="<s:property value='publicProduct.front'/>" width="50" height="50" border="0"></a></td>
								</tr>
								</s:if>
								<s:if test="publicProduct.back != ''">
								<tr>
									<td><a href="#" onmouseover="showImg('a_2','roll_a');showImg('b_2','roll_b')"><img src="<s:property value='publicProduct.back'/>" width="50" height="50" border="0"></a></td>
								</tr>
								</s:if>
								<s:if test="publicProduct.sidea != ''">
								<tr>
									<td><a href="#" onmouseover="showImg('a_3','roll_a');showImg('b_3','roll_b')"><img src="<s:property value='publicProduct.sidea'/>" width="50" height="50" border="0"></a></td>
								</tr>
								</s:if>
								<s:if test="publicProduct.sideb != ''">
								<tr>
									<td><a href="#" onmouseover="showImg('a_4','roll_a');showImg('b_4','roll_b')"><img src="<s:property value='publicProduct.sideb'/>" width="50" height="50" border="0"></a></td>
								</tr>
								</s:if>
								
								<s:if test="publicProduct.top != ''">
								<tr>
									<td><a href="#" onmouseover="showImg('a_5','roll_a');showImg('b_5','roll_b')"><img src="<s:property value='publicProduct.top'/>" width="50" height="50" border="0"></a></td>
								</tr>
								</s:if>
								<s:if test="publicProduct.bottom != ''">
								<tr>
									<td><a href="#" onmouseover="showImg('a_6','roll_a');showImg('b_6','roll_b')"><img src="<s:property value='publicProduct.bottom'/>" width="50" height="50" border="0"></a></td>
								</tr>
								</s:if>
							</table>
						</td>
						<td align="center" width="426" valign="center" align="center">
							<img src="<s:property value='publicProduct.front'/>" width="150" height="150" id="roll_a">
						</td>
						<td valign="top" valign="center" align="left">
							<br><img src="./includes/Front.jpg" id="roll_b">
						</td>
					</tr>
				</table>
			</div>
		</div>
			<div class="dialogpage">
				<b>Product Id:</b><s:property value="publicProduct.id"></s:property><br />
				<b>Product Name:</b><s:property value="publicProduct.name"></s:property><br />
			 	<b>Category:</b><s:property value="category.name"></s:property><br />
				<b>Price:</b><s:property value="publicProduct.formattedprice"></s:property><br />
				<b>Number In Stock:</b><s:property value="publicProduct.quantity"></s:property><br />
				<b>Summary:</b><s:property value="publicProduct.summary"></s:property><br />
				
			</div>	
			
		
</s:if>	
<s:else>
<div class="dialogpage">
<b>Discontinued Product: Information Obtained From Product Archives</b><br />

	<b>Product Id:</b><s:property value="productArchive.productid"></s:property><br />
	<b>Product Name:</b><s:property value="productArchive.productname"></s:property><br />
	<b>Category:</b><s:property value="productArchive.categoryname"></s:property><br />
	<b>Price:</b><s:property value="productArchive.price"></s:property><br />
	<b>Discontinued On:</b><s:property value="productArchive.datedeleted"></s:property><br />
	<b>Summary:</b><s:property value="productArchive.summary"></s:property><br />
</div>
</s:else>	

</body>
</html>