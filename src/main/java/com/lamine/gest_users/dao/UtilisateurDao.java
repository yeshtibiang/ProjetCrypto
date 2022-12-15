package com.lamine.gest_users.dao;


import com.lamine.gest_users.beans.Utilisateur;
import com.lamine.gest_users.db.DBUtils;

import java.util.List;

public class UtilisateurDao {
    public static boolean ajouter(Utilisateur utilisateur)
    {
        try {
            DBUtils.ajouter(utilisateur);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Utilisateur> lister()
    {
        try {
            return DBUtils.getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean modifier(Utilisateur utilisateur)
    {
        try {
            DBUtils.update(utilisateur.getId(), utilisateur);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean supprimer(int id)
    {
        try {
            DBUtils.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Utilisateur get(int id) {
        try {
            return DBUtils.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Utilisateur getByLogin(String login){
        try {
            return DBUtils.getByLogin(login);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
