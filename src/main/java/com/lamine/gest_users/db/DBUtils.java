package com.lamine.gest_users.db;

import com.lamine.gest_users.beans.Utilisateur;
import com.lamine.gest_users.exceptions.MysqlUnreachableException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {
    private static Connection connect() throws MysqlUnreachableException {
        Connection conn = DBConnect.getInstance().getConn();

        if(conn == null)
            throw new MysqlUnreachableException();
        return conn;
    }

    public static List<Utilisateur> getAllUsers() throws MysqlUnreachableException, SQLException {
        PreparedStatement state;
        ResultSet result;
        List<Utilisateur> utilisateurs = new ArrayList<>();
        try {
            Connection conn = connect();
            state = conn.prepareStatement(
                    "SELECT * FROM Utilisateur",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            result = state.executeQuery();

            while(result.next()){
                Utilisateur utilisateur = new Utilisateur(
                        result.getInt("id"),
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("login"),
                        result.getString("password")
                );
                utilisateurs.add(utilisateur);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return utilisateurs;
    }

    public static void ajouter(Utilisateur utilisateur) throws MysqlUnreachableException, SQLException {
        PreparedStatement state;
        try {
            Connection conn = connect();
            state = conn.prepareStatement(
                    "INSERT INTO Utilisateur(nom, prenom, login, password) VALUES(?, ?, ?, ?)"
            );
            state.setString(1, utilisateur.getNom());
            state.setString(2, utilisateur.getPrenom());
            state.setString(3, utilisateur.getLogin());
            state.setString(4, utilisateur.getPassword());
            state.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(int id){
        PreparedStatement state;

        try {
            Connection conn = connect();
            state = conn.prepareStatement("delete from Utilisateur where id = ?");
            state.setInt(1, id);

            state.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Utilisateur getByLogin(String login) {
        Utilisateur utilisateur = null;
        PreparedStatement state;
        ResultSet result;
        try {
            Connection conn = connect();
            state = conn.prepareStatement("select * from Utilisateur where login = ?");
            state.setString(1, login);

            result = state.executeQuery();

            if (result.next()) {
                utilisateur = new Utilisateur(
                        result.getInt("id"),
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("login"),
                        result.getString("password")
                );
            }
        } catch (MysqlUnreachableException | SQLException ex) {
            throw new RuntimeException(ex);
        }
        return utilisateur;
    }

    public static void update(int id, Utilisateur newOne){
        PreparedStatement state;
        try {
            Connection conn = connect();
            state = conn.prepareStatement(
                    "UPDATE Utilisateur SET nom = ?, prenom = ?, login = ?, password = ? WHERE id = ?"
            );
            state.setString(1, newOne.getNom());
            state.setString(2, newOne.getPrenom());
            state.setString(3, newOne.getLogin());
            state.setString(4, newOne.getPassword());
            state.setInt(5, id);
            state.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Utilisateur getById(int id){
        Utilisateur utilisateur = null;
        PreparedStatement state;
        ResultSet result;
        try {
            Connection conn = connect();
            state = conn.prepareStatement("select * from Utilisateur where id = ?");
            state.setInt(1, id);

            result = state.executeQuery();

            if (result.next()) {
                utilisateur = new Utilisateur(
                        result.getInt("id"),
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("login"),
                        result.getString("password")
                );
            }
        } catch (MysqlUnreachableException | SQLException ex) {
            throw new RuntimeException(ex);
        }
        return utilisateur;
    }
}
