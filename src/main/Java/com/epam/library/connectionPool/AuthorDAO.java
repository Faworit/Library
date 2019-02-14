package com.epam.library.connectionPool;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorDAO {
    public static final String EDIT_AUTHOR = "UPDATE AUTHOR SET SURNAME=?, NAME=? WHERE ID_AUTHOR=?";
    private static final Logger log = Logger.getLogger("UserDAO");
    private ConnectionPool connectionPool;
    private Connection connection = null;

    public void editAuthor(String name, String surname, int idAuthor){
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(EDIT_AUTHOR)) {
            preparedStatement.setString(1, surname);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, idAuthor);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }
}
