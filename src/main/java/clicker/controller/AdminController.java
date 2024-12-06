package clicker.controller;

import clicker.util.NavigationUtil;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AdminController {
    @FXML
    private Hyperlink returnlink;
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if ("admin".equals(username) && "admin123".equals(password)) {
            System.out.println("Admin Login Successful!");
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    @FXML
    private void returnToHome(javafx.event.ActionEvent event) {
        NavigationUtil.navigateTo("/ui/home.screen.fxml", returnlink);
    }
}
