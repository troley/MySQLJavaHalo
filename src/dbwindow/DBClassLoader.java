package dbwindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DBClassLoader extends DBWindow { 
    
//    JMenu classMenu;
//    JTable dbTable;
//    JMenu editMenu;
//    JMenu fileMenu;
    
    public DBClassLoader() {
        classMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showClassesAsMenuItems();
            }
        });
    }
    
    private void showClassesAsMenuItems() {
        
    }
    
    private void connectToDb() {
        
    }
}
