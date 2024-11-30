package ProjectOOP.Controller;
import ProjectOOP.Model.User;
import ProjectOOP.Model.Database.UserDatabase;
import ProjectOOP.View.LoginView;
import ProjectOOP.View.MainView;
import ProjectOOP.View.RegisterView;

import javax.swing.*;

public class LoginController {
    private LoginView loginView;
    private UserDatabase userDatabase;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        this.userDatabase = new UserDatabase();
        initListeners();
    }

    private void initListeners() {
        loginView.addLoginListener(e -> loginAction());
        loginView.addRegisterListener(e -> openRegisterView());
    }

    public void loginAction() {
        String username = loginView.getUsername();
        String password = loginView.getPassword();
        User user = new User(username, password);

        if (userDatabase.validateUser(user)) {
            JOptionPane.showMessageDialog(null, "Login successful!");
            loginView.frame.setVisible(false); // Ẩn LoginView
            new MainView(); // Hiển thị MainView
        } else {
            JOptionPane.showMessageDialog(null, "Invalid credentials!");
        }
    }


    public void openRegisterView() {
        loginView.close();
        RegisterView registerView = new RegisterView();
        new RegisterController(registerView, loginView, userDatabase);
    }
}
