<%@ page import="com.lamine.gest_users.beans.Utilisateur" %>
Created by IntelliJ IDEA.
  User: lamine
  Date: 07/11/2022
  Time: 00:01
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
    <h2>Id de l'utilisateur à supprimer</h2>
    <!-- create, a form to type in an id-->
    <form action="<%= APP_ROOT %>/delete" method="get">
        <div class="formItem">
            <label>Id</label>
            <input type="text" name="id">
        </div>
        <input type="submit" value="delete">
    </form>
</body>
</html>
