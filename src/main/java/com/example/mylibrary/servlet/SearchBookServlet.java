package com.example.mylibrary.servlet;

import com.example.mylibrary.manager.BookManager;
import com.example.mylibrary.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/searchBook")
public class SearchBookServlet extends HttpServlet {
    BookManager bookManager=new BookManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title=req.getParameter("title");
        List<Book> allBookName = bookManager.getAllByBookName(title);
        req.setAttribute("allBookName",allBookName);
        req.getRequestDispatcher("WEB-INF/search.jsp").forward(req,resp);
        resp.sendRedirect("/books");
    }
}
