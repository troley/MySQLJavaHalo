/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Troley
 */
public class DocentWindow extends JFrame {

    DefaultTableModel dtm;
    /*
     * Create the JFrame and its components 
     */
    public DocentWindow() {
        setLookAndFeel();
        initComponents();
        dbTableModel();
    }

    private void dbTableModel() {
        dtm = new DefaultTableModel();
        String header[] = {"Student_id", "First name", "Last name", "Birthdate", "Gender", "Weight", "Length", "BMI"};
        dtm.setColumnIdentifiers(header);
        dbTable.setModel(dtm);
    }
    
    public DefaultTableModel getTableModel() {
        return dtm;
    }
    
    // just the look and feel of the JFrame and its components
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tableScrollPane = new javax.swing.JScrollPane();
        dbTable = new javax.swing.JTable();
        menuBar = new javax.swing.JMenuBar();
        loadClassMenu = new javax.swing.JMenu();
        loadTimesMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Docent");

        dbTable.getTableHeader().setReorderingAllowed(false);
        tableScrollPane.setViewportView(dbTable);
        if (dbTable.getColumnModel().getColumnCount() > 0) {
            dbTable.getColumnModel().getColumn(0).setHeaderValue("Student_id");
            dbTable.getColumnModel().getColumn(1).setHeaderValue("First name");
            dbTable.getColumnModel().getColumn(2).setHeaderValue("Last name");
            dbTable.getColumnModel().getColumn(3).setHeaderValue("Birthdate");
            dbTable.getColumnModel().getColumn(4).setHeaderValue("Gender");
            dbTable.getColumnModel().getColumn(5).setHeaderValue("Weight");
            dbTable.getColumnModel().getColumn(6).setHeaderValue("Length");
            dbTable.getColumnModel().getColumn(7).setHeaderValue("BMI");
        }

        menuBar.setAlignmentY(0.4761905F);
        menuBar.setPreferredSize(new java.awt.Dimension(139, 30));

        loadClassMenu.setText("Load class");
        menuBar.add(loadClassMenu);

        loadTimesMenu.setText("Load times");
        menuBar.add(loadTimesMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1293, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JTable dbTable;
    javax.swing.JMenu loadClassMenu;
    javax.swing.JMenu loadTimesMenu;
    javax.swing.JMenuBar menuBar;
    private javax.swing.JScrollPane tableScrollPane;
    // End of variables declaration//GEN-END:variables
}