package com.example.mylibrary.servlet;

import com.example.mylibrary.constants.SharedConstants;
import com.example.mylibrary.manager.AuthorManager;
import com.example.mylibrary.manager.BookManager;
import com.example.mylibrary.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/removeBook")
public class RemoveBookServlet extends HttpServlet {
    private BookManager bookManager = new BookManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Book byId = bookManager.getById(id);
        if (byId != null) {
            if (byId.getPicName() != null && !byId.getPicName().equalsIgnoreCase("null")) {
                File file = new File(SharedConstants.UPLOAD_FOLDER + byId.getPicName());
                if (file.exists()) {
                    file.delete();
                }
            }

            bookManager.removeById(id);
        }

        resp.sendRedirect("/books");
    }
}