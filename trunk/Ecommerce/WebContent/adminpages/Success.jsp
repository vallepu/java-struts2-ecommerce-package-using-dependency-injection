<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>


<%@ page language="java" contentType="text/html" import="java.util.*"%>

<html>
    <head>
    	 <sj:head jqueryui="true" jquerytheme="redmond" />
       
  </head>
    <body>
    
    <s:if test="#session.logged-in != 'true'">
      <jsp:forward page="/pages/uiTags/Login.jsp" />  
    </s:if>
    
    <s:url id="remoteurl" namespace="/" action="jsonaction"/> 
    <s:url id="editcellurl" namespace="/" action="editcellentry"/> 
        <sjg:grid 
        id="gridtable"
        altRows="true"
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
    >
        <sjg:gridColumn name="id" index="id" title="ID" formatter="integer" sortable="false"/>
        <sjg:gridColumn name="name" editable="true" index="name" title="Name" sortable="true"/>
        <sjg:gridColumn name="select" hidden="true" editable="yes" edittype="select" dataType="json" href="%{remoteurl}" index="select" title="Friend Selection" />
    </sjg:grid>
    
    
    <br />
    <s:select headerKey="0" headerValue="Select Month"
 name="smonth" list="#{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'Jun'}" 
 value="5" />
    
    <br />
    Comments:
   
    
    Welcome, you have logged in. <br />
    <b>Session Time: </b><%=new Date(session.getLastAccessedTime())%>
      <br /><br />
      
     <s:url id="remoteurl" action="jsonaction"/> 
	 <sj:select 
        href="%{remoteurl}" 
        id="echo2" 
        name="echo" 
        list="select"
        emptyOption="true" 
        headerKey="-1" 
        headerValue="Please Select a Language"
      />
      
     
      <a href="<%= request.getContextPath() %>/logout.action">Logout</a><br /><br />
      
    <b>User Name: </b> <s:property value="SESSIONID" />

    </body>
</html>