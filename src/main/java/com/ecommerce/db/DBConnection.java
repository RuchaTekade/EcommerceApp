package com.ecommerce.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    // Railway automatically injects these environment variables!
    private static final String URL = System.getenv("MYSQL_URL");
    private static final String USERNAME = System.getenv("MYSQLUSER");
    private static final String PASSWORD = System.getenv("MYSQLPASSWORD");
    
    private static Connection connection = null;
    
    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("✅ Connected to Railway MySQL database!");
                
                // Create users table automatically
                createUsersTable();
                
            } catch (Exception e) {
                System.out.println("❌ Database connection failed!");
                System.out.println("Error: " + e.getMessage());
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
                         "password VARCHAR(100) NOT NULL" +
                         ")";
            connection.createStatement().execute(sql);
            System.out.println("✅ Users table ready!");
        } catch (SQLException e) {
            System.out.println("⚠️ Could not create users table: " + e.getMessage());
        }
    }
    
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
