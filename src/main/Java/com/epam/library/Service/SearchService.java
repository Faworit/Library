package com.epam.library.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchService implements Service {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestSearch = request.getParameter("search");
        List<String> wordsSearch;
    }

    private List<String> parseRequestSearch(String requestSearch){
        String[] wordsArray = requestSearch.split(" ");
        ArrayList<String> wordsSearch = new ArrayList<>(Arrays.asList(wordsArray));

        return wordsSearch;
    }
}
