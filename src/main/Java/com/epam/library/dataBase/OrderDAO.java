package com.epam.library.dataBase;

import com.epam.library.factory.OrderFactory;
import com.epam.library.entity.Order;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
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
    public static final String GET_BOOKINGS_BY_STATUS = "SELECT DISTINCT BG.ID_ORDER, B.TITLE, BG.ORDER_DATE, BG.RETURN_DATE, " +
            "BG.ACTUALLY_RETURNED, S.NAME FROM  BOOK B, BOOKING BG, ORDER2USER O2U, STATE S WHERE  " +
            "BG.ID_STATE=S.ID_STATE AND BG.ID_ORDER=O2U.ID_ORDER AND BG.ID_BOOK=B.ID_BOOK AND " +
            "S.ID_LANGUAGE=B.ID_LANGUAGE AND B.ID_LANGUAGE=? AND BG.ID_STATE=?  ORDER BY ORDER_DATE DESC";
    public static final String GET_ORDER_FOR_EXECUTION = "SELECT DISTINCT BG.ID_ORDER, B.TITLE, BG.ORDER_DATE, BG.RETURN_DATE, BG.ACTUALLY_RETURNED, S.NAME FROM  BOOK B, BOOKING BG, ORDER2USER O2U, STATE S WHERE BG.ID_STATE=S.ID_STATE AND BG.ID_ORDER=O2U.ID_ORDER AND BG.ID_BOOK=B.ID_BOOK AND S.ID_LANGUAGE=B.ID_LANGUAGE AND B.ID_LANGUAGE=? and BG.ID_ORDER=? ";
    public static final String CREATE_BOOKING = "INSERT INTO BOOKING (ID_BOOK, ID_STATE) VALUES (?, 1)";
    public static final String INSERT_INTO_ORDER2USER = "INSERT INTO ORDER2USER (ID_ORDER, ID_USER) VALUES (?, ?)";
    public static final String UPDATE_BOOKING = "UPDATE BOOKING SET ORDER_DATE = ?, RETURN_DATE = ?, ID_STATE = ? WHERE (ID_ORDER = ?) ";
    public static final String UPDATE_ACTUALLY_RETURN_DATE = "UPDATE BOOKING SET ACTUALLY_RETURNED=?, ID_STATE = ? WHERE ID_ORDER=?";
    public static final String CHANGE_STATUS = "UPDATE BOOKING SET ID_STATE = ? WHERE ID_ORDER=?";
    private static final Logger log = Logger.getLogger("OrderDAO");
    private ConnectionPool connectionPool;
    private Connection connection = null;

    public List<Order> getNewOrders(int idLanguage, int idStatus){
        List<Order> orders = new ArrayList<>();
        Order order;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BOOKINGS_BY_STATUS)) {
            preparedStatement.setInt(1, idLanguage);
            preparedStatement.setInt(2, idStatus);
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

    public void updateBooking(Date orderDate, Date returnDate, int status, int idOrder){
        java.sql.Date sqlFormatOrderDate = convertDate(orderDate);
        java.sql.Date sqlFormatReturnDate = convertDate(returnDate);
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOKING)){
            preparedStatement.setDate(1, sqlFormatOrderDate);
            preparedStatement.setDate(2, sqlFormatReturnDate);
            preparedStatement.setInt(3, status);
            preparedStatement.setInt(4, idOrder);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    public void changeStatus(int status, int idOrder){
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_STATUS)){
            preparedStatement.setInt(1, status);
            preparedStatement.setInt(2, idOrder);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    public void updateActuallyReturnBooking(Date actuallyReturn, int idStatus, int idOrder){
        java.sql.Date sqlFormatActuallyReturnDate = convertDate(actuallyReturn);
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ACTUALLY_RETURN_DATE)){
            preparedStatement.setDate(1, sqlFormatActuallyReturnDate);
            preparedStatement.setInt(2, idStatus);
            preparedStatement.setInt(3, idOrder);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connectionPool.returnConnection(connection);
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

    private java.sql.Date convertDate(Date date){
        java.util.Date utilStartDate = date;
        java.sql.Date sqlFormatDate = new java.sql.Date(utilStartDate.getTime());
        return sqlFormatDate;
    }
}
