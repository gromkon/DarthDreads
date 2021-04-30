package Controllers.admin.master_menu;

import Controllers.admin.base.AdminController;
import Entities.Timetable;
import UserUtility.UserInfo;
import Utils.DB;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminMastersAddController extends AdminController implements Initializable {

    public Label helloUserLabel;
    public TextField secondNameLabel;
    public TextField firstNameLabel;
    public TextField patronymicLabel;
    public TextField phoneLabel;
    public TextField mailLabel;
    public CheckBox service1CB;
    public CheckBox service2CB;
    public CheckBox service3CB;
    public CheckBox service4CB;
    public TextField mondayStartH;
    public TextField mondayStartM;
    public TextField mondayEndH;
    public TextField mondayEndM;
    public TextField tuesdayStartH;
    public TextField tuesdayStartM;
    public TextField tuesdayEndH;
    public TextField tuesdayEndM;
    public TextField wednesdayStartH;
    public TextField wednesdayStartM;
    public TextField wednesdayEndH;
    public TextField wednesdayEndM;
    public TextField thursdayStartH;
    public TextField thursdayStartM;
    public TextField thursdayEndH;
    public TextField thursdayEndM;
    public TextField fridayStartH;
    public TextField fridayStartM;
    public TextField fridayEndH;
    public TextField fridayEndM;
    public TextField saturdayStartH;
    public TextField saturdayStartM;
    public TextField saturdayEndH;
    public TextField saturdayEndM;
    public TextField sundayStartH;
    public TextField sundayStartM;
    public TextField sundayEndH;
    public TextField sundayEndM;
    public Label masterAddLabel;
    public Label masterAddErrorLabel;
    public Button addBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserInfo ui = DB.getInfo();
        helloUserLabel.setText("Добро пожаловать, " + ui.getFirstName());
    }

    public void addNewMaster(ActionEvent actionEvent) {
        String secondName = secondNameLabel.getText();
        String firstName = firstNameLabel.getText();
        String patronymic = patronymicLabel.getText();
        String phone = phoneLabel.getText();
        String mail = mailLabel.getText();

        ArrayList<String> services = new ArrayList<>();
        if (service1CB.isSelected()) {
            services.add("1");
        }
        if (service2CB.isSelected()) {
            services.add("2");
        }
        if (service3CB.isSelected()) {
            services.add("3");
        }
        if (service4CB.isSelected()) {
            services.add("4");
        }

        Timetable timetable = new Timetable(
                mondayStartH.getText(), mondayStartM.getText(), mondayEndH.getText(), mondayEndM.getText(),
                tuesdayStartH.getText(), tuesdayStartM.getText(), tuesdayEndH.getText(), tuesdayEndM.getText(),
                wednesdayStartH.getText(), wednesdayStartM.getText(), wednesdayEndH.getText(), mondayEndM.getText(),
                thursdayStartH.getText(), thursdayStartM.getText(), thursdayEndH.getText(), thursdayEndM.getText(),
                fridayStartH.getText(), fridayStartM.getText(), fridayEndH.getText(), fridayEndM.getText(),
                saturdayStartH.getText(), saturdayStartM.getText(), saturdayEndH.getText(), saturdayEndM.getText(),
                sundayStartH.getText(), sundayStartM.getText(), sundayEndH.getText(), sundayEndM.getText()
        );

        if (DB.addMaster(mail, mail, secondName, firstName, patronymic, phone, mail, services, timetable)) {
            masterAddLabel.setVisible(true);
            masterAddErrorLabel.setVisible(false);
        } else {
            masterAddLabel.setVisible(false);
            masterAddErrorLabel.setVisible(true);
        }

    }
}
