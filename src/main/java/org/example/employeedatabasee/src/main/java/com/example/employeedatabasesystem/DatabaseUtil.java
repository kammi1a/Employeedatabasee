package com.example.employeedatabasee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    private static final String URL = "jdbc:postgresql://localhost:5432/employee_db";
    private static final String USER = "postgres";  
    private static final String PASSWORD = "123456"; 

    public static Connection getConnection() throws SQLException {
        try {

          Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("PostgreSQL driver not found.");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void closeResources(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            try {
                if (resource != null) {
                    resource.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
