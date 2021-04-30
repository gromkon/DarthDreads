package Controllers.master.timetable_menu;

import Controllers.master.base.MasterController;
import Entities.Visit;
import UserUtility.InfoTimetable;
import UserUtility.UserInfo;
import Utils.DB;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

public class MasterTimetableEditController extends MasterController implements Initializable {


    public Label helloUserLabel;

    public TextField clientSecondName1Field;
    public ChoiceBox chooseClient1CB;
    public ChoiceBox chooseMaster1CB;
    public ChoiceBox chooseService1CB;
    public Label cost1Lbl;
    public Label duration1Lbl;
    public TextField timeStartH1Fld;
    public TextField timeStartM1Fld;

    public TextField clientSecondName2Field;
    public ChoiceBox chooseClient2CB;
    public ChoiceBox chooseMaster2CB;
    public ChoiceBox chooseService2CB;
    public Label cost2Lbl;
    public Label duration2Lbl;
    public TextField timeStartH2Fld;
    public TextField timeStartM2Fld;

    public Button saveInfoBtn;
//    public Button backBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserInfo ui = DB.getInfo();
        helloUserLabel.setText("Добро пожаловать, " + ui.getFirstName());
        String day = InfoTimetable.getDay();
        String month = InfoTimetable.getMonth();
        String year = InfoTimetable.getYear();
        String time = InfoTimetable.getTime();
        initUsers(year, month, day, time);
    }

    private void initUsers(String year, String month, String day, String time) {
        // 1 клиент
        ArrayList<Visit> visits = DB.getVisitsByDateAndTime(day, month, year, time);

        clientSecondName1Field.setText(DB.getSecondNameById(visits.get(0).getClientID()));

        ObservableList<String> userNames = DB.getUsersBySecondName(clientSecondName1Field.getText());
        chooseClient1CB.setItems(userNames);
        for (String userName: userNames) {
            if (userName.startsWith(clientSecondName1Field.getText())) {
                chooseClient1CB.setValue(userName);
            }
        }

        ObservableList<String> servicesNames = DB.getServicesNames();
        chooseService1CB.setItems(servicesNames);
        for (String serviceName: servicesNames) {
            if (visits.get(0).getServiceName().equals(serviceName)) {
                chooseService1CB.setValue(serviceName);
            }
        }
        duration1Lbl.setText(visits.get(0).getDuration());
        cost1Lbl.setText(visits.get(0).getCost());
        timeStartH1Fld.setText(visits.get(0).getTime().substring(0, 2));
        timeStartM1Fld.setText(visits.get(0).getTime().substring(3));

        String dayName = getDayName(year, month, day);
        ObservableList<String> mastersNames = DB.getFreeMasterAtTimeAndFinishServiceBeforeEndWorkingDay(
                dayName,
                visits.get(0).getTime(),
                DB.getServiceIdByName(visits.get(0).getServiceName())
        );
        chooseMaster1CB.setItems(mastersNames);
        for (String masterName: mastersNames) {
            if (DB.getFirstAndSecondNameById(visits.get(0).getMasterID()).equals(masterName)) {
                chooseMaster1CB.setValue(masterName);
            }
        }

        // 2 клиент
        if (visits.size() == 2) {
            clientSecondName2Field.setText(DB.getSecondNameById(visits.get(1).getClientID()));

            userNames = DB.getUsersBySecondName(clientSecondName2Field.getText());
            chooseClient2CB.setItems(userNames);
            for (String userName: userNames) {
                if (userName.startsWith(clientSecondName2Field.getText())) {
                    chooseClient2CB.setValue(userName);
                }
            }

            chooseService2CB.setItems(servicesNames);
            for (String serviceName: servicesNames) {
                if (visits.get(1).getServiceName().equals(serviceName)) {
                    chooseService2CB.setValue(serviceName);
                }
            }
            duration2Lbl.setText(visits.get(1).getDuration());
            cost2Lbl.setText(visits.get(1).getCost());
            timeStartH2Fld.setText(visits.get(1).getTime().substring(0, 2));
            timeStartM2Fld.setText(visits.get(1).getTime().substring(3));

            mastersNames = DB.getFreeMasterAtTimeAndFinishServiceBeforeEndWorkingDay(
                    dayName,
                    visits.get(1).getTime(),
                    DB.getServiceIdByName(visits.get(1).getServiceName())
            );
            chooseMaster2CB.setItems(mastersNames);
            for (String masterName: mastersNames) {
                if (DB.getFirstAndSecondNameById(visits.get(1).getMasterID()).equals(masterName)) {
                    chooseMaster2CB.setValue(masterName);
                }
            }
        } else {
            duration2Lbl.setText("-");
            cost2Lbl.setText("-");
        }
    }

    private String getDayName(String year, String month, String day) {
        int yearInt = Integer.parseInt(year);
        int monthInt = Integer.parseInt(month);
        int dayInt = Integer.parseInt(day);
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(yearInt, monthInt, dayInt);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        String dayName = null;
        switch (dayOfWeek) {
            case 1:
                dayName = "friday";
                break;
            case 2:
                dayName = "saturday";
                break;
            case 3:
                dayName = "sunday";
                break;
            case 4:
                dayName = "monday";
                break;
            case 5:
                dayName = "tuesday";
                break;
            case 6:
                dayName = "wednesday";
                break;
            case 7:
                dayName = "thursday";
                break;
        }
        return dayName;
    }

    public void saveInfo(ActionEvent actionEvent) {
    }



}
