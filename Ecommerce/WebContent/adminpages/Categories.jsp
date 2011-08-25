<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>


<%@ page language="java" contentType="text/html" import="java.util.*"%>

<html>
<head>
<sj:head jqueryui="true" jquerytheme="redmond" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

	<script type="text/javascript">

	
		function formatParentCategory(cellvalue, options, rowObject) 
		{
			
			var myCategories = new Array();
			"<s:iterator value='categoryList'>";
			myCategories["<s:property value='id'/>"] = "<s:property value='name'/>";
			"</s:iterator>";
			if(myCategories[cellvalue] != null)
			{
				
			return myCategories[cellvalue];
			}
			else
			{
				return "No Sub-Categories";
			}
			
			
        }


  </script>




</head>
<body>
<br />
<br />
<br />
<br />
	
 <s:url id="remoteurl2" namespace="/" action="jsoncategorycontrol" />
 	 	<s:url id="selectactionurl" action="categoryaction"/> 
    <s:url id="editcellurl" namespace="/" action="categorycontroleditcellentry" /> 
    <sjg:grid 
        id="gridtable2" 
        caption="Category Control" 
        dataType="json" 
        href="%{remoteurl2}" 
        pager="true" 
        gridModel="gridModel"
         cellEdit="true"
        cellurl="%{editcellurl}"   
        editurl="%{editcellurl}"
        navigator="true"
        navigatorEdit="true"
        navigatorDelete="true"  
        
    >
        <sjg:gridColumn name="id" index="id" title="ID" hidden="true" formatter="integer" sortable="false"/>
        <sjg:gridColumn name="name" index="name" editable="true" title="Category" sortable="true"/>
        <sjg:gridColumn name="description" editable="true" index="description" title="Desription" sortable="false"/>
      
        <sjg:gridColumn 
    		name="parentid"
    		id="parentid"
    		index="parentid"
    		title="Parent Category" 
    		align="center"
    		formatter="formatParentCategory"
    		cssClass="link"
    		sortable="true" 
    		search="false"
    		searchoptions="{sopt:['eq']}"
    		editable="true"
    		surl="%{selectactionurl}"
    		edittype="select" 
    		editoptions="{ dataUrl : '%{selectactionurl}' }"
    		formoptions="{label:'Select a Parent Category'}"
    		
    		/>
      
    </sjg:grid>

</body>
</html>