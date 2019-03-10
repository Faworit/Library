package com.epam.library.serv;

import com.epam.library.dataBase.AuthorDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

public class AddAuthorService implements Service {

    RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        boolean isExists;
        AuthorDAO authorDAO = new AuthorDAO();
        isExists = authorDAO.isExist(name, surname);
        if(!isExists){
            authorDAO.setAuthor(name, surname);
            ShowAddBookMenuService showAddBookMenuService = new ShowAddBookMenuService();
            showAddBookMenuService.execute(request, response);
        } else {
            request.setAttribute("authorError", "Author is exists");
            dispatcher = request.getRequestDispatcher("jsp/addAuthor.jsp");
            dispatcher.forward(request, response);
        }
    }
}
