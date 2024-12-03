package clicker.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;

import java.io.IOException;

public class NavigationUtil {

    public static boolean navigateTo(String fxmlPath, Node currentNode) {
        try {
            FXMLLoader loader = new FXMLLoader(NavigationUtil.class.getResource(fxmlPath));
            Parent newRoot = loader.load();

            Scene currentScene = currentNode.getScene();
            currentScene.setRoot(newRoot);

            return true; 
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load " + fxmlPath);
        }
        return false;
    }
}
