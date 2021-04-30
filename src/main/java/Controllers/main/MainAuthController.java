package Controllers.main;

import Animations.Shake;
import UserUtility.User;
import UserUtility.UserType;
import Utils.AuthStatus;
import Utils.SceneController;
import Utils.DB;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainAuthController implements Initializable {
    public Label errorLabel;
    public TextField loginField;
    public TextField passField;
    public Button goBtn;
    public Button regBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void tryAuth(ActionEvent actionEvent) throws IOException {
        String login = loginField.getText();
        String pass = passField.getText();
        AuthStatus as = DB.auth(login, pass);
        if (as == AuthStatus.ADMIN) {
            User.setType(UserType.ADMIN);
            User.setLogin(login);

            SceneController.changeScene((Stage) goBtn.getScene().getWindow(), "Админ_ЛК");
        } else if (as == AuthStatus.CLIENT) {
            User.setType(UserType.CLIENT);
            User.setLogin(login);

            SceneController.changeScene((Stage) goBtn.getScene().getWindow(), "Клиент_ЛК");
        } else if (as == AuthStatus.MASTER) {
            User.setType(UserType.MASTER);
            User.setLogin(login);

            SceneController.changeScene((Stage) goBtn.getScene().getWindow(), "Мастер_ЛК");
        } else if (as == AuthStatus.NO_USER) {
            User.setType(UserType.NO_USER);

            errorLabel.setText("Неверный логин или пароль");
            loginField.setStyle("-fx-border-color:#ff5e4e");
            passField.setStyle("-fx-border-color:#ff5e4e");
            Shake goBtnAnim = new Shake(goBtn);
            goBtnAnim.playAnim();
        }
    }

    public void openRegPanel(ActionEvent actionEvent) {
        SceneController.changeScene((Stage) regBtn.getScene().getWindow(), "Главная_Регистрация");
    }

}
