package com.example.mylibrary.servlet;

import com.example.mylibrary.manager.BookManager;
import com.example.mylibrary.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/searchBook")
public class SearchBookServlet extends HttpServlet {
    BookManager bookManager=new BookManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchedTitle = req.getParameter("title");
        List<Book> searchResults = bookManager.getAllByBookName(searchedTitle);
                req.setAttribute("searchResults", searchResults);
        req.getRequestDispatcher("WEB-INF/search.jsp").forward(req, resp);

        }
    }