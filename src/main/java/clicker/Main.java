package clicker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/home_screen.fxml"));
            Parent root = loader.load();

            primaryStage.setTitle("Click Task Application");
            Scene scene = new Scene(root, 650, 820);
            primaryStage.setScene(scene);

            primaryStage.setResizable(true);

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading the Home Screen.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
