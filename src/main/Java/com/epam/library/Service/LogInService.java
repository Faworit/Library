package com.epam.library.Service;

import com.epam.library.connectionPool.UserDAO;
import com.epam.library.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.library.validator.Validator.*;

public class LogInService implements Service{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name;
        String role;
        boolean validateLoginRegex;
        boolean validatePasswordRegex;
        boolean isUser;
        User user;
        UserDAO userDAO = new UserDAO();
        validateLoginRegex = validateMailRegex(login);
        validatePasswordRegex = validatePasswordRegex(password);
        user = userDAO.getUserByMail(login);
        if(validateLoginRegex && validatePasswordRegex){
            isUser = validateUser(user, login, password);
            if(isUser) {
                HttpSession session = request.getSession(true);
                name = user.getName();
                role = user.getRole();
                session.setAttribute("role", role);
                dispatcher = request.getRequestDispatcher("jsp/user.jsp");
                session.setAttribute("name", name);
                dispatcher.forward(request, response);
            }
        }
        else{
            request.setAttribute("notCorrect", "not correct format login/password");
            dispatcher = request.getRequestDispatcher("/authorization.jsp");
            dispatcher.forward(request, response);
        }
    }
}
