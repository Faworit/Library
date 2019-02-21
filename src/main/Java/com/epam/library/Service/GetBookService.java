package com.epam.library.Service;

import com.epam.library.dataBase.BookDAO;
import com.epam.library.entity.Book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.epam.library.util.ConstantsOfLibrary.ENG;
import static com.epam.library.util.ConstantsOfLibrary.RU;

public class GetBookService {
     public static Book getBookFromDB(HttpServletRequest request, HttpServletResponse response){
         String language;
         Book book;
         BookDAO bookDAO = new BookDAO();
         HttpSession session = request.getSession(true);
         int idBook = Integer.parseInt(request.getParameter("ID"));
         language = String.valueOf(session.getAttribute("language"));
         int IDlanguage;
         if(language.equals("RU") || language.equals("/") || language.isEmpty()){
             IDlanguage = RU;
         }
         else{
             IDlanguage = ENG;
         }
         book = bookDAO.getBookByID(idBook, IDlanguage);
         return book;
     }
}
