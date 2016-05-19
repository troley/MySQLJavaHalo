package loginwindow;

import hashpassword.BCrypt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberLogin {

    private final String USERNAME = "root";
    private final String PASSWORD = "rene123";

    private boolean authenticated;
    private String username;
    private String password;
    private String connectionString;
    private Connection connection;
    private Statement statement;

    public MemberLogin(String username, String password) throws SQLException {
        connectionString = "jdbc:mysql://localhost:3306/user_information";
        String query = "SELECT username, password FROM user_information.user_credentials WHERE username = '" + username + "';";
        try {
            connection = DriverManager.getConnection(connectionString, USERNAME, PASSWORD);
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                this.password = rs.getString("password");
                this.username = rs.getString("username");

                if (BCrypt.checkpw(password, this.password) && this.username.equals(username)) {
                    System.out.println(password);
                    System.out.println(this.password);
                    authenticated = true;
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isAuthenticated() {
        return authenticated;
    }
}
