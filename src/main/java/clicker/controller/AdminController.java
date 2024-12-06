package clicker.controller;

import clicker.util.NavigationUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Node;

public class AdminController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    // Handler for login
    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if ("admin".equals(username) && "admin123".equals(password)) {
            // Login successful, switch to admin menu
            Node currentNode = usernameField;
            NavigationUtil.navigateTo("/ui/admin_menu.fxml", currentNode);  // Using NavigationUtil to navigate
        } else {
            errorLabel.setText("Invalid username or password.");
        }
    }
}
