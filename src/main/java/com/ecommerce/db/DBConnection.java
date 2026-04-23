package com.ecommerce.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Change "YOUR_PASSWORD" to your actual MySQL password
    private static final String URL = "jdbc:mysql://localhost:3306/ecommerce";
    private static final String USER = "root";
    private static final String PASSWORD = "root123"; // CHANGE THIS!
    
    private static Connection connection = null;
    
    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Database connected successfully!");
            } catch (Exception e) {
                System.out.println("❌ Database connection failed!");
                e.printStackTrace();
            }
        }
        return connection;
    }
    
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}