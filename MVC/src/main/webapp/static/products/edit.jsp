<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Chỉnh Sửa Sản Phẩm</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/funtion.css'/>">
</head>
<body>
<div class="menu-header">
    <div class="banner">
        <h1>chỉnh sửa sản phẩm</h1>
    </div>
</div>
<form action="${pageContext.request.contextPath}/products?action=edit&id=${product.id}" method="post">
    <input type="text" name="name" placeholder="Tên sản phẩm" value="${product.name}" required>
    <input type="number" name="quantity" placeholder="Số lượng" value="${product.quantity}" required min="1">
    <input type="number" name="price" placeholder="Giá" value="${product.price}" required step="0.01" min="0">
    <input type="text" name="image" placeholder="Hình ảnh" value="${product.image}" required>

    <label for="category">Danh mục:</label>
    <select name="idCategory" id="category" required>
        <c:forEach var="category" items="${categoryList}">
            <option value="${category.id}" <c:if test="${category.id == product.idCategory}">selected</c:if>>${category.name}</option>
        </c:forEach>
    </select>

    <img src="${product.image}" alt="Hình ảnh sản phẩm">

    <button type="submit">Sửa</button>
</form>
<a href="${pageContext.request.contextPath}/productsStaff?action=homeStaff" class="button">Trở về Danh Sách Sản Phẩm</a>
</body>
</html>
