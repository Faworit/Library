package com.epam.library.Service;

import com.epam.library.entity.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

public class ShowEditBookMenuService implements Service{
    RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        /*String language;
        Book book;
        BookDAO bookDAO = new BookDAO();
        HttpSession session = request.getSession(true);
        int idBook = Integer.parseInt(request.getParameter("ID"));
        language = String.valueOf(session.getAttribute("language"));
        int IDlanguage;
        if(language.equals("RU") || language.equals("/") || language.isEmpty()){
            IDlanguage = RU;
        }
        else{
            IDlanguage = ENG;
        }*/
        Book book;
        book = GetBookService.getBookFromDB(request, response);
        request.setAttribute("book", book);
        dispatcher = request.getRequestDispatcher("jsp/editBook.jsp");
        dispatcher.forward(request, response);
    }
}
