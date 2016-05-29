package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;

public class TimeTrack extends TimeTrackWindow {

    Statement statement;
    
    public TimeTrack() {
        getConnAndStatement();
        newRowItem.addActionListener(new FileMenuHandler());
        deleteRowItem.addActionListener(new FileMenuHandler());
    }

    private void getConnAndStatement() {
        try {
            SQLConnection.getConnection();
            statement = SQLConnection.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    class FileMenuHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if (e.getSource() == newRowItem) {
                getTableModel().addRow(new Object[]{"", ""});
            }

            
            
            if (e.getSource() == deleteRowItem) {
                dbTimesTable.editCellAt(dbTimesTable.getSelectedRow() - 1, dbTimesTable.getSelectedColumn() - 1);

                int selectedRows = dbTimesTable.getSelectedRows().length;
                for (int i = 0; i < selectedRows; i++) {
                    getTableModel().removeRow(dbTimesTable.getSelectedRow());
                }
            }
            
            
            if(e.getSource() == saveDataItem) {
                // code om de tijden in de database op te slaan
            }
        }
    }
    
}
