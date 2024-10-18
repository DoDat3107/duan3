<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Tạo Sản Phẩm</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/funtion.css'/>">
</head>
<body>
<div class="menu-header">
    <div class="banner">
        <h1>Thêm mới sản phẩm</h1>
    </div>
</div>
<form action="${pageContext.request.contextPath}/products?action=create" method="post">
    <input type="text" name="name" placeholder="Tên sản phẩm" required>
    <input type="number" name="quantity" placeholder="Số lượng" required min="1">
    <input type="number" name="price" placeholder="Giá" required step="0.01" min="0">
    <input type="text" name="image" placeholder="Hình ảnh" required>

    <label for="idCategory">Danh mục:</label>
    <select name="idCategory" id="idCategory" required>
        <c:forEach var="category" items="${categoryList}">
            <option value="${category.id}">${category.name}</option>
        </c:forEach>
    </select>

    <button type="submit">Thêm</button>
</form>
<a href="${pageContext.request.contextPath}/productsStaff?action=homeStaff" class="button">Trở về Danh Sách Sản Phẩm</a>
</body>
</html>
