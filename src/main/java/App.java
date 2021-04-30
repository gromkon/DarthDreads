import Utils.DB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        DB.connect();
        FXMLLoader loader = new FXMLLoader();
        Parent root = (Parent) loader.load(getClass().getResource("fxml/main/Главная_Авторизация.fxml"));
        stage.setTitle("Salon");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        stage.setOnHidden(event -> {
            DB.disconnect();
        });
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}