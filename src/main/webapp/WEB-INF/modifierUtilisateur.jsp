<%@ page import="com.lamine.gest_users.beans.Utilisateur" %><%--
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

    <div class="formbg-outer">
        <div class="formbg">
            <div class="formbg-inner padding-horizontal--48">
                <span class="padding-bottom--15">Nouvel Utilisateur</span>
                <form id="stripe-login" method="post">
                    <div class="field padding-bottom--24">
                        <label>Nom</label>
                        <input type="text" name="nom">
                    </div>
                    <div class="field padding-bottom--24">
                        <div class="grid--50-50">
                            <label>Prenom</label>
                            <input type="text" name=prenom">
                        </div>
                    </div>
                    <div class="field padding-bottom--24">
                        <div class="grid--50-50">
                            <label>Login</label>
                            <input type="text" name=login">
                        </div>
                    </div>
                    <div class="field padding-bottom--24">
                        <div class="grid--50-50">
                            <label>Password</label>
                            <input type="password" name=password">
                        </div>
                    </div>
                    <div class="field padding-bottom--24">
                        <input type="submit" name="submit" value="Valider">
                    </div>
                </form>
            </div>
        </div>
    </div>

    <jsp:include page="components/footer.jsp"/>
</html>
