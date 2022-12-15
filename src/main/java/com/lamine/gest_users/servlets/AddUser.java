package com.lamine.gest_users.servlets;

import com.lamine.gest_users.forms.AddUserForm;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/add")
public class AddUser extends HttpServlet
{

    private static final String VUE_AJOUT_UTILISATEUR = "/WEB-INF/ajoutUtilisateur.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request
                .getServletContext()
                .getRequestDispatcher(VUE_AJOUT_UTILISATEUR)
                .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AddUserForm form = new AddUserForm(request);
        form.ajouter();

        request.setAttribute("utilisateur", form.getUtilisateur());
        request.setAttribute("status", form.getStatus());
        request.setAttribute("message", form.getMessage());
        request.setAttribute("erreurs", form.getErreurs());

        request.getServletContext()
                .getRequestDispatcher(VUE_AJOUT_UTILISATEUR)
                .forward(request, response);
    }

}

