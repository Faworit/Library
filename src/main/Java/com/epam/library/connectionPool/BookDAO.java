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
    public static final String GET_FROM_BOOK_TABLE = "SELECT ID_BOOK, ID_LANGUAGE, TITLE, ISBN, QUANTITY " +
            "FROM BOOK WHERE ID_LANGUAGE =?";
    public static final String GET_AUTOR = "SELECT A.ID_AUTOR, A.SURNAME, A.NAME FROM BOOK B, AUTOR A, BOOK2AUTOR BA " +
            "WHERE B.ID_BOOK=BA.ID_BOOK AND A.ID_AUTOR=BA.ID_AUTOR AND B.ID_BOOK=? AND B.ID_LANGUAGE=?;";
    public static final String ADD_BOOK = "INSERT INTO BOOK (ID_BOOK, ID_LANGUAGE, TITLE, ISBN, QUANTITY) " +
            "VALUES (?, ?, ?, ?, ?)";
    public static final String CHECK_BOOK_BY_ISBN = "SELECT ISBN FROM BOOK WHERE ISBN=?";
    public static final String GET_BOOK_BY_ID = "SELECT ID_BOOK, ID_LANGUAGE, TITLE, ISBN, QUANTITY FROM BOOK WHERE " +
            "ID_LANGUAGE=? AND ID_BOOK=?";
    public static final String EDIT_BOOK = "UPDATE book SET TITLE = ?, ISBN = ?, QUANTITY = ? WHERE (ID_BOOK = ?) " +
            "and (ID_LANGUAGE = ?)";
    private final String REMOVE_BOOK_BY_ID = "DELETE FROM book WHERE (ID_BOOK = ?)";
    private static final Logger log = Logger.getLogger("UserDAO");
    private ConnectionPool connectionPool;
    private Connection connection = null;

    public List<Book> getBook(int language) {
        List<Book> books = new ArrayList<>();
        Book book;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_FROM_BOOK_TABLE)) {
            preparedStatement.setInt(1, language);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                book = createBook(resultSet, language);
                books.add(book);
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }

        return books;
    }

    public Book getBookByID(int idBook, int language){
        Book book = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BOOK_BY_ID)) {
            preparedStatement.setInt(1, language);
            preparedStatement.setInt(2, idBook);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                book = createBook(resultSet, language);
                }
            }
        catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return book;
    }

    private Book createBook(ResultSet resultSet, int language) {
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
            GenreDAO genreDAO = new GenreDAO();
            genres = genreDAO.getGenre(IDBook, language);
            book.setGeners(genres);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    private List<Autor> getAutor(int IDBook, int language) {
        int IDAutor;
        String surName;
        String name;
        List<Autor> autors = new ArrayList<>();
        Autor autor;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_AUTOR)) {
            preparedStatement.setInt(1, IDBook);
            preparedStatement.setInt(2, language);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                IDAutor = resultSet.getInt("ID_AUTOR");
                surName = resultSet.getString("surname");
                name = resultSet.getString("name");
                autor = new Autor(IDAutor, name, surName);
                autors.add(autor);
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return autors;
    }

    public void addBook(int idBook, int idLanguage, String ISBN, int quantity, String title) {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_BOOK)) {
            preparedStatement.setInt(1, idBook);
            preparedStatement.setInt(2, idLanguage);
            preparedStatement.setString(3, title);
            preparedStatement.setString(4, ISBN);
            preparedStatement.setInt(5, quantity);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    public boolean checkBook(String ISBN) {
        boolean isAvailable = false;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CHECK_BOOK_BY_ISBN)) {
            preparedStatement.setString(1, ISBN);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                isAvailable = true;
            }
        } catch (SQLException e) {
            log.error(e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
        return isAvailable;
    }

    public void editBook(String title, String ISBN, int quantity, int idBook, int idLanguage){
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(EDIT_BOOK)) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, ISBN);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setInt(4, idBook);
            preparedStatement.setInt(5, idLanguage);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    public void removeBook(int bookID) {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_BOOK_BY_ID)) {
            preparedStatement.setInt(1, bookID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    public List<Book> searchBook(List<String> wordsToSearch, String requestToDB, int language) {
        List<Book> books = new ArrayList<>();
        Book book;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(requestToDB)) {
            for(int i=0; i<wordsToSearch.size(); i++) {
                int index = i+1;
                String word = wordsToSearch.get(i);
                preparedStatement.setString(index, word);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                book = createBook(resultSet, language);
                books.add(book);
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return books;
    }
}
