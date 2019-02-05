package com.epam.library.Service;

import com.epam.library.connectionPool.RemoveBookDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveBookService implements Service{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookID = Integer.parseInt(request.getParameter("removeBook"));
        String jspName = request.getParameter("jspname");
        RemoveBookDAO removeBookDAO = new RemoveBookDAO();
        removeBookDAO.removeBook(bookID);
        RequestDispatcher dispatcher;
        dispatcher = request.getRequestDispatcher("/" + jspName);
        dispatcher.forward(request, response);


    }
}
