package clicker.controller;

import clicker.connection.TaskStatsDAO;
import clicker.model.TaskStats;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class DashboardController {

    @FXML
    private Label avgClicksLabel; // Label to display average clicks
    @FXML
    private Label avgTimeLabel;  // Label to display average time spent
    @FXML
    private BarChart<String, Number> barChart; // Bar chart displaying clicks by task
    @FXML
    private LineChart<String, Number> timeLineChart; // Line chart displaying time trends over tasks
    @FXML
    private Button returnButton; // Button to navigate back to the User Menu

    private final int currentUserId = 1; // Replace with dynamic user ID when implementing user sessions

    @FXML
    private void initialize() {
        try {
            loadAverages(); // Load average metrics for the current user
            loadBarChart(); // Populate bar chart with click data
            loadTimeLineChart(); // Populate line chart with time spent data
        } catch (Exception e) {
            showErrorAlert("An error occurred while loading dashboard data.");
            e.printStackTrace();
        }
    }

    /**
     * Loads and displays the average clicks and time spent for the current user.
     */
    private void loadAverages() {
        TaskStatsDAO statsDAO = new TaskStatsDAO();
        try {
            double avgClicks = statsDAO.getAverageClicks(currentUserId);
            double avgTime = statsDAO.getAverageTimeSpent(currentUserId);

            avgClicksLabel.setText(String.format("%.2f", avgClicks));
            avgTimeLabel.setText(String.format("%.2f", avgTime / 1000)); // Convert ms to seconds
        } catch (Exception e) {
            showErrorAlert("Failed to load average metrics.");
            e.printStackTrace();
        }
    }

    /**
     * Populates the bar chart with the number of clicks for each task.
     */
    private void loadBarChart() {
        TaskStatsDAO statsDAO = new TaskStatsDAO();
        try {
            List<TaskStats> statsList = statsDAO.getUserStats(currentUserId);

            if (statsList.isEmpty()) {
                System.out.println("No data available for bar chart.");
                return;
            }

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Clicks by Task");

            for (TaskStats stats : statsList) {
                series.getData().add(new XYChart.Data<>("Task " + stats.getTaskId(), stats.getClickCount()));
            }

            barChart.getData().clear(); // Clear previous data
            barChart.getData().add(series); // Add new data
        } catch (Exception e) {
            showErrorAlert("Failed to load bar chart data.");
            e.printStackTrace();
        }
    }

    /**
     * Populates the line chart with time spent on each task.
     */
    private void loadTimeLineChart() {
        TaskStatsDAO statsDAO = new TaskStatsDAO();
        try {
            List<TaskStats> statsList = statsDAO.getUserStats(currentUserId);

            if (statsList.isEmpty()) {
                System.out.println("No data available for line chart.");
                return;
            }

            XYChart.Series<String, Number> timeSeries = new XYChart.Series<>();
            timeSeries.setName("Time Spent by Task");

            for (TaskStats stats : statsList) {
                timeSeries.getData().add(new XYChart.Data<>("Task " + stats.getTaskId(), stats.getTimeSpent() / 1000)); // Convert ms to seconds
            }

            timeLineChart.getData().clear(); // Clear previous data
            timeLineChart.getData().add(timeSeries); // Add new data
        } catch (Exception e) {
            showErrorAlert("Failed to load line chart data.");
            e.printStackTrace();
        }
    }

    /**
     * Handles the navigation back to the User Menu screen.
     */
    @FXML
    private void handleReturnToUserMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/user_menu.fxml"));
            AnchorPane userMenu = loader.load();

            Scene currentScene = returnButton.getScene();
            currentScene.setRoot(userMenu);
        } catch (Exception e) {
            showErrorAlert("Failed to navigate back to the User Menu.");
            e.printStackTrace();
        }
    }

    /**
     * Displays an error alert with a specified message.
     *
     * @param message The message to display in the alert.
     */
    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.showAndWait();
    }
}
