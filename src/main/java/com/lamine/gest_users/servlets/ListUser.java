package com.lamine.gest_users.servlets;

import com.lamine.gest_users.dao.UtilisateurDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({ "", "/list" })
public class ListUser extends HttpServlet
{
    private static final long	serialVersionUID = 1L;
    private static final String	VUE_LIST_UTILISATEUR = "/listeUtilisateur.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("utilisateurs", UtilisateurDao.lister());
        request
                .getServletContext()
                .getRequestDispatcher(VUE_LIST_UTILISATEUR)
                .forward(request, response);
    }
}
