package com.epam.library.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;

import static com.epam.library.util.ConstantsOfLibrary.ENG;
import static com.epam.library.util.ConstantsOfLibrary.RU;

public class IssueBookService implements Service {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
       /* String language;
        int idOrder = Integer.parseInt(request.getParameter("ID"));
        int IDlanguage;
        HttpSession session = request.getSession(true);
        language = String.valueOf(session.getAttribute("language"));
        if(language.equals("RU") || language.equals("/") || language.isEmpty()){
            IDlanguage = RU;
        }
        else{
            IDlanguage = ENG;
        }*/

    }
}
