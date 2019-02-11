package com.epam.library.Service;

import com.epam.library.connectionPool.AddGenreDAO;
import com.epam.library.validator.GenreValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

import static com.epam.library.util.ConstantsOfLibrary.ENG;
import static com.epam.library.util.ConstantsOfLibrary.RU;

public class AddGenreService implements Service {
    RequestDispatcher dispatcher;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        int idGenre = Integer.parseInt(request.getParameter("idGenre"));
        String genreNameRU = request.getParameter("genreNameRU");
        String genreNameENG = request.getParameter("genreNameENG");
        boolean isExistsID =  GenreValidator.checkExistingGenreID(idGenre, RU);
        boolean isExistsNameRU = GenreValidator.checkExistingGenreID(genreNameRU, RU);
        boolean isExistsNameENG = GenreValidator.checkExistingGenreID(genreNameENG, ENG);
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
            addGenreDAO.addGenre(idGenre, ENG, genreNameENG);
            addGenreDAO.addGenre(idGenre, RU, genreNameRU);
            ShowAddBookMenuService showAddBookMenuService = new ShowAddBookMenuService();
            showAddBookMenuService.execute(request, response);
        }
    }
}
