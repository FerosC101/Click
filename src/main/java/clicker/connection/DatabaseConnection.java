package clicker.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/click";
    private static final String USER = "root";
    private static final String PASSWORD = "426999";

    public static Connection getConnection() throws SQLException {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to database");
        } catch (SQLException e) {
            System.err.println("Error occurred while establishing connection: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for better visibility of the error
        }
        return conn;
    }

    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = getConnection();  // Try getting a connection
            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to connect. Connection is null.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
