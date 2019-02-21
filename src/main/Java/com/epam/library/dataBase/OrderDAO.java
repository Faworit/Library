package com.epam.library.dataBase;

import com.epam.library.factory.OrderFactory;
import com.epam.library.entity.Order;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    public static final String  GET_BOOKING_FOR_USER = "SELECT BG.ID_ORDER, B.TITLE, BG.ORDER_DATE, BG.RETURN_DATE, " +
            "BG.ACTUALLY_RETURNED, S.NAME FROM  BOOK B, BOOKING BG, ORDER2USER O2U, STATE S WHERE  " +
            "BG.ID_STATE=S.ID_STATE AND BG.ID_ORDER=O2U.ID_ORDER AND BG.ID_BOOK=B.ID_BOOK AND " +
            "S.ID_LANGUAGE=B.ID_LANGUAGE AND B.ID_LANGUAGE=? AND O2U.ID_USER=? ORDER BY ORDER_DATE DESC";
    public static final String GET_ALL_BOOKINGS = "SELECT DISTINCT BG.ID_ORDER, B.TITLE, BG.ORDER_DATE, BG.RETURN_DATE, " +
            "BG.ACTUALLY_RETURNED, S.NAME FROM  BOOK B, BOOKING BG, ORDER2USER O2U, STATE S WHERE  " +
            "BG.ID_STATE=S.ID_STATE AND BG.ID_ORDER=O2U.ID_ORDER AND BG.ID_BOOK=B.ID_BOOK AND " +
            "S.ID_LANGUAGE=B.ID_LANGUAGE AND B.ID_LANGUAGE=? ORDER BY ORDER_DATE DESC";
    public static final String GET_ORDER_FOR_EXECUTION = "SELECT DISTINCT BG.ID_ORDER, B.TITLE, BG.ORDER_DATE, BG.RETURN_DATE, BG.ACTUALLY_RETURNED, S.NAME FROM  BOOK B, BOOKING BG, ORDER2USER O2U, STATE S WHERE BG.ID_STATE=S.ID_STATE AND BG.ID_ORDER=O2U.ID_ORDER AND BG.ID_BOOK=B.ID_BOOK AND S.ID_LANGUAGE=B.ID_LANGUAGE AND B.ID_LANGUAGE=? and BG.ID_ORDER=?";
    public static final String CREATE_BOOKING = "INSERT INTO BOOKING (ID_BOOK, ID_STATE) VALUES (?, 1)";
    public static final String INSERT_INTO_ORDER2USER = "INSERT INTO ORDER2USER (ID_ORDER, ID_USER) VALUES (?, ?)";
    private static final Logger log = Logger.getLogger("OrderDAO");
    private ConnectionPool connectionPool;
    private Connection connection = null;

    public Order getOrderExecuting(int idLanguage, int idOrder){
        Order order = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_FOR_EXECUTION)) {
            preparedStatement.setInt(1, idLanguage);
            preparedStatement.setInt(2, idOrder);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                order = OrderFactory.collectOrder(resultSet);
            }
        } catch (SQLException e) {
            log.error(e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }

        return order;
    }

    public List<Order> getPersonalOrders(int idLanguage, int idUser){
        List<Order> orders = new ArrayList<>();
        Order order;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BOOKING_FOR_USER)) {
            preparedStatement.setInt(1, idLanguage);
            preparedStatement.setInt(2, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                order = OrderFactory.collectOrder(resultSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            log.error(e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
        return orders;
    }

    public List<Order> getAllOrders(int idLanguage){
        List<Order> orders = new ArrayList<>();
        Order order;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_BOOKINGS)) {
            preparedStatement.setInt(1, idLanguage);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                order = OrderFactory.collectOrder(resultSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            log.error(e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
        return orders;
    }

    public void createBooking(int idBook, int idUser){
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        int idOrder=0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_BOOKING, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, idBook);
            preparedStatement.executeUpdate();
            idOrder= getGeneratedKey(preparedStatement);

        } catch (SQLException e) {
            log.error(e);
        }
        if(idOrder>0) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_ORDER2USER)) {
                preparedStatement.setInt(1, idOrder);
                preparedStatement.setInt(2, idUser);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                log.error(e);
            }
        }
    }
    private int getGeneratedKey(PreparedStatement preparedStatement) throws SQLException {
        int id=0;
        ResultSet gk = preparedStatement.getGeneratedKeys();
        if(gk.next()){
            id = gk.getInt(1);

        }
        return id;

    }
}
