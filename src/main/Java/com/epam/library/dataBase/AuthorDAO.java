package com.epam.library.dataBase;

import com.epam.library.entity.Autor;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {
    public static final String EDIT_AUTHOR = "UPDATE AUTHOR SET SURNAME=?, NAME=? WHERE ID_AUTHOR=?";
    public static final String GET_AUTOR = "SELECT A.ID_AUTOR, A.SURNAME, A.NAME FROM BOOK B, AUTOR A, BOOK2AUTOR BA " +
            "WHERE B.ID_BOOK=BA.ID_BOOK AND A.ID_AUTOR=BA.ID_AUTOR AND B.ID_BOOK=? AND B.ID_LANGUAGE=?;";
    private static final Logger log = Logger.getLogger("UserDAO");
    private ConnectionPool connectionPool;
    private Connection connection = null;

    public List<Autor> getAutor(int IDBook, int language) {
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
