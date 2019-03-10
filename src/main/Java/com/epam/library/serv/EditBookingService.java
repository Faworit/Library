package com.epam.library.serv;

import com.epam.library.dataBase.OrderDAO;
import com.epam.library.dataBase.StatusDAO;
import com.epam.library.entity.Order;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class EditBookingService implements Service {

    RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        int idOrder;
        int idStatus;
        String status = request.getParameter("status");
        Date todayDate = new Date();
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
            if(orderDate.after(todayDate)){
                session.setAttribute("error", "not correct date");
                dispatcher = request.getRequestDispatcher("jsp/orderProcessing.jsp");
                dispatcher.forward(request, response);
            } else{
                if(!request.getParameter("actuallyReturn").isEmpty()){
                    actuallyReturn = format.parse(request.getParameter("actuallyReturn"));
                    orderDAO.updateActuallyReturnBooking(actuallyReturn, idStatus, idOrder);
                } else{
                    orderDAO.updateBooking(orderDate, returnDate, idStatus, idOrder);
                }
            }

        } else {
            orderDAO.changeStatus(idStatus, idOrder);
        }
        showOrderService.execute(request, response);
    }
}
