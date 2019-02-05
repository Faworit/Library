package com.epam.library.connectionPool;

import com.epam.library.entity.Autor;
import com.epam.library.entity.Book;
import com.epam.library.entity.Genre;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    public static final String GET_FROM_BOOK_TABLE = "SELECT ID_BOOK, ID_LANGUAGE, TITLE, ISBN, NUMBER, QUANTITY FROM BOOK WHERE ID_LANGUAGE =?";
    public static final String GET_AUTOR = "SELECT A.ID_AUTOR, A.SURNAME, A.NAME FROM BOOK B, AUTOR A, BOOK2AUTOR BA WHERE B.ID_BOOK=BA.ID_BOOK AND A.ID_AUTOR=BA.ID_AUTOR AND B.ID_BOOK=? AND B.ID_LANGUAGE=?;";
    public static final String GET_GENRE = "SELECT G.ID_GENRE, G.ID_LANGUAGE, G.GENRE_NAME FROM BOOK B, GENRE G, BOOK2GENRE BG WHERE B.ID_BOOK = BG.ID_BOOK AND G.ID_GENRE = BG.ID_GENRE AND B.ID_LANGUAGE = BG.ID_LANGUAGE AND G.ID_LANGUAGE=BG.ID_LANGUAGE AND G.ID_GENRE = BG.ID_GENRE AND B.ID_BOOK=? AND B.ID_LANGUAGE=?;";
    private static final Logger log = Logger.getLogger("UserDAO");
    private ConnectionPool connectionPool;
    private Connection connection = null;

    public List<Book> getBook(int language) {
        String ISBN;
        int number;
        List<Book> books = new ArrayList<>();
        Book book;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_FROM_BOOK_TABLE)) {
            preparedStatement.setInt(1, language);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                number = resultSet.getInt("number");
                ISBN = resultSet.getString("ISBN");
                if(number!=0){

                } else {
                   book = createBook(resultSet, language);
                   books.add(book);
                }
            }
        } catch (SQLException e) {
            log.error(e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }

        return books;
    }

    private Book createBook(ResultSet resultSet, int language){
        List<Autor> autors;
        List<Genre> genres;
        Book book = null;
        try {
            int IDBook = resultSet.getInt("ID_BOOK");
            int languageID = resultSet.getInt("ID_LANGUAGE");
            String title = resultSet.getString("TITLE");
            book = new Book(title, languageID, IDBook);
            book.setISBN(resultSet.getString("ISBN"));
            book.setQuantity(resultSet.getInt("QUANTITY"));
            autors = getAutor(IDBook, language);
            book.setAutors(autors);
            genres = getGener(IDBook, language);
            book.setGeners(genres);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    private List<Autor> getAutor(int IDBook, int language){
        int IDAutor;
        String surName;
        String name;
        List<Autor> autors = new ArrayList<>();
        Autor autor;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_AUTOR)){
            preparedStatement.setInt(1, IDBook);
            preparedStatement.setInt(2, language);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                IDAutor = resultSet.getInt("ID_AUTOR");
                surName = resultSet.getString("surname");
                name = resultSet.getString("name");
                autor = new Autor(IDAutor, name, surName);
                autors.add(autor);
            }
        } catch (SQLException e) {
           log.error(e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
        return autors;
    }

    private List<Genre> getGener(int IDBook, int language){
        int IDGenre;
        int IDLanguage;
        String genreName;
        List<Genre> geners = new ArrayList<>();
        Genre genre;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_GENRE)){
            preparedStatement.setInt(1, IDBook);
            preparedStatement.setInt(2, language);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                IDGenre = resultSet.getInt("ID_GENRE");
                IDLanguage = resultSet.getInt("ID_LANGUAGE");
                genreName = resultSet.getString("GENRE_NAME");
                genre = new Genre(IDGenre, IDLanguage, genreName);
                geners.add(genre);
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return geners;
    }
}
