package com.epam.library.service;

import com.epam.library.dataBase.AddGenreDAO;
import com.epam.library.dataBase.LanguageDAO;
import com.epam.library.validator.GenreValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddGenreService implements Service {
    RequestDispatcher dispatcher;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idGenre = Integer.parseInt(request.getParameter("idGenre"));
        int idRussianLanguage;
        int idEnglishLanguage;
        String genreNameRU = request.getParameter("genreNameRU");
        String genreNameENG = request.getParameter("genreNameENG");
        LanguageDAO languageDAO = new LanguageDAO();
        idRussianLanguage = languageDAO.getIdLanguage("RU");
        idEnglishLanguage = languageDAO.getIdLanguage("ENG");
        boolean isExistsID =  GenreValidator.checkExistingGenreID(idGenre, idRussianLanguage);
        boolean isExistsNameRU = GenreValidator.checkExistingGenreID(genreNameRU, idRussianLanguage);
        boolean isExistsNameENG = GenreValidator.checkExistingGenreID(genreNameENG, idEnglishLanguage);
        if(isExistsID){
            request.setAttribute("errorID", "Genre with that ID exists");
            dispatcher = request.getRequestDispatcher("jsp/creatingGenre.jsp");
            dispatcher.forward(request, response);
        } else if(isExistsNameRU){
            request.setAttribute("errorNameRU", "Genre with that name exists");
            dispatcher = request.getRequestDispatcher("jsp/creatingGenre.jsp");
            dispatcher.forward(request, response);
        } else if(isExistsNameENG){
            request.setAttribute("errorNameENG", "Genre with that name exists");
            dispatcher = request.getRequestDispatcher("jsp/creatingGenre.jsp");
            dispatcher.forward(request, response);
        } else {
            AddGenreDAO addGenreDAO = new AddGenreDAO();
            addGenreDAO.addGenre(idGenre, idEnglishLanguage, genreNameENG);
            addGenreDAO.addGenre(idGenre, idRussianLanguage, genreNameRU);
            ShowAddBookMenuService showAddBookMenuService = new ShowAddBookMenuService();
            showAddBookMenuService.execute(request, response);
        }
    }
}
