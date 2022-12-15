package com.lamine.gest_users.servlets;

import com.lamine.gest_users.beans.Utilisateur;
import com.lamine.gest_users.dao.UtilisateurDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/update")
public class UpdateUser extends HttpServlet
{

    private static final String VUE_UPDATE_UTILISATEUR = "/WEB-INF/modifierUtilisateur.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id != null && id.matches("[0-9]+"))
        {
            Utilisateur utilisateur = UtilisateurDao.get(Integer.parseInt(id));

            if (utilisateur != null)
            {
                request.setAttribute("utilisateur", utilisateur);
                request
                        .getServletContext()
                        .getRequestDispatcher(VUE_UPDATE_UTILISATEUR)
                        .forward(request, response);
                return;
            }
        }

        response.sendRedirect(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String id = request.getParameter("id");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (id != null && id.matches("[0-9]+"))
        {
            Utilisateur utilisateur = new Utilisateur(Integer.parseInt(id), nom, prenom, login, password);
            UtilisateurDao.modifier(utilisateur);
        }
        response.sendRedirect(request.getContextPath());
    }

}
