<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>admin strana</title>
</head>
<body>
	<h1>Helloooooooo ADMIN </h1>
	<%
		User user = (User)request.getAttribute("user");
	%>
	Vas user name je:<br>
	
	<%= user.getUserName()%>
</body>
</html>