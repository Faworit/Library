package com.epam.library.factory;

import com.epam.library.dataBase.AuthorDAO;
import com.epam.library.dataBase.GenreDAO;
import com.epam.library.entity.Autor;
import com.epam.library.entity.Book;
import com.epam.library.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BookFactory {

    public static Book createBook(ResultSet resultSet, int language) {
        List<Autor> autors;
        List<Genre> genres;
        Book book = null;
        AuthorDAO authorDAO = new AuthorDAO();
        try {
            int IDBook = resultSet.getInt("ID_BOOK");
            int languageID = resultSet.getInt("ID_LANGUAGE");
            String title = resultSet.getString("TITLE");
            book = new Book(title, languageID, IDBook);
            book.setISBN(resultSet.getString("ISBN"));
            book.setQuantity(resultSet.getInt("QUANTITY"));
            autors = authorDAO.getAutor(IDBook, language);
            book.setAutors(autors);
            GenreDAO genreDAO = new GenreDAO();
            genres = genreDAO.getGenre(IDBook, language);
            book.setGeners(genres);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }
}
