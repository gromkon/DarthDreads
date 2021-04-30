package Controllers.master.timetable_menu;

import Controllers.master.base.MasterController;
import Entities.Visit;
import UserUtility.InfoTimetable;
import UserUtility.UserInfo;
import Utils.DB;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MasterTimetableInfoController  extends MasterController implements Initializable {


    public Label helloUserLabel;

    public Label client1Lbl;
    public Label master1Lbl;
    public Label service1Lbl;
    public Label cost1Lbl;
    public Label start1Lbl;
    public Label duration1Lbl;

    public Label client2Lbl;
    public Label master2Lbl;
    public Label service2Lbl;
    public Label cost2Lbl;
    public Label start2Lbl;
    public Label duration2Lbl;

    public Button backBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserInfo ui = DB.getInfo();
        helloUserLabel.setText("Добро пожаловать, " + ui.getFirstName());
        String day = InfoTimetable.getDay();
        String month = InfoTimetable.getMonth();
        String year = InfoTimetable.getYear();
        String time = InfoTimetable.getTime();
        ArrayList<Visit> visits = DB.getVisitsByDateAndTime(day, month, year, time);

        client1Lbl.setText(DB.getFirstAndSecondNameById(visits.get(0).getClientID()));
        master1Lbl.setText(DB.getFirstAndSecondNameById(visits.get(0).getMasterID()));
        service1Lbl.setText(visits.get(0).getServiceName());
        cost1Lbl.setText(DB.getCostServiceByName(visits.get(0).getServiceName()));
        start1Lbl.setText(visits.get(0).getTime());
        duration1Lbl.setText(visits.get(0).getDuration());

        if (visits.size() == 2) {
            client2Lbl.setText(DB.getFirstAndSecondNameById(visits.get(1).getClientID()));
            master2Lbl.setText(DB.getFirstAndSecondNameById(visits.get(1).getMasterID()));
            service2Lbl.setText(visits.get(1).getServiceName());
            cost2Lbl.setText(DB.getCostServiceByName(visits.get(1).getServiceName()));
            start2Lbl.setText(visits.get(1).getTime());
            duration2Lbl.setText(visits.get(1).getDuration());
        } else {
            client2Lbl.setText("-");
            master2Lbl.setText("-");
            service2Lbl.setText("-");
            cost2Lbl.setText("-");
            start2Lbl.setText("-");
            duration2Lbl.setText("-");
        }
    }


}
