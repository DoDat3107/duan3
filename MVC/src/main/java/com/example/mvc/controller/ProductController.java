package com.example.mvc.controller;

import com.example.mvc.model.Category;
import com.example.mvc.model.Product;
import com.example.mvc.model.Subcategory;
import com.example.mvc.service.CategoryService;
import com.example.mvc.service.ProductService;
import com.example.mvc.service.SubcategoryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ProductController", value = "/products")
public class ProductController extends HttpServlet {

    private CategoryService categoryService = new CategoryService();
    private final ProductService productService = new ProductService();
    private final SubcategoryService subcategoryService = new SubcategoryService();
    private static final Logger LOGGER = Logger.getLogger(ProductController.class.getName());

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
            case "delete":
                delete(request, response);
                break;
            case "edit":
                showEdit(request, response);
                break;
            case "search":
                String searchCategory = request.getParameter("searchCategory");
                searchByCategoryName(request, response, searchCategory);
                break;
            case "searchByName":
                String searchName = request.getParameter("searchName");
                searchByProductName(request, response, searchName);
                break;
            default:
                showError(request, response);
                break;
        }
    }

    private void showHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> productList = productService.findAll();
        request.setAttribute("productList", productList);

        List<Category> categoryList = categoryService.findAll(); // Get all categories
        categorizeSubcategories(request, categoryList); // Categorize subcategories

        request.setAttribute("categoryList", categoryList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/static/products/home.jsp");
        dispatcher.forward(request, response);
    }

    private void categorizeSubcategories(HttpServletRequest request, List<Category> categoryList) {
        List<Subcategory> shirtSubcategories = new ArrayList<>();
        List<Subcategory> pantsSubcategories = new ArrayList<>();
        List<Subcategory> accessorySubcategories = new ArrayList<>();

        // Loop through categories to classify
        for (Category category : categoryList) {
            List<Subcategory> subcategoryList = subcategoryService.findByCategoryId(category.getId());
            if (category.getName().equalsIgnoreCase("Áo")) {
                shirtSubcategories.addAll(subcategoryList);
            } else if (category.getName().equalsIgnoreCase("Quần")) {
                pantsSubcategories.addAll(subcategoryList);
            } else if (category.getName().equalsIgnoreCase("Phụ kiện")) {
                accessorySubcategories.addAll(subcategoryList);
            }
        }

        // Set subcategory lists in request to use in JSP
        request.setAttribute("shirtSubcategories", shirtSubcategories);
        request.setAttribute("pantsSubcategories", pantsSubcategories);
        request.setAttribute("accessorySubcategories", accessorySubcategories);
    }

    private void showEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categoryList = categoryService.findAll();
        request.setAttribute("categoryList", categoryList);
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Product product = productService.findProductById(id);
            if (product == null) {
                request.setAttribute("errorMessage", "Không tìm thấy sản phẩm với ID: " + id);
                showError(request, response);
                return;
            }
            request.setAttribute("product", product);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/static/products/edit.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "ID sản phẩm không hợp lệ.", e);
            request.setAttribute("errorMessage", "ID sản phẩm không hợp lệ.");
            showError(request, response);
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            boolean isDeleted = productService.delete(id);

            if (isDeleted) {
                response.sendRedirect("/products?action=home");
            } else {
                LOGGER.log(Level.WARNING, "Không tìm thấy sản phẩm với ID: " + id);
                request.setAttribute("errorMessage", "Không tìm thấy sản phẩm với ID: " + id);
                showError(request, response);
            }
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "ID sản phẩm không hợp lệ.", e);
            request.setAttribute("errorMessage", "ID sản phẩm không hợp lệ.");
            showError(request, response);
        }
    }

    private void showError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/error/notFound.jsp");
        dispatcher.forward(request, response);
    }

    private void showFormCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categoryList = categoryService.findAll();
        request.setAttribute("categoryList", categoryList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/static/products/create.jsp");
        dispatcher.forward(request, response);
    }

    private void searchByCategoryName(HttpServletRequest request, HttpServletResponse response, String searchCategory) throws ServletException, IOException {
        List<Product> productList = productService.findByCategoryName(searchCategory);
        if (productList.isEmpty()) {
            request.setAttribute("errorMessage", "Không tìm thấy sản phẩm trong danh mục: " + searchCategory);
        }
        request.setAttribute("productList", productList);
        categorizeSubcategories(request, categoryService.findAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/static/products/home.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "create":
                addProduct(request, response);
                break;
            case "edit":
                editProduct(request, response);
                break;
            default:
                showError(request, response);
                break;
        }
    }

    public void addProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String image = request.getParameter("image");
        int idCategory;
        int quantity;
        double price;

        try {
            idCategory = Integer.parseInt(request.getParameter("idCategory"));
            quantity = Integer.parseInt(request.getParameter("quantity"));
            price = Double.parseDouble(request.getParameter("price"));
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "ID danh mục, số lượng hoặc giá không hợp lệ.");
            showFormCreate(request, response);
            return;
        }

        Product product = new Product(name, idCategory, quantity, price, image);
        productService.add(product);
        response.sendRedirect("/products?action=home");
    }

    public void editProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String image = request.getParameter("image");
            int idCategory = Integer.parseInt(request.getParameter("idCategory"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double price = Double.parseDouble(request.getParameter("price"));
            Product product = new Product(id, name, idCategory, quantity, price, image);
            productService.edit(product, id);
            response.sendRedirect("/products?action=home");
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Thông tin sản phẩm không hợp lệ.");
            showError(request, response);
        }
    }

    private void searchByProductName(HttpServletRequest request, HttpServletResponse response, String searchName) throws ServletException, IOException {
        List<Product> productList = productService.findByProductName(searchName);
        if (productList.isEmpty()) {
            request.setAttribute("errorMessage", "Không tìm thấy sản phẩm với tên: " + searchName);
        }
        request.setAttribute("productList", productList);
        categorizeSubcategories(request, categoryService.findAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/static/products/home.jsp");
        dispatcher.forward(request, response);
    }
}
