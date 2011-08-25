<%@ taglib uri="sitemesh-decorator" prefix="decorator" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
	  <title>
	    Yourwebdevcenter - <decorator:title default="SiteMesh Tutorial Example" />
	  </title>
	 <script type='text/javascript' src='./includes/jquery-1.2.3.min.js'></script>
	<script type='text/javascript' src='./includes/menu.js'></script>
	 <link rel="stylesheet" href="./includes/project.css" type="text/css" media="screen" />
	  	
	  <decorator:head />
	</head>
	  <body>
	  
	  <div id="headers">
	<div id="container">
	 <br />
	 <br />
	 

	 
	<ul id="nav">
	
    <li><a href="<%= request.getContextPath() %>/index.action">Inventory Control</a>
    	<ul>
    		
    		<li>
    			<a href="<%= request.getContextPath() %>/inventorycontrol.action">Product Control</a>
    		</li>
    		<li>
    			<a href="<%= request.getContextPath() %>/categorycontrol.action">Category Control</a>
    		</li>
    	</ul>
    
    </li>
    
     <li><a href="<%= request.getContextPath() %>/index.action">Shipping</a>
    	<ul>
    		<li>
    			<a href="<%= request.getContextPath() %>/shippingcarriertool.action">Shipping Carriers</a>
    		</li>
    		<li>
    			<a href="<%= request.getContextPath() %>/shippingservicestool.action">Carrier Services</a>
    		</li>
    	</ul>
    
    </li>
    
     <li><a href="<%= request.getContextPath() %>/index.action">Orders</a>
    	<ul>
    		<li>
    			<a href="<%= request.getContextPath() %>/incompleteorders.action">Incomplete Orders</a>
    		</li>
    		<li>
    			<a href="<%= request.getContextPath() %>/completeorders.action">Complete Orders</a>
    		</li>
    		<li>
    			<a href="<%= request.getContextPath() %>/refundedorders.action">Refunded Orders</a>
    		</li>
    		<li>
    			<a href="<%= request.getContextPath() %>/ordersindaterange.action">Orders By Date</a>
    		</li>
    	</ul>
    	
    		
    		
    	
    
    </li>
    
    <li><a href="<%= request.getContextPath() %>/index.action">Home</a></li>
    
   
    <li><a href="<%= request.getContextPath() %>/categoryproduct.action">Ecommerce</a></li>
    <li><a href="./logout.action">Logout</a></li>
   
</ul>
</div>
</div>
	  
	  
	   
	    <div id="content">
	      <decorator:body />
	    </div>
	  </body>
</html>