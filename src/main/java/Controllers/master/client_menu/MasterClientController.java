package Controllers.master.client_menu;

import Controllers.master.base.MasterController;
import Entities.Client;
import UserUtility.EditClient;
import UserUtility.UserInfo;
import Utils.DB;
import Utils.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MasterClientController extends MasterController implements Initializable {

    public Label helloUserLabel;
    public Button addNewClientBtn;
    public Button changeClientBtn;
    public TableView clientsTbl;

    private ObservableList<Client> clients = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserInfo ui = DB.getInfo();
        helloUserLabel.setText("Добро пожаловать, " + ui.getFirstName());
        initTable();
    }

    private void initTable() {
        clients =  DB.getClients();
        clientsTbl.setItems(clients);
    }

    public void addNewClient(ActionEvent actionEvent) {
        SceneController.changeScene((Stage) addNewClientBtn.getScene().getWindow(), "Мастер_Клиенты_Добавить");
    }

    public void changeClient(ActionEvent actionEvent) {
        Client c = (Client) clientsTbl.getSelectionModel().getSelectedItem();
        if (c != null) {
            EditClient.setFirstName(c.getFirstName());
            EditClient.setSecondName(c.getSecondName());
            EditClient.setPatronymic(DB.getPatronymicByMail(c.getMail()));
            EditClient.setPhone(c.getPhone());
            EditClient.setMail(c.getMail());
            SceneController.changeScene((Stage) changeClientBtn.getScene().getWindow(), "Мастер_Клиенты_Редактировать");
        }
    }



}
