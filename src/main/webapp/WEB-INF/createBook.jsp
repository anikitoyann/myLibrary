<%@ page import="com.example.mylibrary.model.Author" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.mylibrary.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Book</title>
</head>
<body style="background-color:snow">
<a href="/">Back</a>
<h2>Create Books</h2>
<% List<Author> authors = (List<Author>) request.getAttribute("authors");%>
<% List<User> users = (List<User>) request.getAttribute("users");%>

<form action="/createBook" method="post" enctype="multipart/form-data">
    title<input type="text" name="title"><br>
    description<input type="text" name="description"><br>
    price<input type="text" name="price"><br>
    author_id:
    <select name="author_id">
        <% for (Author author : authors) { %>
        <option value="<%=author.getId()%>"><%=author.getName()%> <%=author.getSurname()%></option>
        <% }%>
    </select><br>
    user_id:
    <select name="user_id">
        <% for (User user :users){ %>
        <option value="<%=user.getId()%>"><%=user.getName()%> <%=user.getId()%></option>
        <% }%>
    </select>
    image:
    <input type="file"name="profilePic"><br>
    <input type="submit" value="Create">
</form>
</body>
</html>