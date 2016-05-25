package onderzoekers;

import javax.swing.JFrame;

public class OnderzoekerMember {

    OnderzoekerWindow window;
    
    public OnderzoekerMember() {
        window = new OnderzoekerWindow();
        window.setTitle("Onderzoeker");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        window.setVisible(true);
    }
}
