<%--
  Created by IntelliJ IDEA.
  User: bogda
  Date: 04.05.2018
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register form</title>
</head>
<body>
<div class="form">

    <h1>Registration</h1><br>
    <form action="/register" method="post">

        <input type="text" required placeholder="Login" name="login"><br>
        <input type="password" required placeholder="Password" name="password">
        <input type="password" required placeholder="Confirm Password" name="confirmPassword"><br>
        <h2>Dane</h2>
        <input type="text" required placeholder="First Name" name="firstName"><br>
        <input type="text" required placeholder="Last Name" name="lastName"><br>
        <input type="text" required placeholder="E-mail" name="email"><br>
        <h2>Address</h2>
        <input type="text" required placeholder="Address" name="address"><br>
        <input type="text" required placeholder="ZIP" name="zip"><br>
        <input type="text" required placeholder="City" name="city"><br>
        <input type="text" required placeholder="Region" name="region"><br>
        <input type="text" required placeholder="Country" name="country"><br>

        <br><br>
        <input class="button" type="submit" value="Register">

    </form>
</div>
</body>
</html>
