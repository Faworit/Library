package com.epam.library.validator;

import com.epam.library.connectionPool.BookDAO;

public class BookValidator {
    public static boolean checkBook(String ISBN){
        boolean isAvailable;
        BookDAO bookDAO = new BookDAO();
        isAvailable = bookDAO.checkBook(ISBN);
        return isAvailable;
    }
}
