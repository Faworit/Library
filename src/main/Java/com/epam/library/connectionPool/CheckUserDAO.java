package com.epam.library.connectionPool;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckUserDAO {
    public static final String CHECK_USER_BY_MAIL_ID = "SELECT ID_USER, MAIL FROM USER WHERE MAIL=? OR ID_USER=?";
    private static final Logger log = Logger.getLogger("UserDAO");
    private ConnectionPool connectionPool;
    private Connection connection = null;

    public boolean checkUser(String mail, int ID){
        boolean isUser = false;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USER_BY_MAIL_ID)){
            preparedStatement.setString(1, mail);
            preparedStatement.setInt(2, ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                isUser = true;
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return isUser;
    }
}
