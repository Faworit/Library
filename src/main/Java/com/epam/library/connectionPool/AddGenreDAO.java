package com.epam.library.connectionPool;

import com.epam.library.entity.Genre;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddGenreDAO {
    public static final String ADD_GENRE = "INSERT INTO GENRE (ID_GENRE, ID_LANGUAGE, GENRE_NAME) VALUES (?, ?, ?)";
    private static final Logger log = Logger.getLogger("UserDAO");
    private ConnectionPool connectionPool;
    private Connection connection = null;

    public void addGenre(int idGenre, int idLanguage, String genreName){
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_GENRE)) {
            preparedStatement.setInt(1, idGenre);
            preparedStatement.setInt(2, idLanguage);
            preparedStatement.setString(3, genreName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }
}
