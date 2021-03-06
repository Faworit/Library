package com.epam.library.serv;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

public interface Service {
    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException;
}
