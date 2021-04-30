package Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class SceneController {


    public static void changeScene(Stage stage, String sceneName) {
        try {
            String path = null;
            if (sceneName.startsWith("Админ")) {
                path = "/fxml/admin/" + sceneName + ".fxml";
            } else if (sceneName.startsWith("Клиент")) {
                path = "/fxml/client/" + sceneName + ".fxml";
            } else if (sceneName.startsWith("Мастер")) {
                path = "/fxml/master/" + sceneName + ".fxml";
            } else if (sceneName.startsWith("Главная")) {
                path = "/fxml/main/" + sceneName + ".fxml";
            }
            FXMLLoader loader = new FXMLLoader();
            Parent root = (Parent) loader.load(SceneController.class.getResource(path));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
