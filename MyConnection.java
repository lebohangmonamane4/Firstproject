package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MyConnection {
    private static Connection con = null;
    public static Connection getConnection()
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/2025053lebohangmonamane", "root", "");
        }
        catch(ClassNotFoundException | SQLException e)
        {
            e.getMessage();
        }
        return con;
        
    }  
}
