package register;

import hashpassword.BCrypt;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JCheckBox;

public class MemberRegister {

    private final String USERNAME = "root";
    private final String PASSWORD = "rene123";
    private final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/user_information";

    private Connection connection;
    private Statement command;

    private SecureRandom securerand;

    public MemberRegister(String table, String username, String password, boolean isDocent, boolean isOnderzoeker) {
        char is_docent = 'n';
        char is_onderzoeker = 'n';
        
        if (isDocent) {
            is_docent = 'j';
        } else if (isOnderzoeker) {
            is_onderzoeker = 'j';
        }

        if (isDocent || isOnderzoeker) {
            try {
                securerand = new SecureRandom();
                securerand.nextBytes(new byte[32]);
                password = BCrypt.hashpw(password, BCrypt.gensalt(12, securerand));
                addValue(table, username, password, is_docent, is_onderzoeker);
                password = "";
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void addValue(String table, String username, String password, char is_docent, char is_onderzoeker) throws SQLException {
        connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
        command = connection.createStatement();
        command.execute("INSERT INTO " + table + " VALUES ('" + username + "', '" + password + "', '" + is_docent + "', '" + is_onderzoeker + "')");
    }
}
