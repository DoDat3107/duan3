<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User Login</title>
    <link rel="stylesheet" type="text/css" href="/css/login.css">
</head>
<body>
<h2>Login</h2>
<form action="/user?action=login" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>

    <button type="submit">Đăng nhập</button>

    <div class="button-group">
        <a href="/products?action=home">Trở về menu</a>
        <a href="/user?action=register">Đăng ký</a>
    </div>
</form>

<% String errorMessage = (String) request.getAttribute("errorMessage"); %>
<% if (errorMessage != null) { %>
<p class="error-message"><%= errorMessage %></p>
<% } %>
</body>
</html>
