package com.epam.library.Service;

import com.epam.library.dataBase.AuthorDAO;
import com.epam.library.dataBase.BookDAO;
import com.epam.library.dataBase.GenreDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.library.util.ConstantsOfLibrary.ENG;
import static com.epam.library.util.ConstantsOfLibrary.RU;

public class EditBookService implements Service{
    RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        int idBook = Integer.parseInt(request.getParameter("ID"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int idAuthor = Integer.parseInt(request.getParameter("idAuthor"));
        String[] idGenres = request.getParameterValues("idGenre");
        int IDlanguage;
        String title = request.getParameter("title");
        String[] genres = request.getParameterValues("genre");
        String[] authorNames = request.getParameterValues("name");
        String[] authorSurnames = request.getParameterValues("surname");
        String ISBN = request.getParameter("ISBN");
        List<Integer> idGenreConvert = convertFromString(idGenres);
        BookDAO bookDAO = new BookDAO();
        AuthorDAO authorDAO = new AuthorDAO();
        GenreDAO genreDAO = new GenreDAO();
        HttpSession session = request.getSession(true);
        String language = String.valueOf(session.getAttribute("language"));
        if(language.equals("RU") || language.equals("/") || language.isEmpty()){
            IDlanguage = RU;
        }
        else{
            IDlanguage = ENG;
        }
        bookDAO.editBook(title, ISBN, quantity, idBook, IDlanguage);
        for (int i = 0; i < authorNames.length; i++) {
            authorDAO.editAuthor(authorNames[i], authorSurnames[i], idAuthor);
        }
        for (int i = 0; i < genres.length; i++) {
            genreDAO.setEditGenre(idGenreConvert.get(i), IDlanguage, genres[i]);
        }

        dispatcher = request.getRequestDispatcher("jsp/information.jsp");
        dispatcher.forward(request, response);
    }

    private List<Integer> convertFromString(String[] idGenres){
        List<Integer> ID = new ArrayList<>();
        for (int i = 0; i < idGenres.length; i++) {
            int idAfterConvert = Integer.parseInt(idGenres[i]);
            ID.add(idAfterConvert);
        }
        return ID;
    }
}
