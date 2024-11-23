package clicker.controller;

import clicker.connection.DatabaseConnection;
import clicker.util.NavigationUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthController {

    public Button loginButton;
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private Stage getCurrentStage() {
        return (Stage) usernameField.getScene().getWindow();
    }

    @FXML
    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                loadFXML("/clicker/ui/home_screen.fxml");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid username or password.", ButtonType.OK);
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSignUp(ActionEvent event) {
        // Use the NavigationUtil to switch to the signup page
        NavigationUtil.navigateTo("/ui/signup.fxml", (Node) event.getSource());
        System.out.println("Navigated to Sign Up page.");
    }

    @FXML
    public void openSignUp() {
        loadFXML("/clicker/ui/signup.fxml");
    }

    @FXML
    public void openLogin() {
        loadFXML("/clicker/ui/login.fxml");
    }

    private void loadFXML(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            getCurrentStage().setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
