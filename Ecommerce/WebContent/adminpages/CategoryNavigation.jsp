<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>



<%@ page language="java" contentType="text/html" import="java.util.*"%>

<html>
<head>
<sj:head jqueryui="true" jquerytheme="redmond" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Category Navigational Page</title>

	<link rel="stylesheet" href="./includes/project.css" type="text/css" media="screen" />
	 <link rel="stylesheet" href="./includes/verticalnavigationn.css" type="text/css" media="screen" />
		<script type='text/javascript' src='./includes/verticalnavigation.js'></script>
		

</head>
<body>test
	using parameter to hold categoryid value that is chosen. we create a dropbox that has the amount of products
	that will show per page. there will be a default amount of products shown, but if they choose a category
	and then want to show a different amount of products per page, then we use the categoryId parameter and send the 
	categoryId with the change from the select box to the CategoryProductAction controller class. the class will then
	make a call to AuthenticationServices. the controller class will send the amount of products to return (numerical figure)
	and which page number is current.
	NEED:
	1) to design a pagination system. I need to show, on the pagination at the bottom of the page, the total number of pages if 
	its a small amount, or a number of pages that reasonably fits in the page. on the left of those
	pages (which are clickable links that then call thru our controller class the products that correspond to that page number)
	is a back button which takes the person to the previous page. If the are on page one this should be disabled. To the right of
	the clickable pages links will be  a forward button. This should take the user to the page after the current one. This should be 
	disabled when the user has reached the very last page.
	2)mabey use freemarker to accomplish this task. freemarker has a language syntax that works with the conroller class.
	 it has the ability to make dynamic html that changes with changes to the controller its self. we would need this as the user
	 might all the sudden click on a different category, and then the amount of pages available for pagination would change as well.
	 
	 The only problem is, how will we use our param called categoryId, which is a struts2 tag with the links shown in the pagination?
	 the answer is simple, we will, when building the pagination html in the controller class, include the current categoryId with
	 the information as well, then when the user clicks on the link, it will send the categoryId, number of products per page, and 
	 the page number to be displayed to the controller class, which will obtain the data, store it in its properties, call the 
	 service class and return the correct number and paginated products for display.
	 
	 note: the amount of pages for pagination will depend upon the total amount available for return. when we make
	 out initial call the service class and send the amount per page number, and the categoryId value, we will need
	 returned the total number of products which using division will determine how many pages for pagination are available
	 we will need to integrate this into our pagination with freemarker.
	 
	 Currently we are returning a List of Products as our return when making the AuthenticationServices call with the method
	 getPublicProductsByCategoryId( ), but we will need to instead return the list of products and the total amount of products.
	 This means we can keep the 2 in an Object array. When we get the array back from the service class, our controller
	 will then open it and attach the PublicProduct List to the controller property and use the total number (numerical digit)
	 of products available returned to determine how many pages can be pagination and build a freemarker pagination 
	 html output that will create the pagination functionality that will be displayed at the end of the jsp page.
	 
	 Note: we need to make the selectbox, when they change the value to a different amount of products per page do an auto submit
	 to the controller with the categoryId from the struts2 parameter data that says the categoryid (we need both). If for instance
	 they want to change the number per page before they actually click on a category then the parameter value (which 
	 gets its value from the controller class then the select box will send a null value for categoryId. (we could always disable
	 the selectbox (not show it) until they actually click on a category if this becomes troublesome). the page would reload
	 and we would add this data to our suckerfish dropbox menu so when they clicked on a category afterwards the link from the 
	 suckerfish menu would include the amount of products to show per page.
	 
   <s:param name="categoryid" value="categoryId" />
   
	 <s:property escape="false" value="catbuilt"/>
	<s:if test="publicproductList != null">
		<s:iterator value="publicproductList" var="publicproperty">
			<s:property value="%{#publicproperty.name}"/>
		</s:iterator>
	</s:if>
		
</body>
</html>