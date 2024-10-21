package com.example.mvc.service;

import com.example.mvc.model.Subcategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubcategoryService {
    private Connection connection = ConnectToMySql.getConnection();

    public List<Subcategory> findAll() {
        List<Subcategory> subcategoryList = new ArrayList<>();
        String sql = "SELECT * FROM subcategory;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int idCategory = resultSet.getInt("idCategory");
                Subcategory subcategory = new Subcategory(id, name, idCategory);
                subcategoryList.add(subcategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subcategoryList;
    }

    public List<Subcategory> findByCategoryId(int categoryId) {
        List<Subcategory> subcategoryList = new ArrayList<>();
        String sql = "SELECT * FROM subcategory WHERE idCategory = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Subcategory subcategory = new Subcategory(id, name, categoryId);
                subcategoryList.add(subcategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subcategoryList;
    }
}
