package clicker.controller;

import clicker.util.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeScreenController {

    @FXML
    private Button loginButton;

    @FXML
    private Button adminButton;

    @FXML
    private Button exitButton;

    @FXML
    public void handleLogin(ActionEvent event) {
        if (!NavigationUtil.navigateTo("/ui/login.fxml", loginButton)) {
            System.err.println("Failed to load login.fxml");
        }
    }

    @FXML
    public void handleAdmin(ActionEvent event) {
        if (!NavigationUtil.navigateTo("/ui/admin_login.fxml", adminButton)) {
            System.err.println("Failed to load admin_login.fxml");
        }
    }

    @FXML
    public void handleExit(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
