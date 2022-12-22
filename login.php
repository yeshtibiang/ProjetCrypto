<?php
/* Initialize the session */
session_start();

/* Check if the user is already logged in, if yes then redirect him to welcome page */
if (isset($_SESSION["loggedin"]) && $_SESSION["loggedin"] === true)
{
    header("location: welcome.php");
    exit;
}

/* Include config file */
require_once "config.php";

/* Define variables and initialize with empty values */
$username = $password = "";
$username_err = $password_err = "";

/* Processing form data when form is submitted */
if ($_SERVER["REQUEST_METHOD"] == "POST")
{

    /* Check if username is empty */
    if (empty(trim($_POST["username"])))
    {
        $username_err = "Entrez votre nom d'utilisateur SVP.";
    }
    else
    {
        $username = trim($_POST["username"]);
    }

    /* Check if password is empty */
    if (empty(trim($_POST["password"])))
    {
        $password_err = "Entrez votre nom d'utilisateur SVP.";
    }
    else
    {
        $password = trim($_POST["password"]);
    }

    /* Validate credentials */
    if (empty($username_err) && empty($password_err))
    {
        /* Prepare a sql query statement */
        $sql = "SELECT id, username FROM users WHERE username = '$username' and password = '$password'";

        $result = mysqli_query($link, $sql);

        if (mysqli_num_rows($result) > 0)
        {
            session_start();

            /* Store data in session variables */
            $_SESSION["loggedin"] = true;
            $_SESSION["id"] = $id;
            $_SESSION["username"] = $username;

            /* Redirect user to welcome page */
            header("location: welcome.php");
        }
        else
        {
            /* Display an error message if there is no row selected. */
            $password_err = "Le mot de passe entré n'est pas valide.";
        }
        /* Close statement */
        mysqli_close($link);
    }
}
?>
 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="assets/bootstrap.css">
    <style type="text/css">
        body{ font: 14px sans-serif; }
        .wrapper{ width: 350px; padding: 20px; }
    </style>
</head>
<body>
    <div class="wrapper">
        <h2>Connexion</h2>
        <p>Entrez vos identifiants</p>
        <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post">
            <div class="form-group <?php echo (!empty($username_err)) ? 'has-error' : ''; ?>">
                <label>Nom utilisateur</label>
                <input type="text" name="username" autocomplete="off" class="form-control" value="<?php echo $username; ?>">
                <span class="help-block"><?php echo $username_err; ?></span>
            </div>    
            <div class="form-group <?php echo (!empty($password_err)) ? 'has-error' : ''; ?>">
                <label>Mot de passe</label>
                <input type="password" name="password" autocomplete="off" class="form-control">
                <span class="help-block"><?php echo $password_err; ?></span>
            </div>
            <div class="form-group">
                <input type="submit" class="btn btn-primary" value="Login">
            </div>
            <p>Vous n'avez pas un compte? <a href="register.php">S'enregistrer maintenant</a>.</p>
        </form>
    </div>    
</body>
</html>
