package com.ecommerce.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    // YOUR ACTUAL MYSQL CREDENTIALS
    private static final String URL = "jdbc:mysql://localhost:3306/ecommerce";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root123";
    
    private static Connection connection = null;
    
    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("✅ Database connected!");
                
                // Create users table if not exists
                createUsersTable();
                
            } catch (Exception e) {
                System.out.println("❌ Database connection failed!");
                e.printStackTrace();
            }
        }
        return connection;
    }
    
    private static void createUsersTable() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                         "id INT PRIMARY KEY AUTO_INCREMENT," +
                         "name VARCHAR(100) NOT NULL," +
                         "email VARCHAR(100) NOT NULL," +
                         "password VARCHAR(100) NOT NULL)";
            connection.createStatement().execute(sql);
            System.out.println("✅ Users table ready!");
        } catch (SQLException e) {
            System.out.println("⚠️ Table error: " + e.getMessage());
        }
    }
    
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
