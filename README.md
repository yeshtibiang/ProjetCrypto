# SQL Injection

## Description

Ceci est un exemple de mise en place de l'injection SQL. 
Il s'agit de voir comment à travers l'injection SQL un attaquant peut bypasser l'authentification. 

## Installation

### Prérequis

- PHP 7.2^
- mysql 5.7^

### Installation

- Cloner le projet
- Créer une base de données dic3 de préférence
- Importer le fichier dic3.sql
- Modifier le fichier config.php avec vos identifiants de connexion à la base de données
- Lancer le projet avec la commande `php -S localhost:8000`

### tester l'injection sql

- Lancer le projet avec la commande `php -S localhost:8000`
- Ouvrir votre navigateur et taper l'url `http://localhost:8000/login.php`
- Remplir le formulaire avec les identifiants suivants:
    - username: jared
    - password: foo' or '1'='1
- Vous êtes connecté

### Solution

- Utiliser des requêtes préparées avec PDO ou mysqli pour éviter les injections SQL: https://www.php.net/manual/fr/pdo.prepared-statements.php

### explication

- L'attaque de l'injection SQL se déroule dû à une mauvaise utilisation des requêtes SQL pour vérifier l'identité de l'utilisateur qui se connecte. Vu que notre requête SQL est la suivante: `SELECT * FROM users WHERE username = '$username' AND password = '$password'` et que l'on peut injecter du code SQL dans les variables `$username` et `$password`, on peut bypasser l'authentification en injectant le code suivant: `foo' or '1'='1`. Ce code va permettre de passer la vérification de l'identité de l'utilisateur. En effet, la requête SQL devient: `SELECT * FROM users WHERE username = 'foo' or '1'='1' AND password = 'foo' or '1'='1'`. La requête SQL va donc retourner tous les utilisateurs de la base de données car la condition `1=1` est toujours vraie.
