package com.epam.library.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.library.util.ConstantsOfLibrary.ENGLISH;
import static com.epam.library.util.ConstantsOfLibrary.RUSSIAN;

public class ChangeLanguageService implements Service{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jspname = request.getParameter("jspname");
        String language = request.getParameter("language");
        System.out.println(language);
        if(language.equalsIgnoreCase(ENGLISH)){
            request.setAttribute("language", ENGLISH);
        }
        else{
            request.setAttribute("language", RUSSIAN);
        }
        RequestDispatcher dispatcher;
        dispatcher = request.getRequestDispatcher("/" + jspname);
        dispatcher.forward(request, response);
    }
}
