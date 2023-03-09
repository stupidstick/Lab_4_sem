package controller;

import data.FishData;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import objects.Fish;

import java.net.URL;
import java.util.ResourceBundle;

public class AliveController implements Initializable {
    protected class TableFish{
        private int id;
        private int birthTime;
        TableFish(int id, int birthTime){
            this.id = id;
            this.birthTime = birthTime;
        }

        public int getId() {
            return id;
        }
        public int getBirthTime(){
            return birthTime;
        }
    }
    @FXML
    TableView<TableFish> aliveTable;
    @FXML
    TableColumn<TableFish, Integer> idColumn;
    @FXML
    TableColumn<TableFish, Integer> birthTimeColumn;

    ObservableList<TableFish> tableList = FXCollections.observableArrayList(FishData.getFishesList().stream().map(obj -> new TableFish(obj.getId(), obj.getBirthTime())).toList());

    @FXML
    public void cancel(ActionEvent actionEvent){
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<TableFish, Integer>("id"));
        birthTimeColumn.setCellValueFactory(new PropertyValueFactory<TableFish, Integer>("birthTime"));
        aliveTable.setItems(tableList);
    }
}
