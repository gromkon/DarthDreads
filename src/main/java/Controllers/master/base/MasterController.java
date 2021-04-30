package Controllers.master.base;

import UserUtility.User;
import UserUtility.UserType;
import Utils.SceneController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MasterController {

    public Button lkBtn;
    public Button timetableBtn;
    public Button clientsBtn;
    public Button exitBtn;

    public void openLKmenu(ActionEvent actionEvent) {
        SceneController.changeScene((Stage) lkBtn.getScene().getWindow(), "Мастер_ЛК");
    }

    public void openTimetableMenu(ActionEvent actionEvent) {
        SceneController.changeScene((Stage) timetableBtn.getScene().getWindow(), "Мастер_Расписание");
    }

    public void openClientMenu(ActionEvent actionEvent) {
        SceneController.changeScene((Stage) clientsBtn.getScene().getWindow(), "Мастер_Клиенты");
    }

    public void goToAuthMenu(ActionEvent actionEvent) {
        SceneController.changeScene((Stage) exitBtn.getScene().getWindow(), "Главная_Авторизация");
        User.setType(UserType.NO_USER);
        User.setLogin(null);
    }

}
