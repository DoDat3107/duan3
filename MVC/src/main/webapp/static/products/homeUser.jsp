<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Danh Sách Người Dùng</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
  <link rel="stylesheet" type="text/css" href="<c:url value='/css/home.css'/>">
</head>
<body>
<div class="container">
  <div class="header">
    <h1>Quản Lý Người Dùng</h1>
  </div>

  <div class="menu">
    <div class="box">
      <a class="login-btn" href="<c:url value='/menuUser?action=add'/>">Thêm Người Dùng</a>
      <a class="login-btn" href="<c:url value='/menuUser?action=home'/>">Tất cả Người Dùng</a>
    </div>

    <div class="search">
      <form action="<c:url value='/menuUser'/>" method="get">
        <input type="hidden" name="action" value="search">
        <input type="text" id="search-input" name="username" placeholder="Nhập tên người dùng" required> <!-- Thêm required nếu cần -->
        <button class="login-btn" type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
      </form>
    </div>

    <div class="login">
      <a href="<c:url value='/productsStaff?action=homeStaff'/>" class="login-btn">Menu chính</a>
      <a href="<c:url value='/user?action=logout'/>" class="login-btn">Đăng xuất</a>
    </div>
  </div>

  <div class="shop">
    <table border="1">
      <tr>
        <th>ID</th>
        <th>Tên Đăng Nhập</th>
        <th>Mật khẩu</th>
        <th>Vai Trò</th>
        <th>Hành Động</th>
      </tr>
      <c:forEach var="user" items="${users}">
        <tr>
          <td><c:out value="${user.id}"/></td>
          <td><c:out value="${user.username}"/></td>
          <td><c:out value="${user.password}"/></td>
          <td><c:out value="${user.role}"/></td>
          <td>
            <a href="<c:url value='/menuUser?action=edit&id=${user.id}'/>">Sửa</a>
            <a href="<c:url value='/menuUser?action=delete&id=${user.id}'/>" onclick="return confirm('Bạn có chắc chắn muốn xóa người dùng này không?');">Xóa</a>
          </td>
        </tr>
      </c:forEach>
    </table>
  </div>
</div>
</body>
</html>
