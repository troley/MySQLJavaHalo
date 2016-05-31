package src;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class TimeTrackWindow extends JFrame {

    private int initialRowCount;
    private int initialColCount;

    DefaultTableModel dtm;

    public TimeTrackWindow() {
        setLookAndFeel();
        initComponents();
        dbTableModel();

        setVisible(true);
    }

    private void dbTableModel() {
        dtm = new DefaultTableModel();
        String header[] = {"Student_id", "Score_tijd", "Datum", "School", "Docent naam", "Docent code"};
        dtm.setColumnIdentifiers(header);
        dbTimesTable.setModel(dtm);
    }

    private void setLookAndFeel() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DocentWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DocentWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DocentWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DocentWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public int getInitialRowCount() {
        return initialRowCount;
    }

    public void setInitialRowCount(int initialRowCount) {
        this.initialRowCount = initialRowCount;
    }

    public int getInitialColCount() {
        return initialColCount;
    }

    public void setInitialColCount(int initialColCount) {
        this.initialColCount = initialColCount;
    }

    public DefaultTableModel getTableModel() {
        return dtm;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        timesTable = new javax.swing.JScrollPane();
        dbTimesTable = new javax.swing.JTable();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newRowItem = new javax.swing.JMenuItem();
        deleteRowItem = new javax.swing.JMenuItem();
        menuSeparator1 = new javax.swing.JPopupMenu.Separator();
        saveDataItem = new javax.swing.JMenuItem();
        menuSeparator2 = new javax.swing.JPopupMenu.Separator();
        exitItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Class");
        setLocation(new java.awt.Point(0, 0));

        dbTimesTable.setToolTipText("");
        dbTimesTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        timesTable.setViewportView(dbTimesTable);
        if (dbTimesTable.getColumnModel().getColumnCount() > 0) {
            dbTimesTable.getColumnModel().getColumn(0).setHeaderValue("Student Id");
            dbTimesTable.getColumnModel().getColumn(1).setHeaderValue("Time");
        }

        menuBar.setPreferredSize(new java.awt.Dimension(64, 30));

        fileMenu.setText("File");

        newRowItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newRowItem.setText("New row");
        fileMenu.add(newRowItem);

        deleteRowItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        deleteRowItem.setText("Delete row");
        fileMenu.add(deleteRowItem);
        fileMenu.add(menuSeparator1);

        saveDataItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveDataItem.setText("Save data");
        fileMenu.add(saveDataItem);
        fileMenu.add(menuSeparator2);

        exitItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        exitItem.setText("Exit");
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(timesTable, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(timesTable, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JTable dbTimesTable;
    javax.swing.JMenuItem deleteRowItem;
    javax.swing.JMenuItem exitItem;
    javax.swing.JMenu fileMenu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPopupMenu.Separator menuSeparator1;
    private javax.swing.JPopupMenu.Separator menuSeparator2;
    javax.swing.JMenuItem newRowItem;
    javax.swing.JMenuItem saveDataItem;
    private javax.swing.JScrollPane timesTable;
    // End of variables declaration//GEN-END:variables
}
