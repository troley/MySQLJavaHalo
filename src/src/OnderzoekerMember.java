package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OnderzoekerMember {

    OnderzoekerWindow window;
    
    private Statement statement;
    private ResultSet rs;

    String[] menuStrings;
    JMenuItem[] menuClasses;

    String selectedSchool;
    String selectedClass;

    
    public OnderzoekerMember() {
        getConnAndStatement();
        window = new OnderzoekerWindow();
        window.setTitle("Onderzoeker");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addMenuItemsToMenu();  
        window.loadClassMenu.setVisible(false);
        window.setVisible(true);
    }



    
     private void getConnAndStatement() {
        try {
            SQLConnection.getConnection();
            statement = SQLConnection.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addMenuItemsToMenu() {
        String query = "SELECT schoolName FROM school";
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
                menuStrings[i] = rs.getString("schoolName");
                menuClasses[i].setText(menuStrings[i]);
                menuClasses[i].addActionListener(new ClassChooser(menuClasses[i]));
                window.schoolMenu.add(menuClasses[i]);
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
            for(int i = 0; i < window.loadClassMenu.getItemCount(); i++) {
                if (window.loadClassMenu.getItem(i).getText().equals(e.getActionCommand())) {
                    selectedClass = e.getActionCommand();
                }
            }

            if(checkIfLoadClassButtonIsVisible()) {
                loadStudents(e);
            }

            for(int i = 0; i < window.schoolMenu.getItemCount(); i++) {
                if(window.schoolMenu.getItem(i).getText().equals(e.getActionCommand())) {
                    selectedSchool = e.getActionCommand();

                    try {
                        window.loadClassMenu.removeAll();
                        setClassNamesInMenuItem();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }


        }
        
        public boolean checkIfLoadClassButtonIsVisible() {
            if(window.loadClassMenu.isVisible()) {
                return true;
            }
            return false;
        }
        
        public void setClassNamesInMenuItem() throws SQLException {
            String query = "SELECT class_name FROM class WHERE school = '" + selectedSchool + "'";
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
                    window.loadClassMenu.add(menuClasses[i]);
                    i++;
                }
                window.loadClassMenu.setVisible(true);
                window.setTitle("School " + selectedSchool); // set the class name as title of the windows
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }


        public void loadStudents(ActionEvent e) {
           /*
             * Selects all students from the student table and checks their foreign keys
             * (class_name) whether they're not null and equal to the selected jMenuItem.
             * We only want to select students from the class we have chosen in the menu.
             * If all conditions are met, an object array is created where data from all 
             * the corresponding foreign key rows is stored and added to the table.
             */
            if (e.getSource() == menuItem) {
                String query = "SELECT SP.score_tijd, SP.datum, SP.docent_naam, S.gender, S.bmi, C.class_name FROM student S JOIN student_parcours SP ON SP.id = S.id JOIN class C ON C.class_name = S.class_name WHERE C.class_name = '" + selectedClass + "'";
                window.getTableModel().setRowCount(0); // remove rows in the table if there are any
                window.setTitle("School " + selectedSchool + " groep " + selectedClass); // set the class name as title of the windows

                try {
                    ResultSet rs = statement.executeQuery(query);
                    while (rs.next()) {
                        String className = rs.getString("class_name"); // store the foreign keys in className
                        if (className != null) {
                            if (className.equals(menuItem.getText())) {
                                
                                // object array where the row/s is/are stored in
                                Object[] data = {rs.getInt("score_tijd"), rs.getDate("datum"),
                                    rs.getString("docent_naam"), rs.getString("gender"), rs.getString("bmi")
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
