<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Chỉnh Sửa Danh Mục</title>

  <link rel="stylesheet" type="text/css" href="<c:url value='/css/funtion.css'/>">
</head>
<body>
<div class="container">
  <div class="menu-header">
    <div class="banner">
      <h1>Chỉnh sửa loại sản phẩm</h1>
    </div>
  </div>
  <c:if test="${not empty errorMessage}">
    <div style="color: red;">${errorMessage}</div>
  </c:if>
  <form action="${pageContext.request.contextPath}/categories?action=edit&id=${category.id}" method="post">
    <table>
      <tr>
        <td><label for="name">Tên danh mục:</label></td>
        <td><input type="text" name="name" id="name" value="${category.name}" required></td>
      </tr>
      <tr>
        <td colspan="2">
          <button type="submit">Sửa</button>
          <button class="back"><a href="categories?action=home">Trở về</a></button>
        </td>
      </tr>
    </table>
  </form>
</div>
</body>
</html>
