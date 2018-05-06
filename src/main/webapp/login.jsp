<%--
  Author: Pavel Ravvich.
  Date: 14.10.17.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>

</head>
<body>

    <div class="form">

        <h1>Log IN</h1><br>
        <form action="/login" method="post" >

            <input type="text" required placeholder="login" name="login"><br>
            <input type="password" required placeholder="password" name="password"><br><br>
            <input class="button" type="submit" value="Log In">

        </form>
    </div>
    <br>
    <div>
        Nie masz konta. <a href="/register">Zarejestruj się</a>
    </div>
</body>
</html>
