package clicker.controller;

import clicker.util.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeScreenController {

    @FXML
    private Button loginButton;

    @FXML
    private Button adminButton;

    @FXML
    private Button exitButton;

    /**
     * Replace current window content with the User Login screen.
     */
    @FXML
    public void handleLogin(ActionEvent event) {
        NavigationUtil.navigateTo("ui/login.fxml", loginButton);
    }


    /**
     * Replace current window content with the Admin Login screen.
     */
    @FXML
    public void handleAdmin(ActionEvent event) {
        NavigationUtil.navigateTo("/ui/login.fxml", adminButton);
    }

    /**
     * Close the application.
     */
    @FXML
    public void handleExit(ActionEvent event) {
        System.exit(0); // Exit the application
    }
}
