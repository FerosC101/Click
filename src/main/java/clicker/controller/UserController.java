package clicker.controller;

import clicker.util.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
        boolean success = NavigationUtil.navigateTo("/ui/task_screen.fxml", (Node) event.getSource());
        if (!success) {
            showErrorAlert("Failed to navigate to the Start Task screen.");
        }
    }

    @FXML
    private void handleViewStats(ActionEvent event) {

        boolean success = NavigationUtil.navigateTo("/ui/stats_screen.fxml", (Node) event.getSource());
        if (!success) {
            showErrorAlert("Failed to navigate to the View Statistics screen.");
        }
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        boolean success = NavigationUtil.navigateTo("/ui/login.fxml", (Node) event.getSource());
        if (!success) {
            showErrorAlert("Failed to log out.");
        }
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.showAndWait();
    }
}
