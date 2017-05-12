package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/halo?serverTimezone=CET";
    
    private SQLConnection(){}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
    }
}
