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
        NavigationUtil.navigateTo("ui/login.fxml", "User Login");
    }

    @FXML
    public void handleAdmin(ActionEvent event) {
        NavigationUtil.navigateTo("ui/login.fxml", "Admin Login");
    }

    @FXML
    public void handleExit(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
