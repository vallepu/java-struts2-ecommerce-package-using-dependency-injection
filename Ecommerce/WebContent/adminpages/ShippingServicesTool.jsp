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

		
</head>
<body>
	
  <br />
  <br />
  <br />
  <br />
  <br />
  <br />
  <br />
  
  <s:form id="carrierform" action="shippingservicestool">
  <s:url id="remoteurl" action="jsoncategoryaction"/> 
     <s:url id="selectactionurl" action="categoryaction"/> 
   	<s:select  name="id" 
   			   list="shippingcarrierList" 
   			   listKey="id" 
   			   listValue="carrier" 
   			   label="Select a Carrier" />
   
	
      <s:submit />
  </s:form>
	<s:url id="remoteurl" namespace="/" action="jsonshippingservicesaction">
 	 	<s:param name="id" value="id" />
 	</s:url>
	
    <s:url id="editcellurl" namespace="/" action="shippingserviceseditcellentry"> 
    	<s:param name="carrierid" value="id" />
    </s:url>
      <sjg:grid 
        id="gridtable2" 
        caption="Shipping Services" 
        dataType="json" 
        href="%{remoteurl}" 
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
        <sjg:gridColumn name="servicename" index="servicename" editable="true" title="Service" sortable="true"/>
        <sjg:gridColumn name="servicecode" editable="true" index="description" title="Service Code" sortable="false"/>
      
    </sjg:grid>

      
   
    

 
<br />
<br />
 
</body>
</html>