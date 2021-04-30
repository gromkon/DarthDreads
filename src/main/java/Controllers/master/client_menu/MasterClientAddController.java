package Controllers.master.client_menu;

import Animations.Shake;
import Controllers.master.base.MasterController;
import UserUtility.UserInfo;
import Utils.DB;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MasterClientAddController extends MasterController implements Initializable {

    public Label helloUserLabel;
    public TextField secondNameLabel;
    public TextField firstNameLabel;
    public TextField patronymicLabel;
    public TextField phoneLabel;
    public TextField mailLabel;
    public Label mailAlreadyUsedLabel;
    public Label userReg1;
    public Label userReg2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserInfo ui = DB.getInfo();
        helloUserLabel.setText("Добро пожаловать, " + ui.getFirstName());
    }

    public void addNewClient(ActionEvent actionEvent) {
        String secondName = secondNameLabel.getText();
        String firstName = firstNameLabel.getText();
        String patronymic = patronymicLabel.getText();
        String phone = phoneLabel.getText();
        String mail = mailLabel.getText();
        String login = mail;
        String password = mail;
        if (!DB.addClient(login, password, secondName, firstName, patronymic, phone, mail)) {
            mailAlreadyUsedLabel.setVisible(true);
            Shake masterMailAlreadyUsedLabelAnim = new Shake(mailAlreadyUsedLabel);
            masterMailAlreadyUsedLabelAnim.playAnim();
            userReg1.setVisible(false);
            userReg2.setVisible(false);
        } else {
            mailAlreadyUsedLabel.setVisible(false);
            userReg1.setVisible(true);
            userReg2.setVisible(true);
            Shake userReg1Anim = new Shake(userReg1);
            userReg1Anim.playAnim();
            Shake userReg2Anim = new Shake(userReg2);
            userReg2Anim.playAnim();
        }

    }
}
