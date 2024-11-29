package clicker.connection;

import clicker.model.TaskStats;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TaskStatsDAO {

    public void addTaskStats(TaskStats stats) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO stats (task_id, user_id, click_count, time_spent, date) values (?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, stats.getTaskId());
            stmt.setInt(2, stats.getUserId());
            stmt.setInt(3, stats.getClickCount());
            stmt.setLong(4, stats.getTimeSpent());
            stmt.setDate(5, stats.getDate());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
