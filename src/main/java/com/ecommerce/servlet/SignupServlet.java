package com.ecommerce.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ecommerce.db.DBConnection;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String name = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        
        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            response.sendRedirect("signup.html?error=password_mismatch");
            return;
        }
        
        try {
            Connection conn = DBConnection.getConnection();
            String query = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            
            int result = pstmt.executeUpdate();
            
            if (result > 0) {
                response.sendRedirect("login.html?signup=success");
            } else {
                response.sendRedirect("signup.html?error=failed");
            }
            
            pstmt.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("signup.html?error=duplicate");
        }
    }
}