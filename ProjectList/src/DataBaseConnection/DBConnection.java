package DataBaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	 private static final String URL = "jdbc:mysql://localhost:3306/studentdb";
	    private static final String USER = "root"; 
	    private static final String PASSWORD = "admin";  

	    public static Connection getConnection() {
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            return DriverManager.getConnection(URL, USER, PASSWORD);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException("Database connection failed!");
	        }
	    }
	
	  
	}

