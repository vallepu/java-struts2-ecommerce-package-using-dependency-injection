<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>


<%@ page language="java" contentType="text/html" import="java.util.*"%>
<html>
<head>
	<sj:head jqueryui="true" jquerytheme="redmond" />
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>You have logged in.....</title>
	


<link rel="stylesheet" href="./includes/project.css" type="text/css" media="screen" />
<link rel="stylesheet" href="./includes/verticalnavigationn.css" type="text/css" media="screen" />
<script type='text/javascript' src='./includes/verticalnavigation.js'></script>
		
	
	<script type="text/javascript">

	
		function formatCategory(cellvalue, options, rowObject) 
		{
			
			var myCategories = new Array();
			"<s:iterator value='categories'>";
			myCategories["<s:property value='id'/>"] = "<s:property value='name'/>";
			"</s:iterator>";
				
			return myCategories[cellvalue];
			
        }

		function formatImagesLink(cellvalue, options, rowObject) 
		{
			//return "<a href='image.action?id=" + cellvalue + "'>Image</a>";
			 return "<a href='#' onClick='javascript:openDialog("+cellvalue+")'>Images</a>";
			
        }
		
		function openDialog(productid) 
		{
			$("#product_image_details").load("<%= request.getContextPath() %>/images.action?id=" + productid);
			$("#product_image_details").dialog('open');
        }
        
		
	</script>
	
</head>
<body>
 
 	
	<sj:dialog 
		id="product_image_details" 
		title="Image Details" 
		autoOpen="false" 
		modal="true"
		width="400"
		
	>
	
	</sj:dialog>

	<br />
	<br />
	<br />
	<br />
	<br />
	
  
  <s:form id="categoryform" action="inventorycontrol">
  <s:url id="remoteurl" action="jsoncategoryaction"/> 
     <s:url id="selectactionurl" action="categoryaction"/> 
   	<s:select  name="Category" 
   			   list="categories" 
   			   listKey="id" 
   			   listValue="name" 
   			   label="Select a Category" />
   
	
      <s:submit />
  </s:form>
	<s:url id="remoteurl" namespace="/" action="jsoninventorycontrolaction">
 	 	<s:param name="category" value="category" />
 	</s:url>
	
    <s:url id="editcellurl" namespace="/" action="inventorycontroleditcellentry"> 
    	<s:param name="category" value="category" />
    </s:url>
    <sjg:grid 
        id="gridtable"
        caption="Customer Examples" 
        dataType="json" 
        href="%{remoteurl}" 
        pager="true" 
        gridModel="gridModel"
        viewrecords="true"
        errorText="true"
        cellEdit="true"
        cellurl="%{editcellurl}"   
        editurl="%{editcellurl}"
        navigator="true"
        navigatorEdit="true"
        navigatorDelete="true"  
        navigatorEditOptions="{height:280,reloadAfterSubmit:true}"
    >
    
        <sjg:gridColumn name="categoryid" index="categoryid" hidden="true" title="categoryID" formatter="integer" sortable="false"/>
       
        <sjg:gridColumn name="name" searchoptions="{sopt:['eq']}" editable="true" index="name" title="Name" sortable="true"/>
         <sjg:gridColumn name="summary" searchoptions="{sopt:['eq']}" editable="true" index="summary" title="Summary" sortable="true"/>
         <sjg:gridColumn name="description" searchoptions="{sopt:['eq']}" editable="true" index="description" title="Description" sortable="true"/>
          <sjg:gridColumn name="price" searchoptions="{sopt:['eq']}" editable="true" index="price" title="Price" sortable="true"/>
           <sjg:gridColumn name="quantity" searchoptions="{sopt:['eq']}" editable="true" index="quantity" title="Quantity" sortable="true"/>
           <sjg:gridColumn name="id" index="id" title="Images" edittype="select" align="center" hidden="false" formatter="formatImagesLink" key="true" sortable="false"/>
      <sjg:gridColumn 
    		name="categoryid"
    		id="categoryid"
    		index="id"
    		title="Category" 
    		align="center"
    		formatter="formatCategory"
    		cssClass="link"
    		sortable="true" 
    		search="false"
    		searchoptions="{sopt:['eq']}"
    		editable="true"
    		surl="%{selectactionurl}"
    		edittype="select" 
    		editoptions="{ dataUrl : '%{selectactionurl}' }"
    		formoptions="{label:'Select a Category'}"
    		
    		/>
      

      
    </sjg:grid>
    

 
<br />
<br />
 
</body>
</html>