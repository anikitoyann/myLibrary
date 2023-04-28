<%@ page import="java.util.List" %>
<%@ page import="com.example.mylibrary.model.Book" %>
<html>
<head>
    <title>Search Results</title>
</head>
<body>
<h1>Search Results</h1>
<% List<Book> books = (List<Book>) request.getAttribute("searchResults");
    String searchedTitle = request.getParameter("searchResults");
%>

<table border="1">
    <tr>
        <th>id</th>
        <th>title</th>
        <th>description</th>
        <th>price</th>
        <th>author_id</th>
    </tr>
    <% for (Book book : books) {
        if (book.getTitle().contains(searchedTitle)) {
    %>
    <tr>
        <td><%= book.getId() %></td>
        <td><%= book.getTitle() %></td>
        <td><%= book.getDescription() %></td>
        <td><%= book.getPrice() %></td>
        <td><%= book.getAuthor().getId() %></td>
    </tr>
    <%     }
    }
        if (books.isEmpty()) {
    %>
    <tr>
        <td colspan="5">No books found.</td>
    </tr>
    <% } %>
</table>
</body>
</html>