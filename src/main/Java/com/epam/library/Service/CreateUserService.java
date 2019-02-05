package com.epam.library.Service;

import com.epam.library.connectionPool.CreateUserDAO;
import com.epam.library.validator.AuthorizationValidator;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateUserService implements Service {
    private static final Logger log = Logger.getLogger("UserDAO");
    RequestDispatcher dispatcher;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isCorrectMail;
        Date birthday = null;
        int ID = Integer.parseInt(request.getParameter("ID"));
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        DateFormat format = new SimpleDateFormat("yyyy, MM, dd");
        CreateUserDAO createUserDAO = new CreateUserDAO();
        try {
            birthday = format.parse(request.getParameter("birthday"));
        } catch (ParseException e) {
            log.error(e);
        }
        String mail = request.getParameter("mail");
        String telephone = request.getParameter("phone");
        String block = request.getParameter("block");
        String role = request.getParameter("role");
        isCorrectMail = AuthorizationValidator.validateMailRegex(mail);
        if(!isCorrectMail){
            RequestDispatcher dispatcher;
            request.setAttribute("notCorrectMail", "not correct format of mail");
            dispatcher = request.getRequestDispatcher("jsp/setUser.jsp");
            dispatcher.forward(request, response);
        }
        createUserDAO.setUser(ID, password, name, surname,  mail, telephone, birthday, block, role);
        request.setAttribute("information", "new User created successfully");
        dispatcher = request.getRequestDispatcher("/information.jsp");
        dispatcher.forward(request, response);
    }
}
