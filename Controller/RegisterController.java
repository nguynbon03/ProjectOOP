package ProjectOOP.Controller;

import ProjectOOP.Model.User;
import ProjectOOP.Model.Database.UserDatabase;
import ProjectOOP.View.LoginView;
import ProjectOOP.View.RegisterView;

import javax.swing.*;

public class RegisterController {
    private RegisterView registerView;
    private LoginView loginView;
    private UserDatabase userDatabase;

    public RegisterController(RegisterView registerView, LoginView loginView, UserDatabase userDatabase) {
        this.registerView = registerView;
        this.loginView = loginView;
        this.userDatabase = userDatabase;
        initListeners();
    }

    private void initListeners() {
        registerView.addRegisterListener(e -> registerAction());
        registerView.addBackListener(e -> backToLogin());
    }

    public void registerAction() {
        String username = registerView.getUsername();
        String password = registerView.getPassword();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username and password cannot be empty!");
            return;
        }

        if (userDatabase.registerUser(new User(username, password))) {
            JOptionPane.showMessageDialog(null, "Registration successful!");
        } else {
            JOptionPane.showMessageDialog(null, "Registration failed!");
        }
    }

    public void backToLogin() {
        String registeredUsername = registerView.getUsername();
        String registeredPassword = registerView.getPassword();

        registerView.close(); // Đóng màn hình RegisterView
        loginView.setCredentials(registeredUsername, registeredPassword); // Điền thông tin vào LoginView
        loginView.frame.setVisible(true); // Hiển thị lại LoginView
    }
}
