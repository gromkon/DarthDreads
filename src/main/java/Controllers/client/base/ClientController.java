package Controllers.client.base;

import UserUtility.User;
import UserUtility.UserType;
import Utils.SceneController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ClientController {
    public Button lkBtn;
    public Button exitBtn;
    public Button historyBtn;
    public Button comingBtn;


    public void openLKmenu(ActionEvent actionEvent) {
        SceneController.changeScene((Stage) lkBtn.getScene().getWindow(), "Клиент_ЛК");
    }

    public void goToAuthMenu(ActionEvent actionEvent) {
        SceneController.changeScene((Stage) exitBtn.getScene().getWindow(), "Главная_Авторизация");
        User.setType(UserType.NO_USER);
        User.setLogin(null);
    }

    public void goToHistoryMenu(ActionEvent actionEvent) {
        SceneController.changeScene((Stage) lkBtn.getScene().getWindow(), "Клиент_История");
    }

    public void goToComingMenu(ActionEvent actionEvent) {
        SceneController.changeScene((Stage) lkBtn.getScene().getWindow(), "Клиент_Предстоящие");
    }
}
