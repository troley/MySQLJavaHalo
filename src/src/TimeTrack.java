package src;

import com.sun.deploy.uitoolkit.impl.fx.ui.FXUIFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class TimeTrack extends TimeTrackWindow {

    ArrayList deletedRows;
    TimeTrackWindow timesWindow;
    PreparedStatement statement;
    ResultSet rs;

    // add arraylist to store removed rows and then drop them from the table
    public TimeTrack() {
        getConnection();
        deletedRows = new ArrayList<>();
        newRowItem.addActionListener(new FileMenuHandler());
        deleteRowItem.addActionListener(new FileMenuHandler());
        saveDataItem.addActionListener(new FileMenuHandler());
    }

    public ArrayList getDeletedRows() {
        return deletedRows;
    }

    public void setDeletedRows(Object row) {
        this.deletedRows.add(row);
    }

    private void getConnection() {
        try {
            SQLConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    class FileMenuHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            // add new rows to table
            if (e.getSource() == newRowItem) {
                String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
                getTableModel().addRow(new Object[]{"", "", currentDate, "", "", ""});
            }

            // remove rows from table
            if (e.getSource() == deleteRowItem) {
                dbTimesTable.editCellAt(dbTimesTable.getSelectedRow() - 1, dbTimesTable.getSelectedColumn() - 1); // om een tabel bug te voorkomen

                int selectedRows = dbTimesTable.getSelectedRows().length;
                for (int i = 0; i < selectedRows; i++) {
                    setDeletedRows(dbTimesTable.getValueAt(dbTimesTable.getSelectedRow(), 0));
                    setDeletedRows(dbTimesTable.getValueAt(dbTimesTable.getSelectedRow(), 2));
                    getTableModel().removeRow(dbTimesTable.getSelectedRow());
                }
                try {
                    dropFromTable("student_parcours", statement, getDeletedRows());
                } catch (SQLException ex) {
                    Logger.getLogger(TimeTrack.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            // insert into "table" .... query
            if (e.getSource() == saveDataItem) {
                try {
                    insertIntoTable("student_parcours", dbTimesTable, statement, getInitialRowCount(), 0);
                    JOptionPane.showMessageDialog(getContentPane(), "Successfully saved data.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(getContentPane(), "Failed to save data.", "Error", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(TimeTrack.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void dropFromTable(String mysqlTable, PreparedStatement statement, ArrayList deletedRows) throws SQLException {
        int listLength = getDeletedRows().size();

        for (int i = 0; i < listLength; i++) {
            String query = "DELETE FROM " + mysqlTable + " WHERE id = " + deletedRows.get(i) + "and datum = '" + deletedRows.get(i + 1) + "';";
            statement = SQLConnection.getConnection().prepareStatement(query);
            statement.executeUpdate(query);
        }
    }

    /**
     * In deze functie geef je de MySQL tabel mee waar je een INSERT INTO query
     * op uit wilt voeren, een tabel waaruit de gegevens uit moeten worden
     * gelezen en een begin rij (vanaf welke de gegevens moeten worden
     * uitgelezen) en een start kolom (meestal zal dit 0 zijn doordat je bij de
     * eerste value van die rij wilt beginnen).
     *
     * @param mysqlTable de MySQL tabel waar op je de query wilt uitvoeren.
     * @param table de JTable waar je de gegevens uit wilt lezen.
     * @param statement de PreparedStatement die de update zal executen.
     * @param startRow het begin rij waar de iterator bij zal beginnen.
     * @param startCol het begin kolom waar de iterator bij zal beginnen.
     */
    private void insertIntoTable(String mysqlTable, JTable table, PreparedStatement statement, int startRow, int startCol) throws SQLException {
        int lastRow = table.getRowCount();
        int lastCol = table.getColumnCount();
        String query = "INSERT INTO " + mysqlTable + " VALUES ";

        for (int i = startRow; i < lastRow; i++) { // zolang i kleiner is dan de laatste rij van de tabel, itereer
            query += "(";
            for (int j = startCol; j < lastCol; j++) { // zolang j kleiner is dan de laatste kolom van de tabel, itereer
                if (j == table.getColumnCount() - 1) { // als j bij de laatste kolom is (van de rij)
                    if (i == table.getRowCount() - 1) { // als i bij de laatste kolom EN row is (laatste value in de tabel)
                        query += "'" + table.getValueAt(i, j) + "');";
                        break;
                    }
                    query += "'" + table.getValueAt(i, j) + "'), ";
                } else // anders als j NIET bij de laatste kolom van de rijs is (gewoon , en doorgaan)
                {
                    query += "'" + table.getValueAt(i, j) + "', ";
                }
            }
        }

        statement = SQLConnection.getConnection().prepareStatement(query);
        statement.executeUpdate(query);
    }
}
