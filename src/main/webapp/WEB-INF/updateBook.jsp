<%@ page import="com.example.mylibrary.model.Author" %>
<%@ page import="com.example.mylibrary.model.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.mylibrary.model.User" %>
<html>
<head>
  <title>Update Book</title>
</head>
<body>
<% Book book= (Book)request.getAttribute("book");%>
<% List<Author> authors = (List<Author>) request.getAttribute("authors");%>
<% List<User> users = (List<User>) request.getAttribute("users");%>
<a href="/">Back</a>
<h2>Update Book</h2>
<form action="/updateBook" method="post" enctype="multipart/form-data" >
  <input name="id" type="hidden"value="<%=book.getId()%>">
  title<input  name="title" type="text" value="<%=book.getTitle()%>">
  description<input name="description" type="text" value="<%=book.getDescription()%>">
  price<input name="price" type="text" value="<%=book.getPrice()%>">
  author_id:
  <select name="author_id">
  <% for (Author author : authors) { %>
  <option value="<%=author.getId()%>"><%=author.getName() %></option>
  <%}%>
</select><br>
  user_id:
  <select name="user_id">
    <% for (User user :users){ %>
    <option value="<%=user.getId()%>"><%=user.getName()%> <%=user.getId()%>></option>
    <% }%>
  </select><br>
  <input type="file"name="profilePic"><br>
  <input type="submit" value="Update">
</form>
</body>
</html>
