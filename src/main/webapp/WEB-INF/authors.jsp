<%@ page import="com.example.mylibrary.model.Author" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.mylibrary.model.User" %>
<%@ page import="com.example.mylibrary.model.UserType" %>
<html>
<head>
  <title>Authors</title>
</head>
<% List<Author> authors = (List<Author>) request.getAttribute("authors");
  User user = (User) session.getAttribute("user");

%>
<body style="background-color: snow">
<a href="/">Back</a>
<H2 style="font-family: 'Arial Black'">Authors</H2>
<table border="1">
  <tr>
    <th>id</th>
    <th>name</th>
    <th>surname</th>
    <th>email</th>
    <th>age</th>
    <%if (user.getUserType()== UserType.ADMIN){%>
    <th>action</th>
    <%}%>
  </tr>
  <% if (authors != null && !authors.isEmpty()) { %>
  <%
    for (Author author : authors) { %>
  <tr>
    <td><%=author.getId()%></td>
    <td><%=author.getName()%></td>
    <td><%=author.getSurname()%></td>
    <td><%=author.getEmail()%></td>
    <td><%=author.getAge()%></td>
    <%if (user.getUserType()== UserType.ADMIN){%>
    <td><a href="/removeAuthor?id=<%=author.getId()%>">delete</a>
      / <a href="/updateAuthor?id=<%=author.getId()%>">update</a></td>
    <%} %>
  </tr>
  <%} %>
  <%} %>
</table>
</body>
</html>
