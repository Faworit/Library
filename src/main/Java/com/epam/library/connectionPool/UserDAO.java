package com.epam.library.connectionPool;

import com.epam.library.entity.User;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static com.epam.library.util.ConstantDAO.*;

public class UserDAO {
    private static final Logger log = Logger.getLogger("UserDAO");
    private ConnectionPool connectionPool;
    private Connection connection = null;

    public User getUserByMail(String mail){
        User user = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_MAIL)){
            preparedStatement.setString(1, mail);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                user = new User();
                user = setParametrsToUser(user, resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    private User setParametrsToUser(User user, ResultSet resultSet){
        try {
            user.setIDUser(resultSet.getInt("IDUser"));
            user.setPassword(resultSet.getString("password"));
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surName"));
            user.setMail(resultSet.getString("mail"));
            user.setTelephone(resultSet.getString("telephone"));
            user.setBirthDay(resultSet.getDate("birthday"));
            user.setBlock(resultSet.getString("block"));
            user.setRole(resultSet.getString("role"));
        } catch (SQLException e) {
            log.warn(e);
            e.printStackTrace();
        }
        return user;
    }
}
