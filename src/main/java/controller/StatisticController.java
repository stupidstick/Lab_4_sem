package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import objects.GoldFish;
import objects.GuppyFish;

import java.net.URL;
import java.util.ResourceBundle;

public class StatisticController implements Initializable{
    @FXML
    TextArea statisticArea;

    public void setStatistic(int simulationTime){
        statisticArea.setText(String.format(
                """
                        Runtime: %d
                        Guppy fish: %d
                        Gold fish: %d""",
                simulationTime, GuppyFish.getCountObjects(), GoldFish.getCountObjects()
                )
        );
    }

    @FXML
    public void ok(ActionEvent actionEvent){
        Controller.connectionStatistic.set(1);
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    @FXML
    public void cancel(ActionEvent actionEvent){
        Controller.connectionStatistic.set(2);
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        statisticArea.setDisable(true);
    }
}
