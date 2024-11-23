package clicker.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class NavigationUtil {

    public static void navigateTo(String fxmlPath, Node node) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(NavigationUtil.class.getClassLoader().getResource(fxmlPath)));
            Scene scene = node.getScene();
            scene.setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
