package controller;

import database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import objects.GoldFish;
import objects.GuppyFish;

import java.net.URL;
import java.util.ResourceBundle;

public class IpChooserController implements Initializable {
    @FXML
    TextField ipField;
    @FXML
    TextField portField;
    @FXML
    private void ok(ActionEvent actionEvent){
        if (Database.connection(ipField.getText(), portField.getText())){
            ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        }
    }
    @FXML
    private void cancel(ActionEvent actionEvent){
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
