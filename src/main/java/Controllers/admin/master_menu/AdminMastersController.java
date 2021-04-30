package Controllers.admin.master_menu;

import Controllers.admin.base.AdminController;
import Entities.Master;
import UserUtility.EditMaster;
import UserUtility.UserInfo;
import Utils.DB;
import Utils.Parser;
import Utils.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminMastersController extends AdminController implements Initializable {

    public Label helloUserLabel;
    public TableView mastersTbl;
    public Button addNewMasterBtn;
    public Button changeMasterBtn;
    public Button deleteMasterBtn;

    private ObservableList<Master> masters = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserInfo ui = DB.getInfo();
        helloUserLabel.setText("Добро пожаловать, " + ui.getFirstName());
        initTable();
    }

    private void initTable() {
        masters =  DB.getMasters();
        mastersTbl.setItems(masters);
    }

    public void goToAddNewMasterMenu(ActionEvent actionEvent) {
        SceneController.changeScene((Stage) addNewMasterBtn.getScene().getWindow(), "Админ_Мастера_Добавить");
    }

    public void goToChangeMasterMenu(ActionEvent actionEvent) {
        Master m = (Master) mastersTbl.getSelectionModel().getSelectedItem();
        if (m != null) {
            EditMaster.setFirstName(m.getFirstName());
            EditMaster.setSecondName(DB.getSecondNameByMail(m.getMail()));
            EditMaster.setPatronymic(DB.getPatronymicByMail(m.getMail()));
            EditMaster.setPhone(m.getPhone());
            EditMaster.setMail(m.getMail());
            EditMaster.setServices(Parser.parseServices(m.getServices()));
            EditMaster.setTimetable(Parser.parseTimetable(m.getTimetable()));
            SceneController.changeScene((Stage) changeMasterBtn.getScene().getWindow(), "Админ_Мастера_Редактировать");
        }
    }

    public void deleteMaster(ActionEvent actionEvent) {
        Master m = (Master) mastersTbl.getSelectionModel().getSelectedItem();
        if (m != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.getDialogPane().setContent(null);
            alert.setTitle("Удаление мастера");
            alert.setHeaderText("Вы уверены, что хотите удалить мастера из базы данных?");
            alert.setContentText("Мастер " + m.getFirstName() + " будет удален");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {
                DB.deleteMaster(m.getMail());
                masters.remove(m);
                mastersTbl.setItems(masters);
                mastersTbl.refresh();
            }
        }

    }

}
