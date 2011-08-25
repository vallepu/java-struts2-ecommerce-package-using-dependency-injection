<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>


<%@ page language="java" contentType="text/html" import="java.util.*"%>

<html>
<head>
<sj:head jqueryui="true" jquerytheme="redmond" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shipping Carriers</title>
</head>
<body>
<br />
<br />
<br />
<br />
	
 <s:url id="remoteurl2" namespace="/" action="jsonshippingcarrieraction" />
 	 	
    <s:url id="editcellurl" namespace="/" action="shippingcarriercontroleditcellentry" /> 
    <sjg:grid 
        id="gridtable2" 
        caption="Shippin Carriers" 
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
        <sjg:gridColumn name="carrier" index="carrier" editable="true" title="Carrier" sortable="true"/>
     
      
    </sjg:grid>

</body>
</html>