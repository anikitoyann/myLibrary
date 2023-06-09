package com.example.mylibrary.servlet;

import com.example.mylibrary.manager.BookManager;
import com.example.mylibrary.model.Book;
import com.example.mylibrary.model.User;
import com.example.mylibrary.model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/books")
public class BookServlet extends HttpServlet {
    BookManager bookManager = new BookManager();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Book> result = null;
        if (user != null) {
            if (UserType.ADMIN.equals(user.getUserType())) {
                String keyword = req.getParameter("keyword");
                if (keyword == null || keyword.isEmpty()) {
                    result = bookManager.getAll();
                } else {
                    result = bookManager.search(keyword);
                }
            } else if (UserType.USER.equals(user.getUserType())) {
                result = bookManager.getBooksByUser(user);
            }
        }
        req.setAttribute("books", result);
        req.getRequestDispatcher("WEB-INF/book.jsp").forward(req, resp);
    }}
