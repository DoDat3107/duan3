package com.example.mvc.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectToMySql {
   private static String URL = "jdbc:mysql://localhost:3306/abc";
   private static String USERNAME = "root";
   private static String PASSWORD = "123456";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }
        catch (Exception e){
            return null;
        }
    }
}
