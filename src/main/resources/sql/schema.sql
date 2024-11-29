CREATE DATABASE click;

CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50),
    password VARCHAR(50)
);

CREATE TABLE stats (
    task_id SERIAL,
    user_id INT,
    click_count INT,
    time_spent INTERVAL,
    date TIMESTAMP

);