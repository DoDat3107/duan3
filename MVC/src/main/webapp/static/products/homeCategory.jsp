<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
  <title>Danh Mục Sản Phẩm</title>
  <link rel="stylesheet" type="text/css" href="<c:url value='/css/home.css'/>">
</head>
<body>
<div class="container">
  <div class="header">
    <div class="logo">
      <img src="<c:url value='/static/img/logo/1.png'/>" alt="logo">
    </div>
    <div class="menu-header">
      <div class="banner">
        <h1>Quản lý danh mục sản phẩm</h1>
      </div>
    </div>
  </div>

  <div class="menu">
    <div class="box">
      <a  class="login-btn" href="<c:url value='/categories?action=create'/>">Tạo Danh Mục</a>
       <a class="login-btn" href="<c:url value='/categories'/>">Tất cả Danh Mục</a>
    </div>

    <div class="search">
      <form action="<c:url value='/categories'/>" method="get">
        <input type="hidden" name="action" value="search">
        <input type="text" id="search-input" name="name" placeholder="Nhập tên danh mục">
        <button class="login-btn" type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
      </form>
    </div>

    <div class="login">
      <a href="<c:url value='/productsStaff?action=homeStaff'/>" class="login-btn">menu chính</a>
      <a href="<c:url value='/user?action=logout'/>" class="login-btn">Đăng xuất</a>
    </div>
  </div>

  <div class="shop">
    <table border="1">
      <tr>
        <th colspan="4"><h1>Danh sách danh mục sản phẩm</h1></th>
      </tr>
      <tr>
        <th>ID</th>
        <th>Tên Danh Mục</th>
        <th colspan="2">Action </th>
      </tr>
      <c:if test="${empty list}">
        <tr>
          <td colspan="3">Không tìm thấy danh mục nào.</td>
        </tr>
      </c:if>
      <c:forEach var="category" items="${list}">
        <tr>
          <td><c:out value="${category.id}"/></td>
          <td><c:out value="${category.name}"/></td>

          <td><button class="login-btn">  <a href="<c:url value='/categories?action=edit&id=${category.id}'/>">Chỉnh Sửa</a></button></td>
            <td><button class="login-btn"> <a  href="<c:url value='/categories?action=delete&id=${category.id}'/>" onclick="return confirm('Bạn có chắc chắn muốn xóa danh mục này không?');">Xóa</a></button></td>

        </tr>
      </c:forEach>
    </table>
  </div>
</div>
</body>
</html>
