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
            // Load the Home Screen FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ui/home-screen.fxml"));
            Parent root = loader.load();

            primaryStage.setTitle("Click Task");
            primaryStage.setScene(new Scene(root, 400, 300)); // Adjust dimensions as needed
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
