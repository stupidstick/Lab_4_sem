package controller;

import data.Parameters;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    @FXML
    protected CheckBox goldAICheckBox;
    @FXML
    protected CheckBox guppyAICheckBox;
    @FXML
    protected ComboBox<String> goldAIPriorityBox;
    @FXML
    protected ComboBox<String> guppyAIPriorityBox;
    @FXML
    protected ListView listViewId;
    public static void updateListId(){
        System.out.println(Thread.currentThread());
    }
    protected ObservableList<String> listId = FXCollections.observableArrayList();

    protected Alert error = new Alert(Alert.AlertType.ERROR);

    protected void setAICheckBoxActive(){
        goldAICheckBox.setSelected(true);
        guppyAICheckBox.setSelected(true);
    }

    protected void setError(){
        error.setContentText("Wrong spawn time");
        error.setHeaderText("");
    }

    protected void startView(){
        goldAICheckBox.setDisable(false);
        guppyAICheckBox.setDisable(false);
        startButton.setDisable(true);
        goldFishProb.setDisable(true);
        guppyFishProb.setDisable(true);
        goldAIPriorityBox.setDisable(true);
        guppyAIPriorityBox.setDisable(true);
        goldFishSpawnTime.setDisable(true);
        guppyFishSpawnTime.setDisable(true);
        stopButton.setDisable(false);
    }

    protected void pauseView(){
        goldAICheckBox.setDisable(true);
        guppyAICheckBox.setDisable(true);
        startButton.setDisable(false);
        stopButton.setDisable(true);
    }

    protected void stopView(){
        startButton.setDisable(false);
        goldFishProb.setDisable(false);
        guppyFishProb.setDisable(false);
        goldAIPriorityBox.setDisable(false);
        guppyAIPriorityBox.setDisable(false);
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

    private void setAIPriority(){
        List<String> probList = IntStream.range(1, 11).mapToObj(String::valueOf).toList();
        goldAIPriorityBox.getItems().addAll(probList);
        guppyAIPriorityBox.getItems().addAll(probList);
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

    protected void setGoldFishProbListener(){
        Parameters.probGoldFishProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                setFishesProbValue(Parameters.getProbGoldFish(), Parameters.getProbGuppyFish());
            }
        });
    }

    protected void initialize(){
        setAIPriority();
        setProbComboBox();
        setDefaultShowTime();
        goldAIPriorityBox.setValue(String.valueOf(Thread.NORM_PRIORITY));
        guppyAIPriorityBox.setValue(String.valueOf(Thread.NORM_PRIORITY));
        setError();
        setAICheckBoxActive();
        listViewId.setItems(listId);
    }

}
