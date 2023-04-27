<%@ page import="com.example.mylibrary.model.Author" %>
<%@ page import="com.example.mylibrary.model.Book" %>
<%@ page import="java.util.List" %>
<html>
<head>
  <title>Update Book</title>
</head>
<body>
<% Book book= (Book)request.getAttribute("book");%>
<% List<Author> authors = (List<Author>) request.getAttribute("authors");%>
<a href="/">Back</a>
<h2>Update Book</h2>
<form action="/updateBook" method="post" >
  <input name="id" type="hidden"value="<%=book.getId()%>">
  title<input  name="title" type="text" value="<%=book.getTitle()%>">
  description<input name="description" type="text" value="<%=book.getDescription()%>">
  price<input name="price" type="text" value="<%=book.getPrice()%>">
  author_id:
  <select name="author_id">
  <% for (Author author : authors) { %>
  <option value="<%=author.getId()%>"><%=author.getName()%></option>
  <%}%>
</select>
  <input type="submit" value="Update">
</form>

</body>
</html>
