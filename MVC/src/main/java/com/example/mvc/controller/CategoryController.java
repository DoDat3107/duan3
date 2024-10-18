package com.example.mvc.controller;

import com.example.mvc.model.Category;
import com.example.mvc.service.CategoryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "CategoryController", value = "/categories")
public class CategoryController extends HttpServlet {
    private final CategoryService categoryService = new CategoryService();
    private static final Logger LOGGER = Logger.getLogger(CategoryController.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "home";
        }
        switch (action) {
            case "home":
                showHome(request, response);
                break;
            case "create":
                showFormCreate(request, response);
                break;
            case "edit":
                showEdit(request, response);
                break;
            case "delete":
                delete(request, response);
                break;
            case "search":
                searchCategory(request, response);
                break;
            default:
                showError(request, response);
                break;
        }
    }

    private void showHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categoryList = categoryService.findAll();
        LOGGER.info("Danh sách danh mục hiện tại: " + categoryList.size());

        request.setAttribute("list", categoryList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/static/products/homeCategory.jsp");
        dispatcher.forward(request, response);
    }

    private void showFormCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/static/products/createCategory.jsp");
        dispatcher.forward(request, response);
    }

    private void showEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Category category = categoryService.findCategoryById(id);
            if (category == null) {
                request.setAttribute("errorMessage", "Không tìm thấy danh mục với ID: " + id);
                showError(request, response);
                return;
            }
            request.setAttribute("category", category);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/static/products/editCategory.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "ID danh mục không hợp lệ.", e);
            request.setAttribute("errorMessage", "ID danh mục không hợp lệ.");
            showError(request, response);
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String idStr = request.getParameter("id");
            if (idStr == null || idStr.isEmpty()) {
                request.setAttribute("errorMessage", "ID danh mục không hợp lệ.");
                showError(request, response);
                return;
            }

            int id = Integer.parseInt(idStr);
            boolean isDeleted = categoryService.delete(id);

            if (isDeleted) {
                response.sendRedirect("/categories?action=home");
            } else {
                LOGGER.log(Level.WARNING, "Không thể xóa danh mục với ID: " + id);
                request.setAttribute("errorMessage", "Không thể xóa danh mục với ID: " + id);
                showError(request, response);
            }
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "ID danh mục không hợp lệ.", e);
            request.setAttribute("errorMessage", "ID danh mục không hợp lệ.");
            showError(request, response);
        }
    }

    private void showError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/error/notFound.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            showError(request, response);
            return;
        }
        switch (action) {
            case "create":
                addCategory(request, response);
                break;
            case "edit":
                editCategory(request, response);
                break;
            default:
                showError(request, response);
                break;
        }
    }

    private void addCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        if (name == null || name.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Tên danh mục không được để trống.");
            showFormCreate(request, response);
            return;
        }

        Category category = new Category(name);
        LOGGER.info("Thêm danh mục: " + category.getName());
        categoryService.add(category);
        LOGGER.info("Đã thêm danh mục thành công.");

        response.sendRedirect("/categories?action=home");
    }

    private void editCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            if (name == null || name.trim().isEmpty()) {
                request.setAttribute("errorMessage", "Tên danh mục không được để trống.");
                showEdit(request, response);
                return;
            }
            Category category = new Category(id, name);
            categoryService.edit(category, id);
            response.sendRedirect("/categories?action=home");
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "ID danh mục không hợp lệ.", e);
            request.setAttribute("errorMessage", "ID danh mục không hợp lệ.");
            showError(request, response);
        }
    }
    private void searchCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        List<Category> categoryList;

        if (name != null && !name.trim().isEmpty()) {
            categoryList = categoryService.findCategoriesByName(name);
        } else {
            categoryList = categoryService.findAll();
        }

        LOGGER.info("Danh sách danh mục tìm được: " + categoryList.size());
        request.setAttribute("list", categoryList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/static/products/homeCategory.jsp");
        dispatcher.forward(request, response);
    }

}
