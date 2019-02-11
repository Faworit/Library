package com.epam.library.connectionPool;

import com.epam.library.entity.User;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.library.validator.AuthorizationValidator.validatePassword;

public class UserDAO {
    private static final Logger log = Logger.getLogger("UserDAO");
    public static final String GET_USER_BY_MAIL_PASSWORD = "SELECT ID_USER, PASSWORD, NAME, SURNAME, MAIL, TELEPHONE, BIRTH_DAY, BLOCK, ROLE_NAME FROM USER U, ROLE R WHERE MAIL=? AND PASSWORD=? AND U.ROLE=R.ID_ROLE";
    private ConnectionPool connectionPool;
    private Connection connection = null;

    public User getUserByMailPassword(String mail, String password){
        User user = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_MAIL_PASSWORD)){
            preparedStatement.setString(1, mail);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String passFromDB = resultSet.getString("password");
                if(validatePassword(password, passFromDB)) {
                    user = new User();
                    user = setParametrsToUser(user, resultSet);
                }
            }
        } catch (SQLException e) {
            log.error(e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
        return user;
    }

    private User setParametrsToUser(User user, ResultSet resultSet){
        try {
            user.setIDUser(resultSet.getInt("ID_User"));
            user.setPassword(resultSet.getString("password"));
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surName"));
            user.setMail(resultSet.getString("mail"));
            user.setTelephone(resultSet.getString("telephone"));
            user.setBirthDay(resultSet.getDate("birth_day"));
            user.setBlock(resultSet.getString("block"));
            user.setRole(resultSet.getString("role_name"));
        } catch (SQLException e) {
            log.warn(e);
            e.printStackTrace();
        }
        return user;
    }
}
