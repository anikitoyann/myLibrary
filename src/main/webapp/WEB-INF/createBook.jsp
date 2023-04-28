<%@ page import="com.example.mylibrary.model.Author" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Book</title>
</head>
<body>
<a href="/">Back</a>
<h2>Create Books</h2>
<% List<Author> authors = (List<Author>) request.getAttribute("authors");%>
<form action="/createBook" method="post">
    title<input type="text" name="title"><br>
    description<input type="text" name="description"><br>
    price<input type="text" name="price"><br>
    author_id:
    <select name="author_id">
        <% for (Author author : authors) { %>
        <option value="<%=author.getId()%>"><%=author.getName()%> <%=author.getSurname()%></option>
        <% }%>
    </select>
    <input type="submit" value="Create">
</form>
</body>
</html>