package com.epam.library.serv;

import com.epam.library.dataBase.BookDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveBookService implements Service {
    RequestDispatcher dispatcher;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idBook = Integer.parseInt(request.getParameter("ID"));
        BookDAO bookDAO = new BookDAO();
        bookDAO.editByID(idBook, BookDAO.REMOVE_BOOK_BY_ID);
        ShowBookService showBookService = new ShowBookService();
        showBookService.execute(request, response);
    }
}
