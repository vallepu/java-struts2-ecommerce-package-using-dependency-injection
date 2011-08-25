<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>

<title>Administrator Login</title>
	 
	  <link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css"/> 
</head>

<body>
<br />
<br />
<br />
<br />
<br />
<br />
<br />

    <s:form action="/login.action" method="POST">
      <b>User Id:</b><s:textfield name="userId" label="Login Id"/><br>
      <b>Password:</b><s:password name="password" label="Password"/><br>
         <s:submit value="Login" align="center"/>
    </s:form>
</body>
</html>