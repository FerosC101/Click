package clicker.controller;

import clicker.connection.TaskStatsDAO;
import clicker.model.TaskStats;
import clicker.util.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.util.List;

public class DashboardController {
    @FXML
    private Label avgClicksLabel;
    @FXML
    private Label avgTimeLabel;
    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private PieChart pieChart;
    @FXML
    private Button returnButton;

    private final int currentUserId = 1; // Replace with dynamic user ID

    @FXML
    private void initialize() {
        loadAverages();
        loadBarChart();
        loadPieChart();
    }

    private void loadAverages() {
        TaskStatsDAO statsDAO = new TaskStatsDAO();
        double avgClicks = statsDAO.getAverageClicks(currentUserId);
        double avgTime = statsDAO.getAverageTimeSpent(currentUserId);

        avgClicksLabel.setText(String.format("%.2f", avgClicks));
        avgTimeLabel.setText(String.format("%.2f", avgTime / 1000)); // Convert ms to seconds
    }

    private void loadBarChart() {
        TaskStatsDAO statsDAO = new TaskStatsDAO();
        List<TaskStats> statsList = statsDAO.getUserStats(currentUserId);

        if (statsList.isEmpty()) {
            System.out.println("No data available for bar chart.");
            return;
        }

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Clicks Over Tasks");

        for (TaskStats stats : statsList) {
            series.getData().add(new XYChart.Data<>("Task " + stats.getTaskId(), stats.getClickCount()));
        }

        barChart.getData().add(series);
    }


    private void loadPieChart() {
        TaskStatsDAO statsDAO = new TaskStatsDAO();
        List<TaskStats> statsList = statsDAO.getUserStats(currentUserId);

        if (statsList.isEmpty()) {
            System.out.println("No data available for pie chart.");
            return;
        }

        for (TaskStats stats : statsList) {
            PieChart.Data slice = new PieChart.Data("Task " + stats.getTaskId(), stats.getClickCount());
            pieChart.getData().add(slice);
        }
    }

    @FXML
    private void handleReturnToUserMenu() {
        boolean success = NavigationUtil.navigateTo("/ui/user_menu.fxml", returnButton);
        if (!success) {
            showErrorAlert("Failed to navigate back to the User Menu.");
        }
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.showAndWait();
    }
}
