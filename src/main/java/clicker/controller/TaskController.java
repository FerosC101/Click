package clicker.controller;

import clicker.connection.TaskStatsDAO;
import clicker.connection.UserDAO;
import clicker.model.TaskStats;
import clicker.model.User;
import clicker.util.NavigationUtil;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.Date;


public class TaskController {
    @FXML
    public Button clickButton;
    @FXML
    private Label userLabel;

    @FXML
    private Label timerLabel;

    @FXML
    private Label clickCountLabel;

    @FXML
    private Button exitButton;

    private int clickCount = 0;
    private int elapsedSeconds = 0;
    private Timeline timer;

    private final UserDAO userDAO = new UserDAO();
    private static int currentUserId;

    public static void setCurrentUserId(int userId) {
        currentUserId = userId;
    }

    @FXML
    public void initialize() {
        loadUserDetails();
        timerLabel.setText("Time: 0:00");
        clickCountLabel.setText("Click count: 0");

        startTimer();
    }

    private void loadUserDetails() {
        if (currentUserId <= 0) {
            userLabel.setText("User: Unknown");
            return;
        }

        try {
            User currentUser = userDAO.getUserById(currentUserId);

            if (currentUser != null) {
                userLabel.setText("User: " + currentUser.getUsername());
            } else {
                userLabel.setText("User: Unknown");
            }
        } catch (Exception e) {
            e.printStackTrace();
            userLabel.setText("User: Error");
        }
    }

    @FXML
    private void handleClick(ActionEvent event) {
        clickCount++;
        clickCountLabel.setText("Click count: " + clickCount);
    }

    @FXML
    private void handleExit(ActionEvent event) {
        if (timer != null) {
            timer.stop();
        }

        saveTaskStats();

        NavigationUtil.navigateTo("/ui/user_menu.fxml", exitButton);
    }

    private void saveTaskStats() {
        TaskStats stats = new TaskStats();
        TaskStatsDAO taskStatsDAO = new TaskStatsDAO();
        stats.setUserId(currentUserId);
        stats.setClickCount(clickCount);
        stats.setTimeSpent(elapsedSeconds);
        stats.setDate(new Date(System.currentTimeMillis()));
        try {
            taskStatsDAO.addTaskStats(stats);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void startTimer() {
        timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTimer()));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    private void updateTimer() {
        elapsedSeconds++;
        int minutes = elapsedSeconds / 60;
        int seconds = elapsedSeconds % 60;
        timerLabel.setText(String.format("Time: %d:%02d", minutes, seconds));
    }
}
