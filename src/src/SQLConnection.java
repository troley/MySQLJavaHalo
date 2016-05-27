package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {

    private static final String USERNAME = "halo";
    private static final String PASSWORD = "8.2Gax5q#";
    private static final String CONNECTION_STRING = "jdbc:mysql://149.210.228.109/halo";
    
    private SQLConnection(){}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
    }
}
