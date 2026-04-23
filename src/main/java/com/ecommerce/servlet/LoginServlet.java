package com.ecommerce.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.ecommerce.db.DBConnection;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT * FROM users WHERE name = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("user_id", rs.getInt("id"));
                session.setAttribute("user_name", rs.getString("name"));
                response.sendRedirect("products.html");
            } else {
                response.sendRedirect("login.html?error=invalid");
            }
            
            rs.close();
            pstmt.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("login.html?error=failed");
        }
    }
}