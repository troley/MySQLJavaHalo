package register;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class RegisterWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    private JLabel userlabel;
    private JLabel passlabel;
    private JPanel passpanel;
    private JPanel userpanel;
    private JTextField username;
    private JPasswordField password;
    private JButton register;

    public RegisterWindow() {
        super("Register to a database");
        setSize(300, 150);
        setLayout(new GridLayout(3, 1));
        setLocationRelativeTo(null);
        this.getContentPane().setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        componentsInit();

    }

    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {
        String passString = new String(password.getPassword());
        return passString;
    }

    private void componentsInit() {

        userlabel = new JLabel("Username:");
        passlabel = new JLabel("Password:");

        username = new JTextField("Enter username", 15);
        username.addFocusListener(new Hint("Enter username", username));
        password = new JPasswordField(15);
        password.setEchoChar((char) 0);
        password.setText("Enter password");
        password.addFocusListener(new Hint("Enter password", password));

        register = new JButton("Register");
        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!getUsername().isEmpty() && !getPassword().isEmpty()) {
                    if (!getUsername().equals("Enter username") && !getPassword().equals("Enter password")) {
                        new MemberRegister("user_credentials", getUsername(), getPassword());
                    }
                }
            }
        });

        userpanel = new JPanel(new FlowLayout());
        userpanel.add(userlabel);
        userpanel.add(username);
        passpanel = new JPanel(new FlowLayout());
        passpanel.add(passlabel);
        passpanel.add(password);
        add(userpanel);
        add(passpanel);
        add(register);
    }

    public static void main(String[] args) {
        new RegisterWindow().setVisible(true);
    }
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
