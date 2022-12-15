<%--
  Created by IntelliJ IDEA.
  User: lamine
  Date: 07/11/2022
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    final String APP_ROOT = request.getContextPath();
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="menu">
    <ul>
        <li><a class="active" href="<%= APP_ROOT %> ">Accueil</a></li>
        <li><a href="<%= APP_ROOT %>/list">Lister</a></li>
        <li><a href="<%= APP_ROOT %>/add">Ajouter</a></li>
        <li><h3>Gestion des utilisateurs</h3></li>
        <li class="logout"><a href="<%= APP_ROOT %>/logout">Se Deconnecter</a></li>
    </ul>
</div>
</body>
</html>
