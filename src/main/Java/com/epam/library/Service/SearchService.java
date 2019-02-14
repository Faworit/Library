package com.epam.library.Service;

import com.epam.library.connectionPool.BookDAO;
import com.epam.library.entity.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.epam.library.util.ConstantsOfLibrary.ENG;
import static com.epam.library.util.ConstantsOfLibrary.RU;

public class SearchService implements Service {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        String requestDB;
        String language;
        int countWordsSearch;
        List<Book> books;
        BookDAO bookDAO = new BookDAO();
        HttpSession session = request.getSession(true);
        language = String.valueOf(session.getAttribute("language"));
        int IDlanguage;
        if(language.equals("RU") || language.equals("/")){
            IDlanguage = RU;
        }
        else{
            IDlanguage = ENG;
        }
        String requestSearch = request.getParameter("search");
        List<String> wordsSearch = parseRequestSearch(requestSearch);
        countWordsSearch = wordsSearch.size();
        requestDB = createRequest(countWordsSearch);
        books = bookDAO.searchBook(wordsSearch, requestDB, IDlanguage);
        session.setAttribute("list", books);
        dispatcher = request.getRequestDispatcher("jsp/user.jsp");
        dispatcher.forward(request, response);

    }

    private List<String> parseRequestSearch(String requestSearch){
        String afterRemovePunctuation = requestSearch.replaceAll("[^а-яА-Яa-zA-Z]", " ").replaceAll("\\s+", " ");
        String[] wordsArray = afterRemovePunctuation.split(" ");
        ArrayList<String> wordsSearch = new ArrayList<>(Arrays.asList(wordsArray));
        return wordsSearch;
    }

    public String createRequest(int countWordsSearch){
        String firstPartOfRequest = "select title from book where (title like \"%\" + ? +\"%\" )"; //58
        String partToInsert = " or title like \"%\"+?+\"%\"";
        String requestToDB;
        if(countWordsSearch>1) {
            StringBuilder stringBuilder = new StringBuilder(firstPartOfRequest);
            for (int i = 0; i < countWordsSearch-1; i++) {
                stringBuilder.insert(54, partToInsert);
            }
            requestToDB = stringBuilder.toString();
        }
        else{
            requestToDB = firstPartOfRequest;
        }
        return requestToDB;
    }
}
