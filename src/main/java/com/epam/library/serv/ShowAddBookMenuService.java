package com.epam.library.service;

import com.epam.library.dataBase.GenreDAO;
import com.epam.library.dataBase.LanguageDAO;
import com.epam.library.entity.Genre;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ShowAddBookMenuService implements Service {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        int idLanguage;
        List<Genre> genres;
        HttpSession session = request.getSession(true);
        LanguageDAO languageDAO = new LanguageDAO();
        GenreDAO genreDAO = new GenreDAO();
        idLanguage = languageDAO.getIdLanguage(String.valueOf(session.getAttribute("language")));
        genres = genreDAO.getAllGenres(idLanguage);
        session.setAttribute("list", genres);
        dispatcher = request.getRequestDispatcher("jsp/creatingBook.jsp");
        dispatcher.forward(request, response);
    }
}
