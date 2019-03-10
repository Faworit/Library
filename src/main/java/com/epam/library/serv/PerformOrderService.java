package com.epam.library.service;

import com.epam.library.dataBase.LanguageDAO;
import com.epam.library.dataBase.OrderDAO;
import com.epam.library.dataBase.StatusDAO;
import com.epam.library.dataBase.UserDAO;
import com.epam.library.entity.Order;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class PerformOrderService implements Service {
    RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int idOrder = Integer.parseInt(request.getParameter("ID"));
        int idUser;
        int idLanguage;
        List<String> statuses;
        Order booking;
        UserDAO userDAO = new UserDAO();
        OrderDAO orderDAO = new OrderDAO();
        StatusDAO statusDAO = new StatusDAO();
        LanguageDAO languageDAO = new LanguageDAO();
        HttpSession session = request.getSession(true);
        idUser = (int) session.getAttribute("idUser");
        idLanguage = languageDAO.getIdLanguage(String.valueOf(session.getAttribute("language")));
        userDAO.insertPerformer(idOrder, idUser);
        booking = orderDAO.getOrderExecuting(idLanguage, idOrder);
        statuses = statusDAO.getStatuses(idLanguage);
        session.setAttribute("booking", booking);
        session.setAttribute("list", statuses);
        dispatcher = request.getRequestDispatcher("jsp/orderProcessing.jsp");
        dispatcher.forward(request, response);



    }
}
