package src;

import src.BCrypt;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberLogin {
    
    private boolean docentAuthenticated;
    private boolean onderzoekerAuthenticated;
    private String username;
    private String password;
    private String is_docent;
    private String is_onderzoeker;
    private static Connection connection;
    private Statement statement;

    public MemberLogin(String username, String password) throws SQLException {
        String query = "SELECT username, password, is_docent, is_onderzoeker FROM halo.user_credentials WHERE username = '" + username + "';";
        try {
            connection = SQLConnection.getConnection();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                this.password = rs.getString("password");
                this.username = rs.getString("username");
                this.is_docent = rs.getString("is_docent");
                this.is_onderzoeker = rs.getString("is_onderzoeker");

                if (BCrypt.checkpw(password, this.password) && this.username.equals(username) && this.is_docent.equals("j")) {
                    docentAuthenticated = true;
                }
                else if(BCrypt.checkpw(password, this.password) && this.username.equals(username) && this.is_onderzoeker.equals("j")) {
                    onderzoekerAuthenticated = true;
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean docentAuthenticated() {
        return docentAuthenticated;
    }
    
    public boolean onderzoekerAuthenticated() {
        return onderzoekerAuthenticated;
    }
}
