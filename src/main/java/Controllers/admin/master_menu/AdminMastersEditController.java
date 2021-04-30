package Controllers.admin.master_menu;

import Animations.Shake;
import Controllers.admin.base.AdminController;
import Entities.Timetable;
import UserUtility.EditMaster;
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

public class AdminMastersEditController extends AdminController implements Initializable {

    public Label helloUserLabel;
    public TextField secondNameLabel;
    public TextField firstNameLabel;
    public TextField patronymicLabel;
    public TextField phoneLabel;
    public Label mailLabel;

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

    public Button editBtn;

    public Label masterEditLabel;
    public Label masterEditErrorLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserInfo ui = DB.getInfo();
        helloUserLabel.setText("Добро пожаловать, " + ui.getFirstName());

        secondNameLabel.setText(EditMaster.getSecondName());
        firstNameLabel.setText(EditMaster.getFirstName());
        patronymicLabel.setText(EditMaster.getPatronymic());
        phoneLabel.setText(EditMaster.getPhone());
        mailLabel.setText(EditMaster.getMail());

        ArrayList<String> checkBoxes = EditMaster.getServices();
        for (String item: checkBoxes) {
            if (item.equals("1")) {
                service1CB.setSelected(true);
            } else if (item.equals("2")) {
                service2CB.setSelected(true);
            } else if (item.equals("3")) {
                service3CB.setSelected(true);
            } else if (item.equals("4")) {
                service4CB.setSelected(true);
            }
        }

        Timetable t = EditMaster.getTimetable();
        if (t.getMondayStartH() != null) {
            mondayStartH.setText(t.getMondayStartH());
            mondayStartM.setText(t.getMondayStartM());
            mondayEndH.setText(t.getMondayEndH());
            mondayEndM.setText(t.getMondayEndM());
        }
        if (t.getTuesdayStartH() != null) {
            tuesdayStartH.setText(t.getTuesdayStartH());
            tuesdayStartM.setText(t.getTuesdayStartM());
            tuesdayEndH.setText(t.getTuesdayEndH());
            tuesdayEndM.setText(t.getTuesdayEndM());
        }
        if (t.getWednesdayStartH() != null) {
            wednesdayStartH.setText(t.getWednesdayStartH());
            wednesdayStartM.setText(t.getWednesdayStartM());
            wednesdayEndH.setText(t.getWednesdayEndH());
            wednesdayEndM.setText(t.getWednesdayEndM());
        }
        if (t.getThursdayStartH() != null) {
            thursdayStartH.setText(t.getThursdayStartH());
            thursdayStartM.setText(t.getThursdayStartM());
            thursdayEndH.setText(t.getThursdayEndH());
            thursdayEndM.setText(t.getThursdayEndM());
        }
        if (t.getFridayStartH() != null) {
            fridayStartH.setText(t.getFridayStartH());
            fridayStartM.setText(t.getFridayStartM());
            fridayEndH.setText(t.getFridayEndH());
            fridayEndM.setText(t.getFridayEndM());
        }
        if (t.getSaturdayStartH() != null) {
            saturdayStartH.setText(t.getSaturdayStartH());
            saturdayStartM.setText(t.getSaturdayStartM());
            saturdayEndH.setText(t.getSaturdayEndH());
            saturdayEndM.setText(t.getSaturdayEndM());
        }
        if (t.getSundayStartH() != null) {
            sundayStartH.setText(t.getSundayStartH());
            sundayStartM.setText(t.getSundayStartM());
            sundayEndH.setText(t.getSundayEndH());
            sundayEndM.setText(t.getSundayEndM());
        }
    }


    public void editMaster(ActionEvent actionEvent) {
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

        if (DB.updateMasterInfo(secondName, firstName, patronymic, phone, mail, services, timetable)) {
            masterEditLabel.setVisible(true);
            Shake masterEditLabelAnim = new Shake(masterEditLabel);
            masterEditLabelAnim.playAnim();
            masterEditErrorLabel.setVisible(false);
        } else {
            masterEditLabel.setVisible(false);
            masterEditErrorLabel.setVisible(true);
            Shake masterEditErrorLabelAnim = new Shake(masterEditErrorLabel);
            masterEditErrorLabelAnim.playAnim();
        }
    }
}
