package com.example.mylibrary.servlet;

import com.example.mylibrary.manager.AuthorManager;
import com.example.mylibrary.manager.BookManager;
import com.example.mylibrary.model.Author;
import com.example.mylibrary.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/updateBook")
public class UpdateBookServlet extends HttpServlet {
    private BookManager bookManager=new BookManager();
    private AuthorManager authorManager=new AuthorManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> authors = authorManager.getAll();
        req.setAttribute("authors",authors);
        int id = Integer.parseInt(req.getParameter("id"));
        Book book = bookManager.getById(id);
        req.setAttribute("book", book);
        req.getRequestDispatcher("WEB-INF/updateBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id= Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        int price = Integer.parseInt(req.getParameter("price"));
        int author_id = Integer.parseInt(req.getParameter("author_id"));
        Book book=new Book();
        book.setId(id);
     book.setTitle(title);
     book.setDescription(description);
        book.setPrice(price);
        book.setAuthor(authorManager.getById(author_id));
        bookManager.update(book);
        resp.sendRedirect("/books");
    }
}
