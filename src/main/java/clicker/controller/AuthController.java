package clicker.controller;

import clicker.connection.UserDAO;
import clicker.model.User;
import clicker.util.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AuthController {
    @FXML
    private Button loginButton;
    @FXML
    private Hyperlink signupLink;
    @FXML
    private Button signupButton;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    private final UserDAO userDAO = new UserDAO();

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Username or password cannot be empty.");
            return;
        }

        User user = userDAO.verifyUser(username, password);
        if (user != null) {
            TaskController.setCurrentUserId(user.getId());
            NavigationUtil.navigateTo("/ui/user_menu.fxml", loginButton);
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
        }
    }

    @FXML
    private void handleSignUp(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Username or password cannot be empty.");
            return;
        }

        if (userDAO.isUsernameTaken(username)) {
            showAlert(Alert.AlertType.WARNING, "Signup Failed", "Username already exists.");
            return;
        }

        if (userDAO.createUser(username, password)) {
            showAlert(Alert.AlertType.INFORMATION, "Signup Successful", "Account created successfully!");
            NavigationUtil.navigateTo("/ui/home_screen.fxml", signupButton);
        } else {
            showAlert(Alert.AlertType.ERROR, "Signup Failed", "An error occurred during signup. Please try again.");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type, message, ButtonType.OK);
        alert.setTitle(title);
        alert.showAndWait();
    }

    public void returntohome(ActionEvent event) {
    }
}
