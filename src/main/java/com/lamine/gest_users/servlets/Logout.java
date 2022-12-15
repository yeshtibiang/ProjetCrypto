package com.lamine.gest_users.servlets;

import com.lamine.gest_users.dao.UtilisateurDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //destroy the session
        request.getSession().invalidate();
        //redirect to the login page
        response.sendRedirect("login");
    }
}
