package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.List;
import java.util.stream.IntStream;

public class View {
    @FXML
    protected AnchorPane workspace;
    @FXML
    protected Pane simulationArea;
    @FXML
    protected ComboBox<String> goldFishProb;
    @FXML
    protected ComboBox<String> guppyFishProb;
    @FXML
    protected TextField goldFishSpawnTime;
    @FXML
    protected TextField guppyFishSpawnTime;
    @FXML
    protected HBox controlPanel;
    @FXML
    protected Button startButton;
    @FXML
    protected Button stopButton;
    @FXML
    protected RadioButton showTimeButton;
    @FXML
    protected RadioButton hideTimeButton;
    @FXML
    protected RadioMenuItem showTimeMenuItem;
    @FXML
    protected RadioMenuItem hideTimeMenuItem;
    @FXML
    protected HBox timeBox;
    @FXML
    protected CheckBox infoButton;
    @FXML
    protected CheckMenuItem infoMenuItem;
    @FXML
    protected TextField goldFishLifeTime;
    @FXML
    protected TextField guppyFishLifeTime;
    protected Alert error = new Alert(Alert.AlertType.ERROR);

    protected void setError(){
        error.setContentText("Wrong spawn time");
        error.setHeaderText("");
    }

    protected void startView(){
        startButton.setDisable(true);
        goldFishProb.setDisable(true);
        guppyFishProb.setDisable(true);
        goldFishSpawnTime.setDisable(true);
        guppyFishSpawnTime.setDisable(true);
        stopButton.setDisable(false);

    }

    protected void pauseView(){
        startButton.setDisable(false);
        stopButton.setDisable(true);
    }

    protected void stopView(){
        startButton.setDisable(false);
        goldFishProb.setDisable(false);
        guppyFishProb.setDisable(false);
        goldFishSpawnTime.setDisable(false);
        guppyFishSpawnTime.setDisable(false);
        stopButton.setDisable(true);
    }

    protected void showTimeView(boolean isActive) {
        showTimeButton.setDisable(!isActive);
        showTimeButton.setSelected(!isActive);
        hideTimeButton.setDisable(isActive);
        hideTimeButton.setSelected(isActive);
        showTimeMenuItem.setDisable(!isActive);
        showTimeMenuItem.setSelected(!isActive);
        hideTimeMenuItem.setDisable(isActive);
        hideTimeMenuItem.setSelected(isActive);
    }
    protected void setProbComboBox(){
        List<String> probList = IntStream.range(1, 11).mapToObj(value -> String.valueOf(value * 10) + "%").toList();
        goldFishProb.getItems().addAll(probList);
        guppyFishProb.getItems().addAll(probList);
    }

    protected void setFishesProbValue(double probGoldFish, double probGuppyFish){
        goldFishProb.setValue(String.valueOf((int) (probGoldFish * 100)) + "%");
        guppyFishProb.setValue(String.valueOf((int) (probGuppyFish * 100)) + "%");
    }
    protected void setFishesSpawnTime(int spawnTimeGoldFish, int spawnTimeGuppyFish){
        goldFishSpawnTime.setText(String.valueOf(spawnTimeGoldFish));
        guppyFishSpawnTime.setText(String.valueOf(spawnTimeGuppyFish));
    }
    protected void setDefaultShowTime() {
        hideTimeButton.setSelected(true);
        hideTimeButton.setDisable(true);
    }
}
