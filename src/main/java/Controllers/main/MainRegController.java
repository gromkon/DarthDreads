package Controllers.main;

import Animations.Shake;
import Utils.DB;
import Utils.SceneController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainRegController {
    public TextField secondNameField;
    public TextField firstNameField;
    public TextField patronymicField;
    public TextField phoneField;
    public TextField mailField;
    public PasswordField passField;
    public PasswordField passCheckField;
    public Label regOkLbl;
    public Label emailUsedLbl;
    public Label passNotEqualsLbl;
    public Button regBtn;
    public Button authBtn;


    public void goToAuthMenu(ActionEvent actionEvent) {
        SceneController.changeScene((Stage) regBtn.getScene().getWindow(), "Главная_Авторизация");
    }

    public void regUser(ActionEvent actionEvent) {
        String firstName = firstNameField.getText();
        String secondName = secondNameField.getText();
        String patronymic = patronymicField.getText();
        String phone = phoneField.getText();
        String mail = mailField.getText();
        String pass = passField.getText();
        String passCheck = passCheckField.getText();
        if (pass.equals(passCheck)) {
            if (DB.addClient(mail, pass, secondName, firstName, patronymic, phone, mail)) {
                regOkLbl.setVisible(true);
                emailUsedLbl.setVisible(false);
                passNotEqualsLbl.setVisible(false);
                Shake regOkShake = new Shake(regOkLbl);
                regOkShake.playAnim();
            } else {
                regOkLbl.setVisible(false);
                emailUsedLbl.setVisible(true);
                passNotEqualsLbl.setVisible(false);
                Shake emailUsedShake = new Shake(emailUsedLbl);
                emailUsedShake.playAnim();
            }
        } else {
            regOkLbl.setVisible(false);
            emailUsedLbl.setVisible(false);
            passNotEqualsLbl.setVisible(true);
            Shake passNotEqualsShake = new Shake(passNotEqualsLbl);
            passNotEqualsShake.playAnim();
        }
    }
}
