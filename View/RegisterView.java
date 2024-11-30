package ProjectOOP.View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RegisterView {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton backButton;

    public RegisterView() {
        frame = new JFrame("Register");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        registerButton = new JButton("Register");
        backButton = new JButton("Back to Login");

        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(registerButton);
        frame.add(backButton);

        frame.setVisible(true);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public void addRegisterListener(ActionListener listener) {
        registerButton.addActionListener(listener);
    }

    public void addBackListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    public void close() {
        frame.dispose();
    }
}
