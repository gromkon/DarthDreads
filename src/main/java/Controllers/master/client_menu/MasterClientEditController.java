package Controllers.master.client_menu;

import Animations.Shake;
import Controllers.master.base.MasterController;
import UserUtility.EditClient;
import UserUtility.UserInfo;
import Utils.DB;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MasterClientEditController extends MasterController implements Initializable {

    public Label helloUserLabel;
    public Label dataUpdateLbl;
    public TextField phoneLabel;
    public TextField patronymicLabel;
    public TextField firstNameLabel;
    public TextField secondNameLabel;
    public Label mailLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserInfo ui = DB.getInfo();
        helloUserLabel.setText("Добро пожаловать, " + ui.getFirstName());
        firstNameLabel.setText(EditClient.getFirstName());
        secondNameLabel.setText(EditClient.getSecondName());
        patronymicLabel.setText(EditClient.getPatronymic());
        phoneLabel.setText(EditClient.getPhone());
        mailLbl.setText(EditClient.getMail());
    }

    public void editClient(ActionEvent actionEvent) {
        DB.updateClientInfo(
                secondNameLabel.getText(),
                firstNameLabel.getText(),
                patronymicLabel.getText(),
                phoneLabel.getText(),
                mailLbl.getText()
        );
        dataUpdateLbl.setVisible(true);
        Shake dataUpdateShake = new Shake(dataUpdateLbl);
        dataUpdateShake.playAnim();
    }

}
