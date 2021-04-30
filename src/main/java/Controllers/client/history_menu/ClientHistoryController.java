package Controllers.client.history_menu;

import Controllers.client.base.ClientController;
import UserUtility.UserInfo;
import Utils.DB;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

public class ClientHistoryController extends ClientController implements Initializable {

    public Label visitsLabel;
    public Label helloUserLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserInfo ui = DB.getInfo();
        helloUserLabel.setText("Добро пожаловать, " + ui.getFirstName());

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 0);
        String date = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DATE);
        visitsLabel.setText(DB.getClientVisitsEarlier(ui.getMail(), date));
    }
}
