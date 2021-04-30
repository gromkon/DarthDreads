package Controllers.master.LK_menu;

import Animations.Shake;
import Controllers.admin.LK_menu.AdminLKController;
import Controllers.master.base.MasterController;
import UserUtility.EditMaster;
import UserUtility.EditMasterTimeWork;
import UserUtility.UserInfo;
import Utils.DB;
import Utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MasterLKController extends MasterController implements Initializable {

    public Label helloUserLabel;

    public TextField secondNameLabel;
    public TextField firstNameLabel;
    public TextField patronymicLabel;
    public TextField phoneLabel;
    public Label mailLbl;
    public PasswordField passField;
    public PasswordField passCheckField;
    public Label passEditLbl;
    public Label passNotEqualLbl;
    public Button changeTimeWorkBtn;
    public Label dataUpdateLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserInfo ui = DB.getInfo();
        helloUserLabel.setText("Добро пожаловать, " + ui.getFirstName());
        secondNameLabel.setText(ui.getSecondName());
        firstNameLabel.setText(ui.getFirstName());
        patronymicLabel.setText(ui.getPatronymic());
        phoneLabel.setText(ui.getPhone());
        mailLbl.setText(ui.getMail());
    }

    public void saveInfo(ActionEvent actionEvent) {
        DB.updateInfo(
                secondNameLabel.getText(),
                firstNameLabel.getText(),
                patronymicLabel.getText(),
                phoneLabel.getText()
        );
        dataUpdateLbl.setVisible(true);
        Shake dataUpdateShake = new Shake(dataUpdateLbl);
        dataUpdateShake.playAnim();
    }

    public void changePass(ActionEvent actionEvent) {
        String pass = passField.getText();
        String passCheck = passCheckField.getText();
        if (pass.equals(passCheck)) {
            DB.changePassword(pass, mailLbl.getText());
            passNotEqualLbl.setVisible(false);
            passEditLbl.setVisible(true);
            Shake passEditShake = new Shake(passEditLbl);
            passEditShake.playAnim();
        } else {
            passNotEqualLbl.setVisible(true);
            passEditLbl.setVisible(false);
            Shake passNotEqualShake = new Shake(passNotEqualLbl);
            passNotEqualShake.playAnim();
        }
    }

    public void goToChangeTimeWorkMenu(ActionEvent actionEvent) {
        EditMasterTimeWork.setMail(mailLbl.getText());
        SceneController.changeScene((Stage) changeTimeWorkBtn.getScene().getWindow(), "Мастер_ВремяРаботы");
    }
}
