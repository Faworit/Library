package com.epam.library.Service;

import com.epam.library.entity.Genre;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EditBookService implements Service{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession(true);
        int idBook = Integer.parseInt(request.getParameter("ID"));


        request.setAttribute("list", idBook);
        dispatcher = request.getRequestDispatcher("jsp/editBook.jsp");
        dispatcher.forward(request, response);
    }
}
