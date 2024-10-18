package com.example.mvc.controller;

import com.example.mvc.model.User;
import com.example.mvc.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserController", value = "/user")
public class UserController extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String action = request.getParameter("action");
        if (action == null) {
            action = "home";
        }
        if (checkUser(session)) {
            String role = (String) session.getAttribute("role");
            if (action.equals("logout")) {
                logout(request, response);
                return;
            }
            if (role.equals("quản lý")) {
                response.sendRedirect("/productsStaff?action=homeStaff");
            } else {
                response.sendRedirect("/productsCostumer?action=homeCostumer");
            }
        } else {
            switch (action) {
                case "login":
                    showLoginForm(request, response);
                    break;
                case "register":
                    showRegisterForm(request, response);
                    break;
                default:
                    showLoginForm(request, response);
                    break;
            }
        }
    }

    private boolean checkUser(HttpSession session) {
        return session != null && session.getAttribute("idUser") != null;
    }

    private void showLoginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/static/products/login.jsp");
        dispatcher.forward(request, response);
    }

    private void showRegisterForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/static/products/register.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "login":
                login(request, response);
                break;
            case "register":
                register(request, response);
                break;
            default:
                showError(request, response);
                break;
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (userService.checkUser(username, password)) {
            int id = userService.getIdUser(username, password);
            String role = userService.getRole(username, password);

            HttpSession session = request.getSession();
            session.setAttribute("idUser", id);
            session.setAttribute("role", role);

            if ("quản lý".equals(role)) {
                response.sendRedirect("/productsStaff?action=homeStaff");
            } else {
                response.sendRedirect("/productsCostumer?action=homeCostumer");
            }
        } else {
            request.setAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu không đúng.");
            showLoginForm(request, response);
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        if (userService.isUsernameExists(username)) {
            request.setAttribute("errorMessage", "Tên đăng nhập đã tồn tại.");
            showRegisterForm(request, response);
            return;
        }

        User user = new User(0, username, password, role);
        userService.add(user);
        response.sendRedirect("/user?action=login");
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("/user?action=login");
    }

    private void showError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("errorMessage", "Có lỗi xảy ra. Vui lòng thử lại.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/static/products/error.jsp");
        dispatcher.forward(request, response);
    }
}
