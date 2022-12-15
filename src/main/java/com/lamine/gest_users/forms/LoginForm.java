package com.lamine.gest_users.forms;

import com.lamine.gest_users.beans.Utilisateur;
import com.lamine.gest_users.dao.UtilisateurDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;
import java.util.zip.CRC32;

public class LoginForm {
    private final String CHAMP_LOGIN = "login";
    private final String CHAMP_PASSWORD = "password";
    private final String EMPTY_FIELD_MESSAGE = "Ce champ est obligatoire";

    private boolean status;
    private String message;
    private Utilisateur utilisateur;
    private final Map<String, String> erreurs;
    private final HttpServletRequest request;

    public LoginForm(HttpServletRequest request) {
        this.request = request;
        this.status = false;
        this.message = "Login ou mot de passe incorrect";
        this.erreurs = new HashMap<>();
    }

    public boolean connecter(){
        String login = getParameter(CHAMP_LOGIN);
        String password = getParameter(CHAMP_PASSWORD);

        this.utilisateur = new Utilisateur("", "", login, password);
        validerChamps(CHAMP_LOGIN, CHAMP_PASSWORD);
        validerPassword();

        if (erreurs.isEmpty()){
            HttpSession session = request.getSession();
            session.setAttribute("isConnected", true);
            status = true;
            message = "Connexion reussie";
        }

        //the "status" and "message" variables are respectively
        // initialized to false and the correcponding message
        return status;
    }

    public String getParameter(String nomChamp){
        String valeur = request.getParameter(nomChamp);
        if(valeur == null || valeur.trim().isEmpty()){
            return null;
        }else{
            return valeur.trim();
        }
    }

    private void validerChamps(String ...champs){
        for(String champ : champs){
            if(getParameter(champ) == null){
                erreurs.put(champ, EMPTY_FIELD_MESSAGE);
            }
        }
    }

    private void validerPassword() {
        String login = utilisateur.getLogin();
        String password = utilisateur.getPassword();
        CRC32 crc = new CRC32();
        crc.update(password.trim().getBytes());

        String passwordHash = Long.toHexString(crc.getValue());
        String passwordSavedHash = "";

        Utilisateur savedUtilisateur = UtilisateurDao.getByLogin(login);
        if (savedUtilisateur != null) {
            passwordSavedHash = savedUtilisateur.getPassword();
        }

        if (!passwordHash.equals(passwordSavedHash)) {
            erreurs.put(CHAMP_PASSWORD, "Incorrect password");
        }
    }

    public boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Utilisateur getUtilisateur(){
        return utilisateur;
    }
}
