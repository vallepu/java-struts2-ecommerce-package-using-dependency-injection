<%@ taglib uri="sitemesh-decorator" prefix="decorator" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
	<head>
	  <title>
	    Yourwebdevcenter - <decorator:title default="SiteMesh Tutorial Example" />
	  </title>
	 <script type='text/javascript' src='jquery-1.4.3.js'></script>
	
	<link rel="stylesheet" href="./includes/testverticalnavigation.css" type="text/css" media="screen" />
	<script type='text/javascript' src='./includes/verticalnavigation.js'></script>
	
 	
		
		
	  <decorator:head />
	</head>
	  <body>
	  
	  <div class="header">
	 
	 
	  
	  <div id="headers">
	<div id="container">
	 <br />
	 <br />
	 

	 
	<ul id="nav">
	
   
    		
    		
    
    
    <li><a href="<%= request.getContextPath() %>/login.action">Administration</a></li>
    
   
    <li><a href="<%= request.getContextPath() %>/categoryproduct.action">Ecommerce</a></li>
    <li><a href="<%= request.getContextPath() %>/companyinformation.action">Company Information</a></li>
   
</ul>
</div>
</div>
</div>	  
	  
	   
	  
	      <decorator:body />
	   
	  </body>
</html>