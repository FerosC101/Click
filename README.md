# Clicker: A Simple Task Clicker Web Application  

Clicker is a lightweight web application where users and admins can log in to interact with an engaging task-based system. It focuses on data visualization and user activity tracking.  

## Features  

### User Functionalities  
- **Login/Sign Up**: Users can log in to their account or create a new one if they don't have an existing account.  
- **Interactive Task Menu**:  
  - **Start Task**: Engage in a click-based task with a timer. Users decide whether to continue or exit. The system tracks the number of clicks.  
  - **View Dashboard**: Access a personalized dashboard with data visualizations, including:  
    - Total Clicks  
    - Total Tasks  
    - Average Time per Task  
    - Bar Graphs, Line Graphs, and Pie Charts for performance insights.  
  - **Logout/Exit**: Log out or terminate the session.  

### Admin Functionalities  
- **View All Stats**: Admins can monitor all users' performance stats.  
- **Search User**: Find a specific user by username and view their detailed stats.  
- **Exit**: Log out of the admin session.  

## Database Structure  
1. **Users Table**:  
   - `user_id` (Unique ID)  
   - `username`  
   - `password`  

2. **Stats Table**:  
   - `task_id` (Unique ID for each task)  
   - `user_id` (References the `Users` table)  
   - `number_of_clicks`  
   - `time_taken`  
   - `date_completed`  

## Data Visualization  
The user's personal dashboard provides dynamic insights into their activity using charts and graphs, similar to tools like Power BI or Tableau. These visualizations help users analyze their performance at a glance.  

This project is perfect for beginners interested in integrating user interaction, database management, and data visualization into a single application!
