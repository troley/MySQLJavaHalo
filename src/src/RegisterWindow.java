package src;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ButtonGroup;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class RegisterWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    private JLabel userLabel;
    private JLabel passLabel;
    private JLabel docentLabel;
    private JLabel onderzoekerLabel;
    private JPanel passPanel;
    private JPanel userPanel;
    private JPanel docentPanel;
    private JPanel onderzoekerPanel;
    private JTextField username;
    private JPasswordField password;
    private JButton register;
    private JRadioButton docentCheck;
    private JRadioButton onderzoekerCheck;
    private ButtonGroup checkboxGroup;

    public RegisterWindow() {
        super("Register member");
        setSize(280, 165);
        setLayout(new GridLayout(5, 1));
        setLocationRelativeTo(null);
        this.getContentPane().setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        componentsInit();

        this.setVisible(true);
    }

    public boolean getDocentSelectedStatus() {
        return docentCheck.isSelected();
    }

    public boolean getOnderzoekerSelectedStatus() {
        return onderzoekerCheck.isSelected();
    }

    public String getUsernameText() {
        return username.getText();
    }

    public String getPasswordText() {
        String passString = new String(password.getPassword());
        return passString;
    }

    private void componentsInit() {

        userLabel = new JLabel("Username:");
        passLabel = new JLabel("Password:");
        docentLabel = new JLabel("Docent");
        onderzoekerLabel = new JLabel("Onderzoeker");

        username = new JTextField("Enter username", 15);
        username.addFocusListener(new Hint("Enter username", username));
        password = new JPasswordField(15);
        password.setEchoChar((char) 0);
        password.setText("Enter password");
        password.addFocusListener(new Hint("Enter password", password));

        docentCheck = new JRadioButton();
        onderzoekerCheck = new JRadioButton();
        checkboxGroup = new ButtonGroup();
        checkboxGroup.add(docentCheck);
        checkboxGroup.add(onderzoekerCheck);

        register = new JButton("Register");
        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!getUsernameText().isEmpty() && !getPasswordText().isEmpty()) {
                    if (!getUsernameText().equals("Enter username") && !getPasswordText().equals("Enter password")) {
                        if (getOnderzoekerSelectedStatus() ^ getDocentSelectedStatus()) {
                            new MemberRegister("user_credentials", getUsernameText(), getPasswordText(), getDocentSelectedStatus(), getOnderzoekerSelectedStatus());
                        }
                    }
                }
            }
        });

        userPanel = new JPanel(new FlowLayout());
        userPanel.add(userLabel);
        userPanel.add(username);
        passPanel = new JPanel(new FlowLayout());
        passPanel.add(passLabel);
        passPanel.add(password);
        docentPanel = new JPanel(new FlowLayout());
        docentPanel.add(docentLabel);
        docentPanel.add(docentCheck);
        onderzoekerPanel = new JPanel(new FlowLayout());
        onderzoekerPanel.add(onderzoekerLabel);
        onderzoekerPanel.add(onderzoekerCheck);
        add(userPanel);
        add(passPanel);
        add(docentPanel);
        add(onderzoekerPanel);
        add(register);
    }

    class Hint extends FocusAdapter {

        private String text;
        private JTextComponent field;

        public Hint(String text, JTextComponent field) {
            this.text = text;
            this.field = field;
        }

        @Override
        public void focusGained(FocusEvent arg0) {
            if (field.getText().equals("Enter username") || field.getText().equals("Enter password")) {
                if (field instanceof JPasswordField) {
                    ((JPasswordField) field).setEchoChar('*');
                }
                field.setText("");
            }
        }

        @Override
        public void focusLost(FocusEvent arg0) {
            if (field.getText().isEmpty()) {
                if (field instanceof JPasswordField) {
                    ((JPasswordField) field).setEchoChar((char) 0);
                    field.setText(text);
                } else {
                    field.setText(text);
                }
            }
        }
    }
}
