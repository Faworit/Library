package com.epam.library.Service;

import com.epam.library.dataBase.OrderDAO;
import com.epam.library.dataBase.StatusDAO;
import com.epam.library.entity.Order;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import static com.epam.library.util.ConstantsOfLibrary.ENG;
import static com.epam.library.util.ConstantsOfLibrary.RU;

public class ShowBookingByStatusService implements Service {
    RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        int idLanguage;
        int idStatus;
        String language;
        String status = request.getParameter("status");
        List<Order> bookings;
        HttpSession session = request.getSession(true);
        OrderDAO orderDAO = new OrderDAO();
        StatusDAO statusDAO = new StatusDAO();
        language = String.valueOf(session.getAttribute("language"));
        if(language.equals("RU") || language.equals("/")){
            idLanguage = RU;
        }
        else{
            idLanguage = ENG;
        }
        idStatus = statusDAO.getStatusID(status);
        bookings = orderDAO.getNewOrders(idLanguage, idStatus);
        session.setAttribute("list", bookings);
        dispatcher = request.getRequestDispatcher("jsp/orders.jsp");
        dispatcher.forward(request, response);
    }
}
