package com.epam.library.service;

import com.epam.library.dataBase.OrderDAO;
import com.epam.library.dataBase.StatusDAO;
import com.epam.library.entity.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditBookingService implements Service {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        int idOrder;
        int idStatus;
        String status = request.getParameter("status");
        Date orderDate;
        Date returnDate;
        Date actuallyReturn;
        Order order;
        OrderDAO orderDAO = new OrderDAO();
        StatusDAO statusDAO = new StatusDAO();
        ShowOrderService showOrderService = new ShowOrderService();
        HttpSession session = request.getSession(true);
        order = (Order) session.getAttribute("booking");
        idOrder = order.getOrderID();
        idStatus = statusDAO.getStatusID(status);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if(request.getParameter("orderDate")!=null && request.getParameter("returnDate")!=null){
            orderDate = format.parse(request.getParameter("orderDate"));
            returnDate = format.parse(request.getParameter("returnDate"));
            if(!request.getParameter("actuallyReturn").isEmpty()){
                actuallyReturn = format.parse(request.getParameter("actuallyReturn"));
                orderDAO.updateActuallyReturnBooking(actuallyReturn, idStatus, idOrder);
            } else{
                orderDAO.updateBooking(orderDate, returnDate, idStatus, idOrder);
            }
        } else {
            orderDAO.changeStatus(idStatus, idOrder);
        }
        showOrderService.execute(request, response);
    }
}
