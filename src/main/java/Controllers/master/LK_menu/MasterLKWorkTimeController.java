package Controllers.master.LK_menu;

import Animations.Shake;
import Controllers.master.base.MasterController;
import Entities.Timetable;
import UserUtility.EditMaster;
import UserUtility.EditMasterTimeWork;
import UserUtility.UserInfo;
import Utils.DB;
import Utils.Parser;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MasterLKWorkTimeController extends MasterController implements Initializable {

    public Label helloUserLabel;

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

    public Label dataUpdateLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserInfo ui = DB.getInfo();
        helloUserLabel.setText("Добро пожаловать, " + ui.getFirstName());

        String timetable = DB.getMasterTimetable(EditMasterTimeWork.getMail());
        Timetable t = Parser.parseTimetable(timetable);
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

    public void saveInfo(ActionEvent actionEvent) {
        Timetable timetable = new Timetable(
                mondayStartH.getText(), mondayStartM.getText(), mondayEndH.getText(), mondayEndM.getText(),
                tuesdayStartH.getText(), tuesdayStartM.getText(), tuesdayEndH.getText(), tuesdayEndM.getText(),
                wednesdayStartH.getText(), wednesdayStartM.getText(), wednesdayEndH.getText(), mondayEndM.getText(),
                thursdayStartH.getText(), thursdayStartM.getText(), thursdayEndH.getText(), thursdayEndM.getText(),
                fridayStartH.getText(), fridayStartM.getText(), fridayEndH.getText(), fridayEndM.getText(),
                saturdayStartH.getText(), saturdayStartM.getText(), saturdayEndH.getText(), saturdayEndM.getText(),
                sundayStartH.getText(), sundayStartM.getText(), sundayEndH.getText(), sundayEndM.getText()
        );

        if (DB.updateMasterTimetable(EditMasterTimeWork.getMail(), timetable)) {
            dataUpdateLbl.setVisible(true);
            Shake dataUpdateShake = new Shake(dataUpdateLbl);
            dataUpdateShake.playAnim();
        } else {
            dataUpdateLbl.setVisible(false);
        }
    }
}
