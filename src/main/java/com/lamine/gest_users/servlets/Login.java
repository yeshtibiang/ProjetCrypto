package com.lamine.gest_users.servlets;

import com.lamine.gest_users.forms.LoginForm;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {
    private static final String VUE_LOGIN_UTILISATEUR = "/connecterUtilisateur.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(VUE_LOGIN_UTILISATEUR)
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginForm form = new LoginForm(request);

        request.setAttribute("status", form.getStatus());
        request.setAttribute("message", form.getMessage());
        request.setAttribute("erreurs", form.getErreurs());
        request.setAttribute("utilisateur", form.getUtilisateur());

        if (form.connecter()) {
            response.sendRedirect("list");
        } else {
            getServletContext()
                    .getRequestDispatcher(VUE_LOGIN_UTILISATEUR)
                    .forward(request, response);
        }
    }
}
