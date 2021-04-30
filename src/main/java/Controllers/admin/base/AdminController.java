package Controllers.admin.base;

import UserUtility.User;
import UserUtility.UserType;
import Utils.SceneController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminController {

    public Button lkBtn;
    public Button timetableBtn;
    public Button mastersBtn;
    public Button clientsBtn;
    public Button exitBtn;

    public void openLKmenu(ActionEvent actionEvent) {
        SceneController.changeScene((Stage) lkBtn.getScene().getWindow(), "Админ_ЛК");
    }

    public void openTimetableMenu(ActionEvent actionEvent) {
        SceneController.changeScene((Stage) timetableBtn.getScene().getWindow(), "Админ_Расписание");
    }

    public void openMastersMenu(ActionEvent actionEvent) {
        SceneController.changeScene((Stage) mastersBtn.getScene().getWindow(), "Админ_Мастера");
    }

    public void openClientMenu(ActionEvent actionEvent) {
        SceneController.changeScene((Stage) clientsBtn.getScene().getWindow(), "Админ_Клиенты");
    }

    public void goToAuthMenu(ActionEvent actionEvent) {
        SceneController.changeScene((Stage) exitBtn.getScene().getWindow(), "Главная_Авторизация");
        User.setType(UserType.NO_USER);
        User.setLogin(null);
    }


}
