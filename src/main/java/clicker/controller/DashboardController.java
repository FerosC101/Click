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
    private Label avgClicksLabel;
    @FXML
    private Label avgTimeLabel;
    @FXML
    private BarChart<String, Number> barChart;  // Bar chart displaying clicks by task
    @FXML
    private LineChart<String, Number> timeLineChart;  // Line chart displaying time trends over tasks
    @FXML
    private Button returnButton;

    private final int currentUserId = 1; // Replace with dynamic user ID

    @FXML
    private void initialize() {
        loadAverages();
        loadBarChart();
        loadTimeLineChart();
    }

    private void loadAverages() {
        TaskStatsDAO statsDAO = new TaskStatsDAO();
        double avgClicks = statsDAO.getAverageClicks(currentUserId);
        double avgTime = statsDAO.getAverageTimeSpent(currentUserId);

        avgClicksLabel.setText(String.format("%.2f", avgClicks));
        avgTimeLabel.setText(String.format("%.2f", avgTime / 1000));  // Convert ms to seconds
    }

    private void loadBarChart() {
        TaskStatsDAO statsDAO = new TaskStatsDAO();
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

        barChart.getData().clear();  // Clear previous data
        barChart.getData().add(series);  // Add new data
    }

    private void loadTimeLineChart() {
        TaskStatsDAO statsDAO = new TaskStatsDAO();
        List<TaskStats> statsList = statsDAO.getUserStats(currentUserId);

        if (statsList.isEmpty()) {
            System.out.println("No data available for line chart.");
            return;
        }

        XYChart.Series<String, Number> timeSeries = new XYChart.Series<>();
        timeSeries.setName("Time Spent by Task");

        for (TaskStats stats : statsList) {
            timeSeries.getData().add(new XYChart.Data<>("Task " + stats.getTaskId(), stats.getTimeSpent() / 1000));  // Convert ms to seconds
        }

        timeLineChart.getData().clear();  // Clear previous data
        timeLineChart.getData().add(timeSeries);  // Add new data
    }

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

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.showAndWait();
    }
}
