package com.lamine.gest_users.forms;

import com.lamine.gest_users.beans.Utilisateur;
import com.lamine.gest_users.dao.UtilisateurDao;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

public class AddUserForm {
    private final String CHAMP_NOM = "nom";
    private final String CHAMP_PRENOM = "prenom";
    private final String CHAMP_LOGIN = "login";
    private final String CHAMP_PASSWORD = "password";
    private final String CHAMP_PASSWORD_BIS = "passwordBis";
    private final String WRONG_PASSWORD_MESSAGE = "Les mots de passe ne correspondent pas";
    private final String EMPTY_FIELD_MESSAGE = "Ce champ est obligatoire";
    private boolean status;
    private String message;
    private Utilisateur utilisateur;
    private final HttpServletRequest request;
    private final Map<String, String> erreurs;

    public AddUserForm(HttpServletRequest request) {
        this.request = request;
        this.erreurs = new HashMap<>();
    }

    public boolean ajouter(){
        String nom = getParameter(CHAMP_NOM);
        String prenom = getParameter(CHAMP_PRENOM);
        String login = getParameter(CHAMP_LOGIN);
        String password = getParameter(CHAMP_PASSWORD);
        String passwordBis = getParameter(CHAMP_PASSWORD_BIS);

        utilisateur = new Utilisateur(nom, prenom, login, password);

        validerChamps(CHAMP_NOM, CHAMP_PRENOM, CHAMP_LOGIN, CHAMP_PASSWORD, CHAMP_PASSWORD_BIS);
        validerPasswords();

        if(erreurs.isEmpty()){
            status = true;
            message = "Utilisateur ajouté avec succès";
            UtilisateurDao.ajouter(utilisateur);
        }
        else{
            status = false;
            message = "Erreur lors de l'ajout de l'utilisateur";
        }

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

    private void validerPasswords(){
        String password = getParameter(CHAMP_PASSWORD);
        String passwordBis = getParameter(CHAMP_PASSWORD_BIS);

        if(password != null && !password.equals(passwordBis)){
            erreurs.put(CHAMP_PASSWORD, WRONG_PASSWORD_MESSAGE);
            erreurs.put(CHAMP_PASSWORD_BIS, WRONG_PASSWORD_MESSAGE);
        }
    }

    public boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }
}
