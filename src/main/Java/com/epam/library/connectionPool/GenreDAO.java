package com.epam.library.connectionPool;

import com.epam.library.entity.Genre;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO {
    public static final String GET_GENRE = "SELECT G.ID_GENRE, G.ID_LANGUAGE, G.GENRE_NAME FROM BOOK B, GENRE G, BOOK2GENRE BG WHERE B.ID_BOOK = BG.ID_BOOK AND G.ID_GENRE = BG.ID_GENRE AND B.ID_LANGUAGE = BG.ID_LANGUAGE AND G.ID_LANGUAGE=BG.ID_LANGUAGE AND G.ID_GENRE = BG.ID_GENRE AND B.ID_BOOK=? AND B.ID_LANGUAGE=?";
    public static final String GET_ALL_GENRES = "SELECT * FROM GENRE WHERE ID_LANGUAGE=?";
    private static final Logger log = Logger.getLogger("UserDAO");
    private ConnectionPool connectionPool;
    private Connection connection = null;

    public List<Genre> getGenre(int IDBook, int language) {
        int IDGenre;
        int IDLanguage;
        String genreName;
        List<Genre> geners = new ArrayList<>();
        Genre genre;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_GENRE)) {
            preparedStatement.setInt(1, IDBook);
            preparedStatement.setInt(2, language);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                IDGenre = resultSet.getInt("ID_GENRE");
                IDLanguage = resultSet.getInt("ID_LANGUAGE");
                genreName = resultSet.getString("GENRE_NAME");
                genre = new Genre(IDGenre, IDLanguage, genreName);
                geners.add(genre);
            }
        } catch (SQLException e) {
            log.error(e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
        return geners;
    }

    public List<Genre> getAllGenres(int language) {
        int idGenre;
        int idLanguage;
        String genreName;
        List<Genre> genres = new ArrayList<>();
        Genre genre;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_GENRES)) {
            preparedStatement.setInt(1, language);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                idGenre = resultSet.getInt("ID_GENRE");
                idLanguage = resultSet.getInt("ID_LANGUAGE");
                genreName = resultSet.getString("GENRE_NAME");
                genre = new Genre(idGenre, idLanguage, genreName);
                genres.add(genre);
            }
        } catch (SQLException e) {
            log.error(e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
        return genres;
    }
}
