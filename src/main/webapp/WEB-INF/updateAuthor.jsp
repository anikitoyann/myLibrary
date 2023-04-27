<%@ page import="com.example.mylibrary.model.Author" %>
<html>
<head>
    <title>Update Author</title>
</head>
<body>
<% Author authors= (Author) request.getAttribute("author");%>
<a href="/">Back</a>
<h2>Update Author</h2>
<form action="/updateAuthor" method="post" >
    <input name="id" type="hidden"value="<%=authors.getId()%>">
    name<input  name="name" type="text" value="<%=authors.getName()%>">
    surname<input name="surname" type="text" value="<%=authors.getSurname()%>">
    email<input name="email" type="text" value="<%=authors.getEmail()%>">
    age<input  name="age" type="text"value="<%=authors.getAge()%>">
   <br>
    <input type="submit" value="Update">
</form>

</body>
</html>
