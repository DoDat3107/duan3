package com.example.mvc.service;

import com.example.mvc.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryService implements IService<Category> {
    private Connection connection = ConnectToMySql.getConnection();

    @Override
    public boolean add(Category category) {
        String sql = "INSERT INTO category (name) VALUES (?);";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, category.getName());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Đã thêm thành công danh mục: " + category.getName());
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean edit(Category category, int id) {
        String sql = "UPDATE category SET name = ? WHERE id = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, category.getName());
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM category WHERE id = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int findById(int id) {
        String sql = "SELECT id FROM category WHERE id = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Category> findAll() {
        List<Category> categoryList = new ArrayList<>();
        String sql = "SELECT * FROM category;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Category category = new Category(id, name);
                categoryList.add(category);
            }
            System.out.println("Số lượng danh mục hiện tại: " + categoryList.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    public Category findCategoryById(int id) {
        String sql = "SELECT * FROM category WHERE id = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                return new Category(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Category> findCategoriesByName(String name) {
        List<Category> categoryList = new ArrayList<>();
        String sql = "SELECT * FROM category WHERE name LIKE ?;"; // Tìm kiếm trong bảng category
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%"); // Thêm wildcard cho tìm kiếm
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String categoryName = resultSet.getString("name");
                categoryList.add(new Category(id, categoryName)); // Thêm danh mục vào danh sách
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList; // Trả về danh sách danh mục tìm được
    }
}
