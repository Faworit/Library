package com.epam.library.Service;

import com.epam.library.dataBase.BookDAO;
import com.epam.library.entity.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.epam.library.util.ConstantsOfLibrary.ENG;
import static com.epam.library.util.ConstantsOfLibrary.RU;

public class ShowBookService implements Service {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession(true);
        BookDAO bookDAO = new BookDAO();
        String language = String.valueOf(session.getAttribute("language"));
        int IDlanguage;
        if(language.equals("RU") || language.equals("/")){
            IDlanguage = RU;
        }
        else{
            IDlanguage = ENG;
        }
        List<Book> books = bookDAO.getBook(IDlanguage);
        session.setAttribute("list", books);
        dispatcher = request.getRequestDispatcher("jsp/user.jsp");
        dispatcher.forward(request, response);
    }
}
