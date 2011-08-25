<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>

<title>Insert data here</title>
	 
	</head>

<body>

    <s:form action="/login.action" method="POST">
      <s:textfield name="userId" label="Login Id"/><br>
      <s:password name="password" label="Password"/><br>
         <s:submit value="Login" align="center"/>
    </s:form>
</body>
</html>