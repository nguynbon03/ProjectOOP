package ProjectOOP;


import ProjectOOP.Controller.LoginController;
import ProjectOOP.View.LoginView;

public class Main {
    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        LoginController loginController = new LoginController(loginView);
    }
}
