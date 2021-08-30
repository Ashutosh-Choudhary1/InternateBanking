<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
out.println("Dear "+session.getAttribute("NAME")+", Thank you for showing interest in our loan facility our executive will contact you in our loan facility.Our executive will contact you in below email id "+session.getAttribute("EMAIL"));
%>
</body>
</html>