<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>User Registration</title>
  <link rel="stylesheet" type="text/css" href="/css/register.css">
</head>
<body>
<h2>Đăng ký</h2>
<form action="/user?action=register" method="post">
  <label for="username">Username:</label>
  <input type="text" id="username" name="username" required>

  <label for="password">Password:</label>
  <input type="password" id="password" name="password" required>

  <label for="role">Role:</label>
  <select id="role" name="role">
    <option value="khách hàng">Khách hàng</option>
    <option value="quản lý">Quản lý</option>
  </select>
  <button type="submit">Đăng ký</button>
</form>
</body>
</html>
