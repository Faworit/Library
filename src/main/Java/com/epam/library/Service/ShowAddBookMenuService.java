package com.epam.library.Service;

import com.epam.library.connectionPool.BookDAO;
import com.epam.library.connectionPool.GenreDAO;
import com.epam.library.entity.Genre;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.epam.library.util.ConstantsOfLibrary.ENG;
import static com.epam.library.util.ConstantsOfLibrary.RU;

public class ShowAddBookMenuService implements Service{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession(true);
        String language = String.valueOf(session.getAttribute("language"));
        int IDlanguage;
        List<Genre> genres;
        if(language.equals("RU") || language.equals("/") || language.isEmpty()){
            IDlanguage = RU;
        }
        else{
            IDlanguage = ENG;
        }
        GenreDAO genreDAO = new GenreDAO();
        genres = genreDAO.getAllGenres(IDlanguage);
        session.setAttribute("list", genres);
        dispatcher = request.getRequestDispatcher("jsp/creatingBook.jsp");
        dispatcher.forward(request, response);
    }
}
