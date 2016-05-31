package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.spi.NamingManager;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

public class DocentMember {

    private Statement statement;
    private ResultSet rs;
    private DocentWindow docentWindow;
    private TimeTrackWindow timesWindow;

    String[] menuStrings;
    JMenuItem[] loadMenuClasses;
    JMenuItem[] timesMenuClasses;

    // constructor
    public DocentMember() {
        getConnAndStatement();
        docentWindow = new DocentWindow();
        docentWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addItemsToLoadMenus();
        
        docentWindow.setVisible(true);
    }

    private void getConnAndStatement() {
        try {
            SQLConnection.getConnection();
            statement = SQLConnection.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addItemsToLoadMenus() {
        String query = "SELECT class_name FROM class";
        try {
            rs = statement.executeQuery(query);

            rs.last(); // set ReultSet object to the last row in the database
            int size = rs.getRow(); // initialize the last row of the database to the size variable
            rs.beforeFirst(); // set ResultSet object back to it's initial position

            // initialize jMenuItems array and the (.setText) strings array lengths
            loadMenuClasses = new JMenuItem[size];
            timesMenuClasses = new JMenuItem[size];
            menuStrings = new String[size];

            // initialize the arrays
            int i = 0;
            while (i < size) {
                loadMenuClasses[i] = new JMenuItem();
                timesMenuClasses[i] = new JMenuItem();
                menuStrings[i] = new String();
                i++;
            }

            // exit menuItem ActionListener
            docentWindow.exitItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            
            // defining the arrays and setting the strings array as text to the jMenuItems array
            i = 0;
            while (rs.next()) {
                menuStrings[i] = rs.getString("class_name");
                loadMenuClasses[i].setText(menuStrings[i]);
                timesMenuClasses[i].setText(menuStrings[i]);
                loadMenuClasses[i].addActionListener(new LoadClassMenuHandler(loadMenuClasses[i]));
                timesMenuClasses[i].addActionListener(new LoadTimesMenuHandler(timesMenuClasses[i]));
                docentWindow.loadClassMenu.add(loadMenuClasses[i]);
                docentWindow.loadTimesMenu.add(timesMenuClasses[i]);
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    class LoadClassMenuHandler implements ActionListener {

        JMenuItem menuItem;

        public LoadClassMenuHandler(JMenuItem menuItem) {
            this.menuItem = menuItem;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            /*
             * Selects students from a class based on user selection in the menu.
             * If the user for example selects class "7A" then students which have
             * class 7A as their foreign key will be loaded from the DB and added
             * to the table in the window.
             */
            if (e.getSource() == menuItem) {
                String query = "SELECT * FROM student WHERE class_name = '" + menuItem.getText() + "';";
                docentWindow.getTableModel().setRowCount(0); // remove rows in the table if there are any
                docentWindow.setTitle("");
                docentWindow.setTitle(docentWindow.getTitle() + " groep " + menuItem.getText()); // set the class name as title of the windows

                try {
                    ResultSet rs = statement.executeQuery(query);
                    while (rs.next()) {
                        Object[] data = {rs.getInt("id"), rs.getString("first_name"),
                            rs.getString("surname"), rs.getDate("birthdate"), rs.getString("gender"),
                            rs.getDouble("weight"), rs.getInt("length"), rs.getDouble("bmi")
                        };
                        docentWindow.getTableModel().addRow(data); // add everything to the table
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    class LoadTimesMenuHandler implements ActionListener {

        JMenuItem menuItem;

        public LoadTimesMenuHandler(JMenuItem menuItem) {
            this.menuItem = menuItem;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            /*
             * Selects students from a class based on user selection in the menu.
             * Id's and times from the student_tijd table are selected and only
             * students from the selected class are subselected. So if user picks class
             * "7A" from the menu then the table will be filled with ids and times from class "7A".
             * 
             */
            if (e.getSource() == menuItem) {
                timesWindow = new TimeTrack();
                timesWindow.setLocationRelativeTo(docentWindow);

                String query = "SELECT * FROM student_parcours WHERE id IN (SELECT id FROM student WHERE class_name = '" + menuItem.getText() + "');";
                timesWindow.getTableModel().setRowCount(0); // remove rows in the table if there are any
                timesWindow.setTitle("");
                timesWindow.setTitle(timesWindow.getTitle() + " groep " + menuItem.getText()); // set the class name as title of the windows

                try {
                    ResultSet rs = statement.executeQuery(query);
                    while (rs.next()) {
                        Object[] data = {rs.getInt("id"), rs.getInt("score_tijd"), rs.getDate("datum"), rs.getString("school"),
                        rs.getString("docent_naam"), rs.getInt("d_code")};
                        timesWindow.getTableModel().addRow(data); // add everything to the table
                    }
                    timesWindow.setInitialRowCount(timesWindow.dbTimesTable.getRowCount());
                    timesWindow.setInitialColCount(timesWindow.dbTimesTable.getColumnCount());
                    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
