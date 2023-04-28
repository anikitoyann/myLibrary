<%@ page import="com.example.mylibrary.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
 <body style= background-image:url(../789813.2021-05-26-19-51-09.jpg) ;>
<%User user = (User) session.getAttribute("user");%>
Welcome<%=user.getName()%> --->  <a href="/logout"><span style="font-family: 'Arial Black';color: red">LogOut</span></a> <br>
<a href="/authors"> <h1 style="font-family: 'Arial Black'">See All Authors</h1></a> | <br>
<a href="/books"><h1 style="font-family: 'Arial Black'"> See All Books</h1></a> |  <br><br><br><br><br><br><br>
<a href="/createAuthor"><h1 style="font-family: 'Arial Black'">Create Authors</h1></a><br>
<a href="/createBook"><h1 style="font-family: 'Arial Black'">Create Books</h1></a>
</body>
</html>
