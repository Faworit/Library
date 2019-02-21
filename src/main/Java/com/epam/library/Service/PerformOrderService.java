package com.epam.library.Service;

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
import java.text.ParseException;
import java.util.List;

import static com.epam.library.util.ConstantsOfLibrary.ENG;
import static com.epam.library.util.ConstantsOfLibrary.RU;

public class PerformOrderService implements Service {
    RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        int idOrder = Integer.parseInt(request.getParameter("ID"));
        int idUser;
        int idLanguage;
        String language;
        List<String> statuses;
        Order booking;
        UserDAO userDAO = new UserDAO();
        OrderDAO orderDAO = new OrderDAO();
        StatusDAO statusDAO = new StatusDAO();
        HttpSession session = request.getSession(true);
        language = String.valueOf(session.getAttribute("language"));
        idUser = (int) session.getAttribute("idUser");
        if(language.equals("RU") || language.equals("/") || language.isEmpty()){
            idLanguage = RU;
        }
        else{
            idLanguage = ENG;
        }
        userDAO.insertPerformer(idOrder, idUser);
        booking = orderDAO.getOrderExecuting(idLanguage, idOrder);
        statuses = statusDAO.getStatuses(idLanguage);
        session.setAttribute("booking", booking);
        session.setAttribute("list", statuses);
        dispatcher = request.getRequestDispatcher("jsp/orderProcessing.jsp");
        dispatcher.forward(request, response);



    }
}
