package clicker.controller;

import clicker.util.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class UserController {
    @FXML
    public Button viewStatsButton;
    @FXML
    public Button startTaskButton;
    @FXML
    public Button logoutButton;

    @FXML
    private void handleStartTask(ActionEvent event) {
        boolean success = NavigationUtil.navigateTo("/ui/task_page.fxml", startTaskButton);
        if (!success) {
            showErrorAlert("Failed to navigate to the Start Task screen.");
        }
    }

    @FXML
    private void handleViewStats(ActionEvent event) {
        System.out.println("Navigating to Dashboard...");
        boolean success = NavigationUtil.navigateTo("/ui/dashboard.fxml", viewStatsButton);
        if (!success) {
            showErrorAlert("Failed to navigate to the View Statistics screen.");
        }
    }


    @FXML
    private void handleLogout(ActionEvent event) {
        boolean success = NavigationUtil.navigateTo("/ui/home_screen.fxml", logoutButton);
        if (!success) {
            showErrorAlert("Failed to log out.");
        }
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.showAndWait();
    }
}
