package com.example.Bookstore_management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {

    public static Connection connectDb() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/book", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            // Rethrow the exception to let the caller handle it
            throw new SQLException("Error connecting to the database", e);
        }
    }
}
