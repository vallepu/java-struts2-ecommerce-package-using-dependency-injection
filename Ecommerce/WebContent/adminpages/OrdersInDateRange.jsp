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

	
	function formatOrderInfo(cellvalue, options, rowObject) 
	{
		return "<a href='#' onClick='javascript:openDialog("+cellvalue+")'>Order Information</a>";
		
    }
	
	function openDialog(orderpaymentid) 
	{
		$("#order_details").load("<%= request.getContextPath() %>/generalorder.action?orderpaymentId=" + orderpaymentid);
		$("#order_details").dialog('open');
    }
        
		
	</script>
	
</head>
<body>
 	
 	
	<sj:dialog 
		id="order_details" 
		title="Order Details" 
		autoOpen="false" 
		modal="true"
		width="500"	
	>
	
	</sj:dialog>

	<br />
	<br />
	<br />
	<br />
	<br />
	
  
  <s:form id="categoryform" action="ordersindaterange">
  <s:url id="remoteurl" action="jsongeneralorderaction"/> 
     <s:url id="selectactionurl" action="categoryaction"/> 
	 
	 
	<sj:datepicker  id="datefrom" name="datefrom" key="datefrom" changeMonth="true" changeYear="true" displayFormat="mm.dd.yy" label="From" />
    <sj:datepicker  id="dateto" name="dateto" key="dateto" changeMonth="true" changeYear="true" displayFormat="mm.dd.yy" label="To" />
   
      <s:submit />
  </s:form>
	<s:url id="remoteurl" namespace="/" action="jsongeneralorderaction">
 	 	
 	 	
 	 	<s:param name="datefromto" value="datefromto" />
 	 	
 	</s:url>
 	
 	<b><s:property value="capture" /></b>
 	<b><s:property value="refund" /></b>
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	
    <s:if test="datefromto != null">
    <sjg:grid 
        id="gridtable"
        caption="Orders By Date" 
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
    
        <sjg:gridColumn name="orderdate" index="orderdate" hidden="false" title="Order Date" formatter="date" sortable="false"/>
        <sjg:gridColumn name="orderid" editable="false" index="orderid" title="Order ID" sortable="false"/>
         <sjg:gridColumn name="total" editable="false" index="total" title="Total" sortable="false"/>
         <sjg:gridColumn name="firstname" editable="false" index="firstname" title="First Name" sortable="false"/>
         <sjg:gridColumn name="lastname" editable="false" index="lastname" title="Last Name" sortable="false"/>
          <sjg:gridColumn name="status" editable="false" index="status" title="Order Status" sortable="false"/>
           <sjg:gridColumn name="id" index="id" title="Get Order Info" edittype="text" align="center" hidden="false" formatter="formatOrderInfo" key="true" sortable="false"/>
      
   
      
    </sjg:grid>
    
</s:if>
 
<br />
<br />
 
</body>
</html>