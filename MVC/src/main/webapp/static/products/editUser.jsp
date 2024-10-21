<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sửa Người Dùng</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/funtion.css'/>">
</head>
<body>
<div class="menu-header">
    <div class="banner">
        <h1>Sửa tài khoản</h1>
    </div>
</div>
<form action="${pageContext.request.contextPath}/menuUser?action=edit&id=${user.id}" method="post">
    <label for="username">Tên đăng nhập:</label>
    <input type="text" id="username" name="username" value="${user.username}" required>

    <label for="password">Mật khẩu:</label>
    <input type="password" id="password" name="password" required>

    <label for="role">Vai trò:</label>
    <select id="role" name="role" required>
        <option value="khách hàng" <c:if test="${user.role == 'khách hàng'}">selected</c:if>>Khách Hàng</option>
        <option value="quản lý" <c:if test="${user.role == 'quản lý'}">selected</c:if>>Quản Lý</option>
    </select>

    <button type="submit">Sửa Người Dùng</button>
</form>
<a href="${pageContext.request.contextPath}/menuUser?action=home">Trở Về Danh Sách Người Dùng</a>

</body>
</html>
