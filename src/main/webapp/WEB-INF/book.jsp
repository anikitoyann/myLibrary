
<%@ page import="java.util.List" %>
<%@ page import="com.example.mylibrary.model.User" %>
<%@ page import="com.example.mylibrary.model.UserType" %>
<%@ page import="com.example.mylibrary.model.Book" %>
<html>
<head>
    <title>Books</title>
</head>
<% List<Book> books = (List<Book>) request.getAttribute("books");
    User user = (User) session.getAttribute("user");
%>

<body style="background-color: snow">
<a href="/">Back</a>
<H2 style="font-family: 'Arial Black'">Books</H2>
<table border="1">
    <tr>
        <th>id</th>
        <th>title</th>
        <th>description</th>
        <th>price</th>
        <th>author_id</th>
        <%if (user.getUserType()== UserType.ADMIN){%>
        <th>action</th>
        <%}%>
    </tr>
    <% if (books != null && !books.isEmpty()) { %>
    <%
        for (Book book : books) { %>
    <tr>
        <td><%=book.getId()%></td>
        <td><%=book.getTitle()%></td>
        <td><%=book.getDescription()%></td>
        <td><%=book.getPrice()%></td>
        <td><%=book.getAuthor().getId()%></td>
        <%if (user.getUserType()== UserType.ADMIN){%>
        <td><a href="/removeBook?id=<%=book.getId()%>">delete</a>
            / <a href="/updateBook?id=<%=book.getId()%>">update</a></td>


        <%} %>
    </tr>
    <%} %>
    <%} %>
    <form action="/searchBook?>" method="post">
        Searching <input name="search" type="text">
        <input type="submit" value="search"></form>
</table>

</body>
</html>
