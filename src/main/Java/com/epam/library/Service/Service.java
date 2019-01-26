package com.epam.library.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Service {
    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
