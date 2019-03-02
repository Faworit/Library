package com.epam.library.Service;

import com.epam.library.dataBase.BookDAO;
import com.epam.library.dataBase.BookGenreDAO;
import com.epam.library.validator.BookValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddBookService implements Service {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        boolean isAvailableBook;
        int IDBook = Integer.parseInt(request.getParameter("IDBook"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String titleRU = request.getParameter("titleRU");
        String titleENG = request.getParameter("titleENG");
        String ISBN = request.getParameter("ISBN");
        String genre = request.getParameter("genre");
        BookDAO bookDAO = new BookDAO();
        BookGenreDAO bookGenreDAO = new BookGenreDAO();
        List<String> genres = parseRequest(genre);
        isAvailableBook = BookValidator.checkBook(ISBN);
        if(isAvailableBook){
            request.setAttribute("already exists", "This book is already exists");
            dispatcher = request.getRequestDispatcher("jsp/creatingBook.jsp");
            dispatcher.forward(request, response);
        } else{
            bookDAO.addBook(IDBook, 1, ISBN, quantity, titleRU);
            bookDAO.addBook(IDBook, 2, ISBN, quantity, titleENG);
            bookGenreDAO.addData(IDBook,1,genres);
            bookGenreDAO.addData(IDBook,2,genres);
            request.setAttribute("information", "new book added successfully");
            dispatcher = request.getRequestDispatcher("jsp/information.jsp");
            dispatcher.forward(request, response);
        }
    }

    private List<String> parseRequest(String genre){
        List<String> genres;
        String[] words = genre.replaceAll("[^а-яА-Яa-zA-Z]", " ").replaceAll("\\s+", " ").split("\\s");
        genres = new ArrayList<>(Arrays.asList(words));
        return genres;
    }
}
