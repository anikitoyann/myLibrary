<%@ page import="com.example.mylibrary.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
<%User user = (User) session.getAttribute("user");%>
Welcome<%=user.getName()%> --->  <a href="/logout">LogOut</a> <br>
<a href="/authors"> See All Authors</a> | <br>
<a href="/books"> See All Books</a> | <br>
<a href="/createAuthor">Create authors</a> <br>
<a href="/createBook">Create Books</a>
</body>
</html>
