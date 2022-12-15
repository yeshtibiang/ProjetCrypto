package com.lamine.gest_users.servlets;

import com.lamine.gest_users.dao.UtilisateurDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete")
public class DeleteUser extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");

        if (id != null && id.matches("[0-9]+"))
        {
            UtilisateurDao.supprimer(Integer.parseInt(id));
        }

        response.sendRedirect(request.getContextPath());
    }

}

