package dbwindow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JMenuItem;

public class DBClassLoader extends DBWindow {

//    JMenu classMenu;
//    JTable dbTable;
//    JMenu editMenu;
//    JMenu fileMenu;
    private final String USERNAME = "root";
    private final String PASSWORD = "rene123";
    private final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/user_information";

    private Connection connection;
    private Statement statement;
    private ResultSet rs;

    String[] menuStrings;
    JMenuItem[] menuClasses;
    
    // constructor
    public DBClassLoader() {
        connectToDb(); // connect to database before windows is created

        try {
            addMenuItemsToMenu();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        super.setVisible(true);
    }

    // connect to database
    private void connectToDb() {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addMenuItemsToMenu() throws SQLException {
        String query = "SELECT class_name FROM class";
        rs = statement.executeQuery(query);

        rs.last(); // set ReultSet object to the last row in the database
        int size = rs.getRow(); // initialize the last row of the database to the size variable
        rs.beforeFirst(); // set ResultSet object back to it's initial position

        // initialize jMenuItems array and the (.setText) strings array lengths
        menuClasses = new JMenuItem[size];
        menuStrings = new String[size];

        // initialize the arrays
        int i = 0;
        while (i < size) {
            menuClasses[i] = new JMenuItem();
            menuStrings[i] = new String();
            i++;
        }

        // defining the arrays and setting the strings array as text to the jMenuItems array
        i = 0;
        while (rs.next()) {
            menuStrings[i] = rs.getString("class_name");
            menuClasses[i].setText(menuStrings[i]);
            classMenu.add(menuClasses[i]);
            i++;
        }
    }
}
