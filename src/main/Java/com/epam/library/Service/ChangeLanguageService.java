package com.epam.library.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.library.util.ConstantsOfLibrary.ENGLISH;
import static com.epam.library.util.ConstantsOfLibrary.RUSSIAN;

public class ChangeLanguageService implements Service{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String jspName = request.getParameter("jspname");
        String language = request.getParameter("language");
        System.out.println(language);
        if(language.equalsIgnoreCase(ENGLISH)){
            session.setAttribute("language", ENGLISH);
        }
        else{
            session.setAttribute("language", RUSSIAN);
        }
        RequestDispatcher dispatcher;
        dispatcher = request.getRequestDispatcher("/" + jspName);
        dispatcher.forward(request, response);
    }
}
