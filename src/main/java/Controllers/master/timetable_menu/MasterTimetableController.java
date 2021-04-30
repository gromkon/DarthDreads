package Controllers.master.timetable_menu;

import Controllers.master.base.MasterController;
import Entities.TimeData;
import Entities.Visit;
import UserUtility.InfoTimetable;
import UserUtility.UserInfo;
import Utils.DB;
import Utils.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

public class MasterTimetableController extends MasterController implements Initializable {

    public Label helloUserLabel;
    public TableView timetableTbl;
//    public Button addNewVisitBtn;
    public Button changeVisitBtn;
    public Button infoBtn;

    private Calendar calendar;
    private ObservableList<TimeData> timeData;
    private int dayStart;

    private int curYear;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserInfo ui = DB.getInfo();
        helloUserLabel.setText("Добро пожаловать, " + ui.getFirstName());
        dayStart = InfoTimetable.getDayStart();
        InfoTimetable.setDayStart(0);
        initTable(dayStart);
    }

    private void initTable(int dayStart) {
        ArrayList<String> days = initColumnNames(dayStart);
        initRows(days, dayStart);
    }

    private void initRows(ArrayList<String> days, int dayStart) {
        // Инициализация строк таблицы
        timeData = FXCollections.observableArrayList();
        for (int hour = 9; hour < 22; hour++) {
            for (int min = 0; min <= 30; min += 30) {
                if (hour < 10) {
                    if (min == 0) {
                        timeData.add(new TimeData("0" + hour + ":0" + min));
                    } else {
                        timeData.add(new TimeData("0" + hour + ":" + min));
                    }
                } else {
                    if (min == 0) {
                        timeData.add(new TimeData(hour + ":0" + min));
                    } else {
                        timeData.add(new TimeData(hour + ":" + min));
                    }
                }

            }
        }

        // Наполнение строк информацией
        ArrayList<Visit> visits = DB.getVisitsByDateFromTodayForSevenDays(dayStart);
        for (Visit v: visits) {
            String day = v.getDay();
            int columnNum = 0;
            for (String dayNum: days) {
                if (dayNum.startsWith(day)) {
                    break;
                } else {
                    columnNum++;
                }
            }
            for (int i = 0; i < timeData.size(); i++) {
                TimeData time = timeData.get(i);
                if (v.getTime().equals(time.getTime())) {
                    int countCellsDuration = v.getCountCellForDuration();
                    for (int j = i; j < i + countCellsDuration; j++) {
                        TimeData timeCells = timeData.get(j);
                        if (timeCells.getText(columnNum).equals("-")) {
                            timeCells.setText(columnNum, "1 запись");
                        } else if (timeCells.getText(columnNum).equals("1 запись")) {
                            timeCells.setText(columnNum, "2 записи");
                        }
                    }
                    break;
                }
            }
        }

        timetableTbl.setItems(timeData);

    }

    private String getDateString() {
        int month = calendar.get(Calendar.MONTH) + 1;
        int date = calendar.get(Calendar.DATE);
        int year = calendar.get(Calendar.YEAR);
        String dateString;
        if (month < 10) {
            if (date < 10) {
                dateString = "0" + date + ".0" + month;
            } else {
                dateString = date + ".0" + month;
            }
        } else {
            if (date < 10) {
                dateString = "0" + date + "." + month;
            } else {
                dateString = date + "." + month;
            }
        }
        if (year != curYear) {
            dateString += "." + year;
        }
        return dateString;
    }

    private ArrayList<String> initColumnNames(int dayStart) {
        calendar = Calendar.getInstance();
        ArrayList<String> days = new ArrayList<>();
        days.add("Время");

        calendar.add(Calendar.DATE, dayStart);
        if (dayStart == 0) {
            curYear = calendar.get(Calendar.YEAR);
        } else {
            Calendar yearCalendar = Calendar.getInstance();
            yearCalendar.add(Calendar.DATE, 0);
            curYear = yearCalendar.get(Calendar.YEAR);
        }

        days.add(getDateString());
        for (int i = 0; i < 6; i++) {
            calendar.add(Calendar.DATE, 1);
            days.add(getDateString());
        }
        ObservableList<TableColumn> columns = timetableTbl.getColumns();
        int columnNum = 0;
        for (TableColumn column: columns) {
            column.setText(days.get(columnNum++));
        }
        return days;
    }

    public void nextWeek(ActionEvent actionEvent) {
        dayStart += 7;
        initTable(dayStart);
    }

    public void prevWeek(ActionEvent actionEvent) {
        dayStart -= 7;
        initTable(dayStart);
    }

    private void setInfo(int column, int row) {
        String time = timeData.get(row).getTime();
        TableColumn tc = (TableColumn) timetableTbl.getColumns().get(column);
        String date = tc.getText();
        String day = date.substring(0, 2);
        String month = date.substring(3, 5);
        String year;
        if (date.length() > 5) {
            year = date.substring(6);
        } else {
            year = String.valueOf(curYear);
        }
        InfoTimetable.setDay(day);
        InfoTimetable.setMonth(month);
        InfoTimetable.setYear(year);
        InfoTimetable.setTime(time);
        InfoTimetable.setDayStart(dayStart);
    }

    public void showInfo(ActionEvent actionEvent) {
        int column = timetableTbl.getFocusModel().getFocusedCell().getColumn();
        int row = timetableTbl.getFocusModel().getFocusedCell().getRow();
        if (column != -1 & row != 0) {
            if (!timeData.get(row).getText(column).equals("-")) {
                setInfo(column, row);
                SceneController.changeScene((Stage) infoBtn.getScene().getWindow(), "Мастер_Расписание_Информация");
            }
        }

    }

    public void changeVisit(ActionEvent actionEvent) {
        int column = timetableTbl.getFocusModel().getFocusedCell().getColumn();
        int row = timetableTbl.getFocusModel().getFocusedCell().getRow();
        if (column != -1 & row != 0) {
            if (!timeData.get(row).getText(column).equals("-")) {
                setInfo(column, row);
                SceneController.changeScene((Stage) infoBtn.getScene().getWindow(), "Мастер_Расписание_Редактировать");
            }
        }
    }
}
