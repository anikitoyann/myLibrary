
<%@ page import="java.util.List" %>
<%@ page import="com.example.mylibrary.model.Book" %>
<html>
<head>
    <head>
        <title>Search Results</title>
    </head>
<body>
<h1>Search Results</h1>
<ul>
    <% for (Book book : (List<Book>) request.getAttribute("allBookName")) { %>
    <li><%= book.getTitle()%></li>
    <% } %>
</ul>

<% if (((List<Book>) request.getAttribute("allBookName")).isEmpty()) { %>
<p>No books found.</p>
<% } %>
</body>
</html>

