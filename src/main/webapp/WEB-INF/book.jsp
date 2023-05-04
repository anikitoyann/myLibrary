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
    String keyword = request.getParameter("keyword") == null || !request.getParameter("keyword").equals(null) ?
            "" : request.getParameter("keyword");
%>

<body style=background-color:snow>
<a href="/">Back</a>
<H2 style="font-family: 'Arial Black'">Books</H2>
<table border="1">
    <tr>
        <th>image</th>
        <th>id</th>
        <th>title</th>
        <th>description</th>
        <th>price</th>
        <th>author_id</th>
        <th>user_id</th>
        <%if (user.getUserType() == UserType.ADMIN) {%>
        <th>action</th>
        <%}%>
    </tr>
    <% if (books != null && !books.isEmpty()) { %>
    <%
        for (Book book : books) { %>
    <tr>
        <td><%if (book.getPicName() == null) {%>
            <img src="/img/defaultPic.png" width="50">
            <%} else {%>
            <a href="/getImage?picName=<%=book.getPicName()%>"> <img src="/getImage?picName=<%=book.getPicName()%>"
                                                                     width="50"></a>
        </td>
            <%} %>
        <td><%=book.getId()%>
        </td>
        <td><%=book.getTitle()%>
        </td>
        <td><%=book.getDescription()%>
        </td>
        <td><%=book.getPrice()%>
        </td>
        <td><%=book.getAuthor().getId()%>
        </td>
        <th><%= (book.getUser() != null ) ? book.getUser().getId() : "" %>
        </th>
            <%if (user.getUserType()== UserType.ADMIN){%>
        <td><a href="/removeBook?id=<%=book.getId()%>">delete</a>
            / <a href="/updateBook?id=<%=book.getId()%>">update</a></td>
            <%} %>
            <%} %>

            <% } else { %>

    <tr>
        <td colspan="7">No books found.</td>
    </tr>
    <%}%>
    </tr>
    <form action="/books" method="get">
        Searching <input name="keyword" type="text" value="<%=keyword%>">
        <input type="submit" value="search"></form>
</table>
</body>
</html>
