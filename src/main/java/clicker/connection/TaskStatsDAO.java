package clicker.connection;

import clicker.model.TaskStats;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaskStatsDAO {

    public void addTaskStats(TaskStats stats) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO stats (task_id, user_id, click_count, time_spent, date) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, stats.getTaskId());
            stmt.setInt(2, stats.getUserId());
            stmt.setInt(3, stats.getClickCount());
            stmt.setLong(4, stats.getTimeSpent());
            stmt.setDate(5, stats.getDate()); // Ensure this matches your TaskStats model type
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error adding task stats: " + e.getMessage());
        }
    }

    public List<TaskStats> getUserStats(int userId) {
        List<TaskStats> statsList = new ArrayList<>();
        String query = "SELECT * FROM stats WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TaskStats stats = new TaskStats();
                stats.setTaskId(rs.getInt("task_id")); // FIXED
                stats.setUserId(rs.getInt("user_id"));
                stats.setClickCount(rs.getInt("click_count"));
                stats.setTimeSpent(rs.getLong("time_spent"));
                stats.setDate(rs.getDate("date")); // Ensure compatibility
                statsList.add(stats);
            }
        } catch (Exception e) {
            System.err.println("Error adding task stats: " + e.getMessage());
        }
        return statsList;
    }

    public double getAverageClicks(int userId) {
        String query = "SELECT AVG(click_count) AS avg_clicks FROM stats WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("avg_clicks");
            }
        } catch (Exception e) {
            System.err.println("Error adding task stats: " + e.getMessage());
        }
        return 0.0; // Return 0.0 if no data or an error occurs
    }

    public double getAverageTimeSpent(int userId) {
        String query = "SELECT AVG(time_spent) AS avg_time FROM stats WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("avg_time");
            }
        } catch (Exception e) {
            System.err.println("Error adding task stats: " + e.getMessage());
        }
        return 0.0;
    }
}
