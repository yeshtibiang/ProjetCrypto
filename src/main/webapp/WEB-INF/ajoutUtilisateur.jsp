<%@ page import="com.lamine.gest_users.beans.Utilisateur" %>
Created by IntelliJ IDEA.
  User: lamine
  Date: 04/11/2022
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    final String APP_ROOT = request.getContextPath();
    Utilisateur utilisateur = (Utilisateur) request.getAttribute("utilisateur");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Gestion des utilisateurs</title>
    <link rel="stylesheet" href="<%=APP_ROOT%>/css/style.css">
    <script src="<%=APP_ROOT%>/js/script.js"></script>
</head>
<body>
<jsp:include page="components/navbar.jsp"/>

<div id="corps">
    <h1 id="titre-principal">Ajout d'un utilisateur</h1>
    <p id="statusMessage" class="${requestScope.status ? "success" : "erreur"}">${requestScope.message}</p>
    <div class="form">
        <form method="post">
            <div class="input-container ic1">
                <label>Nom</label>
                <input class="input" type="text" name="nom" value=${requestScope.utilisateur.nom}>
                <span class="erreur">${requestScope.erreurs.nom}</span>
            </div>
            <div class="input-container ic1">
                <label>Prenom</label>
                <input class="input" type="text" name="prenom" value=${requestScope.utilisateur.prenom}>
                <span class="erreur">${requestScope.erreurs.prenom}</span>
            </div>
            <div class="input-container ic1">
                <label>Login</label>
                <input class="input" type="text" name="login" value=${requestScope.utilisateur.login}>
                <span class="erreur">${requestScope.erreurs.login}</span>
            </div>
            <div class="input-container ic1">
                <label>Password</label>
                <input class="input" type="password" name="password">
                <span class="erreur">${requestScope.erreurs.password}</span>
            </div>
            <div class="input-container ic1">
                <label>Password (Confirmation)</label>
                <input class="input" type="password" name="passwordBis">
                <span class="erreur">${requestScope.erreurs.passwordBis}</span>
            </div>
            <div class="input-container ic1">
                <input type="submit" class="submit" value="Ajouter">
            </div>
        </form>
    </div>

    <jsp:include page="components/footer.jsp"/>
</html>
