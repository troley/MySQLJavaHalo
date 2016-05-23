package database;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import login.MemberLogin;

public class DBClassLoader {

    private Statement statement;
    private ResultSet rs;
    public DBWindow window;

    String[] menuStrings;
    JMenuItem[] menuClasses;

    // constructor
    public DBClassLoader() {
        getConnAndStatement();

        window = new DBWindow();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addMenuItemsToMenu();

        window.setVisible(true);
    }

    private void getConnAndStatement() {
        try {
            MemberLogin.getConnection();
            statement = MemberLogin.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*dbTable.setModel(new javax.swing.table.DefaultTableModel(
     new Object [][] {

     },
     new String [] {
     "Student_id", "First name", "Last name", "Birthdate", "Gender", "Weight", "Length", "BMI"
     }
     ) {
     Class[] types = new Class [] {
     java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class
     };

     public Class getColumnClass(int columnIndex) {
     return types [columnIndex];
     }
     });*/
    private void addMenuItemsToMenu() {
        String query = "SELECT class_name FROM class";
        try {
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
                menuClasses[i].addActionListener(new ClassChooser(menuClasses[i]));
                window.classMenu.add(menuClasses[i]);
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    class ClassChooser implements ActionListener {

        JMenuItem menuItem;

        public ClassChooser(JMenuItem menuItem) {
            this.menuItem = menuItem;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            /*
             * Selects all students from the student table and checks their foreign keys
             * (class_name) whether they're not null and equal to the selected jMenuItem.
             * We only want to select students from the class we have chosen in the menu.
             * If all conditions are met, an object array is created where data from all 
             * the corresponding foreign key rows is stored and added to the table.
             */
            if (e.getSource() == menuItem) {
                String query = "SELECT * FROM student";
                window.getTableModel().setRowCount(0); // remove rows in the table if there are any
                window.setTitle("Groep " + menuItem.getText()); // set the class name as title of the windows

                try {
                    ResultSet rs = statement.executeQuery(query);
                    while (rs.next()) {
                        String className = rs.getString("class_name"); // store the foreign keys in className
                        if (className != null) {
                            if (className.equals(menuItem.getText())) {
                                
                                // object array where the row/s is/are stored in
                                Object[] data = {rs.getInt("id"), rs.getString("first_name"),
                                    rs.getString("surname"), rs.getDate("birthdate"), rs.getString("gender"),
                                    rs.getDouble("weight"), rs.getInt("length"), rs.getDouble("bmi")
                                };
                                
                                window.getTableModel().addRow(data); // add everything to the table

                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Column not available.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}