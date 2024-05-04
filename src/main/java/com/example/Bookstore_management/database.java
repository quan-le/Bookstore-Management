package com.example.Bookstore_management;

import java.sql.DriverManager;
import java.sql.Connection;

public class database {

    public static Connection connectDb() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/book", "root", "");
            return connect;

        }catch(Exception e) {e.printStackTrace();}
        return null;
    }
}
