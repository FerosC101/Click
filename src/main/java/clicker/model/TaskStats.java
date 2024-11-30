package clicker.model;

import java.util.Date;

public class TaskStats {
    private int taskId;
    private int userId;
    private int clickCount;
    private long timeSpent;
    private Date date;
    private int currentUserId;

    public TaskStats(int taskId, int userId, int clickCount, long timeSpent, Date date) {
        this.taskId = taskId;
        this.userId = userId;
        this.clickCount = clickCount;
        this.timeSpent = timeSpent;
        this.date = date;
    }

    public TaskStats() {

    }

    public int getTaskId() {
        return taskId;
    }

    public int getUserId() {
        return userId;
    }

    public int getClickCount() {
        return clickCount;
    }

    public long getTimeSpent() {
        return timeSpent;
    }

    public java.sql.Date getDate() {
        return (java.sql.Date) date;
    }

    public void setUserId(int UserId) {
        this.userId = UserId;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    public void setTimeSpent(long timeSpent) {
        this.timeSpent = timeSpent;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
