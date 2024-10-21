<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Tạo Người Dùng Mới</title>
  <link rel="stylesheet" type="text/css" href="<c:url value='/css/funtion.css'/>">
</head>
<body>
<div class="menu-header">
  <div class="banner">
    <h1>Thêm tài khoản mới</h1>
  </div>
</div>
<form action="/menuUser?action=add" method="post">
  <label for="username">Tên đăng nhập:</label>
  <input type="text" id="username" name="username" required>

  <label for="password">Mật khẩu:</label>
  <input type="password" id="password" name="password" required>

  <label for="role">Vai trò:</label>
  <select id="role" name="role" required>
    <option value="khách hàng">Khách Hàng</option>
    <option value="quản lý">Quản Lý</option>
  </select>

  <button type="submit">Tạo Người Dùng</button>
</form>
<a href="/menuUser?action=home">Trở Về Danh Sách Người Dùng</a>
</body>
</html>
