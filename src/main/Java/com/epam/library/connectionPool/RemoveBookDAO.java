package com.epam.library.connectionPool;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RemoveBookDAO {
    private final Logger log = Logger.getLogger("RemoveBookDAO");
    private final String REMOVE_BOOK_BY_ID = "DELETE FROM BOOK WHERE ID_BOOK=?";
    private ConnectionPool connectionPool;
    private Connection connection = null;

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
}