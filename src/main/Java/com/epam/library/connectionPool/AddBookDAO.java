package com.epam.library.connectionPool;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddBookDAO {
    public static final String ADD_BOOK = "INSERT INTO book (ID_BOOK, ID_LANGUAGE, TITLE, ISBN, QUANTITY) VALUES (?, ?, ?, ?, ?)";
    private static final Logger log = Logger.getLogger("UserDAO");
    private ConnectionPool connectionPool;
    private Connection connection = null;

    public void addBook(int IDBook, String title, String ISBN, int quantity){
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_BOOK)){
            preparedStatement.setInt(1, IDBook);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, ISBN);
            preparedStatement.setInt(4, quantity);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }
}
