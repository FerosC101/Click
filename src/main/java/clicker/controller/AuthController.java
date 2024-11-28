package clicker.controller;

import clicker.connection.DatabaseConnection;
import clicker.util.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthController {

    @FXML
    public Button loginButton;
    @FXML
    public Hyperlink signupLink;
    @FXML
    public Button signupButton;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin(ActionEvent event) {
        if (usernameField == null || passwordField == null) {
            System.err.println("FXML elements are not properly initialized.");
            return;
        }

        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Username or password cannot be empty.", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                NavigationUtil.navigateTo("src/main/resources/ui/home_screen.fxml", loginButton);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid username or password.", ButtonType.OK);
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Unexpected error: " + e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void handleSignUp(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Username or password cannot be empty.", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String checkQuery = "SELECT * FROM users WHERE username = ?";
            
            PreparedStatement stmt = conn.prepareStatement(checkQuery);
            stmt.setString(1, username);
            
            if (stmt.executeQuery().next()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Username already exists.", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            
            String insertQuery = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
            insertStmt.setString(1, username);
            insertStmt.setString(2, password);
            
            int rowsAffected = insertStmt.executeUpdate();
            if (rowsAffected > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "User has been registered.", ButtonType.OK);
                alert.showAndWait();

                NavigationUtil. navigateTo("/ui/login.fxml", signupButton);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to Create Account. Please try again.", ButtonType.OK);
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Unexpected error: " + e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void openLogin(ActionEvent event) {
        NavigationUtil.navigateTo("src/main/resources/ui/login.fxml", loginButton);
    }
}
