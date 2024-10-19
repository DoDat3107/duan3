<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <title>costumer</title>
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
                <h1>Cửa hàng quần áo nam</h1>
            </div>
        </div>
    </div>

    <div class="menu">
        <div class="box">
            <div class="select"><a href="<c:url value='/productsCostumer'/>">Tất cả</a></div>

            <div class="select">
                <a href="#">Áo</a>
                <div class="dropdown">
                    <c:forEach var="category" items="${categoryList}">
                        <c:if test="${category.name.toLowerCase().contains('áo')}">
                            <a href="<c:url value='/productsCostumer?action=search&searchCategory=${category.name}'/>">${category.name}</a>
                        </c:if>
                    </c:forEach>
                </div>
            </div>

            <div class="select">
                <a href="#">Quần</a>
                <div class="dropdown">
                    <c:forEach var="category" items="${categoryList}">
                        <c:if test="${category.name.toLowerCase().contains('quần')}">
                            <a href="<c:url value='/productsCostumer?action=search&searchCategory=${category.name}'/>">${category.name}</a>
                        </c:if>
                    </c:forEach>
                </div>
            </div>

            <div class="select">
                <a href="#">Đồ bộ</a>
                <div class="dropdown">
                    <c:forEach var="category" items="${categoryList}">
                        <c:if test="${category.name.toLowerCase().contains('bộ')}">
                            <a href="<c:url value='/productsCostumer?action=search&searchCategory=${category.name}'/>">${category.name}</a>
                        </c:if>
                    </c:forEach>
                </div>
            </div>

            <div class="select">
                <a href="#">Phụ kiện</a>
                <div class="dropdown">
                    <c:forEach var="category" items="${categoryList}">
                        <c:if test="${!category.name.toLowerCase().contains('áo') && !category.name.toLowerCase().contains('quần')&& !category.name.toLowerCase().contains('bộ')}">
                            <a href="<c:url value='/productsCostumer?action=search&searchCategory=${category.name}'/>">${category.name}</a>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </div>

        <div class="box2">
            <div class="search">
                <form action="<c:url value='/productsCostumer'/>" method="get">
                    <input type="hidden" name="action" value="searchByName">
                    <input type="text" id="search-input" name="searchName" placeholder="Nhập tên sản phẩm">
                    <button class="login-btn" type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
                </form>
            </div>
        </div>

        <div class="login">
            <a href="http://localhost:8080/user?action=logout" class="login-btn">Đăng xuất</a>
        </div>
    </div>
    <div class="maketting">
        <div class="share1"><img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT8xkuCLTIs2_gLgP3EBPujywZPDVYlBAyKQ2VoXGrXXYZwuDPm&s" alt="img"></div>
        <div class="share1"><img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQhOygAwbQnNu8ljgZ5xLUS_B7qPqokH4PUR11g1LGAY3kVlok&s" alt=""></div>
        <div class="share1"><img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQqk3A1L6Jvz2XfOhSHvyCL899xVDIybBkqe3B4ZyaKCwidlbY&s" alt=""></div>
        <div class="share1"><img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTgV9CiwW9kHwtPfMXday-mTzPKX4toX-XqcSoe_yuh-Yo31I0&s" alt=""></div>
    </div>
    <div class="shop">
        <table border="1">
            <tr>
                <th colspan="7"><h1>Danh sách sản phẩm</h1></th>
            </tr>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Tên Loại mặt hàng</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Image</th>
                <%--                    <th colspan="2">Action</th>--%>
            </tr>
            <c:forEach var="item" items="${productList}">
                <tr>
                    <td><c:out value="${item.id}"/></td>
                    <td><c:out value="${item.name}"/></td>
                    <td><c:out value="${item.nameCategory}"/></td>
                    <td><c:out value="${item.price}"/></td>
                    <td><c:out value="${item.quantity}"/></td>
                    <td><img src="<c:out value='${item.image}'/>" alt="" style="width: 100px;"></td>
                        <%--                        <td><button><a href="http://localhost:8080/products?action=edit&id=${item.id}">Edit</a></button></td>--%>
                        <%--                        <td><button><a href="http://localhost:8080/products?action=delete&id=${item.id}">Delete</a></button></td>--%>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>