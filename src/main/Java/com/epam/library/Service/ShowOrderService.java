package com.epam.library.Service;

import com.epam.library.dataBase.OrderDAO;
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

public class ShowOrderService implements Service {
    RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        int idLanguage;
        int idUser;
        String role;
        String language;
        HttpSession session = request.getSession(true);
        idUser = (int) session.getAttribute("idUser");
        role = (String) session.getAttribute("role");
        language = String.valueOf(session.getAttribute("language"));
        List<Order> bookings;
        OrderDAO orderDAO = new OrderDAO();
        if(language.equals("RU") || language.equals("/")){
            idLanguage = RU;
        }
        else{
            idLanguage = ENG;
        }
        if(role.equals("reader")){
            bookings = orderDAO.getPersonalOrders(idLanguage, idUser);
            session.setAttribute("list", bookings);
            dispatcher = request.getRequestDispatcher("jsp/orders.jsp");
            dispatcher.forward(request, response);
        }
        if(role.equals("librarian")){

        }


    }
}
