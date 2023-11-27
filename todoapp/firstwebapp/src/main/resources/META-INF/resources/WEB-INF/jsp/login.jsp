<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login</title>
</head>
<body>
<%--Welcome to the login page ${name}!--%>
<%--Lenguaje de expresión--%>
Welcome to Login Page
<pre>${errorMessage}</pre>
<form method="post" >
    Name: <input type="text" name="name">
    Password: <input type="password" name="password">
    <input type="submit">
</form>
</body>
</html>