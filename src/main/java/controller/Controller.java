package controller;

import Domain.Model;
import ai.GoldFishAI;
import ai.GuppyFishAI;
import data.FishData;
import data.Parameters;
import javafx.animation.AnimationTimer;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.robot.Robot;
import javafx.stage.Modality;
import javafx.stage.Stage;
import objects.Fish;
import objects.GoldFish;
import objects.GuppyFish;
import sample.Main;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class Controller extends View implements Initializable {
    ObservableValue<Boolean> isActive;
    Model model = new Model();
    TimeView simulationTime = new TimeView();

    GoldFishAI goldFishAI = new GoldFishAI();
    GuppyFishAI guppyFishAI = new GuppyFishAI();

    public static SimpleIntegerProperty connectionStatistic = new SimpleIntegerProperty(0);

    final LongProperty startTime = new SimpleLongProperty();

    @FXML
    public void switchGoldAI(){
        synchronized (goldFishAI.getCheckAi()){
            goldFishAI.getCheckAi().notify();
        }
        goldFishAI.setActive(!goldFishAI.getActive());
    }

    @FXML
    public void switchGuppyAI(){
        synchronized (guppyFishAI.getCheckAi()){
            guppyFishAI.getCheckAi().notify();
        }
        guppyFishAI.setActive(!guppyFishAI.getActive());
    }

    private void createStatisticStage() {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("statistic.fxml"));
        Scene statisticScene = null;
        try{
            statisticScene = new Scene(loader.load(), 800, 600);
        }
        catch (Exception ignored){
        }
        Stage statisticStage = new Stage();
        statisticStage.initModality(Modality.APPLICATION_MODAL);
        statisticStage.setScene(statisticScene);
        statisticStage.setTitle("Statistics");
        statisticStage.setResizable(false);
        statisticStage.show();
        ((StatisticController)loader.getController()).setStatistic(simulationTime.getTimeInSeconds());
    }
    @FXML
    private void createAliveStage(){
        FXMLLoader loaderAlive = new FXMLLoader(Main.class.getResource("alive.fxml"));
        Scene aliveScene = null;
        try{
            aliveScene = new Scene(loaderAlive.load(), 800, 600);
        }
        catch (Exception ignored){
        }
        Stage aliveStage = new Stage();
        aliveStage.initModality(Modality.APPLICATION_MODAL);
        aliveStage.setScene(aliveScene);
        aliveStage.setTitle("Table");
        aliveStage.setResizable(false);
        aliveStage.show();
    }
    private boolean setSpawnField(){
        boolean result = true;
        try {
            if (Integer.parseInt(goldFishSpawnTime.getCharacters().toString()) <= 0)
                throw new Exception();
            Parameters.setSpawnTimeGoldFish(Integer.parseInt(goldFishSpawnTime.getCharacters().toString()));
        }
        catch (Exception exception){
            goldFishSpawnTime.setText(String.valueOf(Parameters.getSpawnTimeGoldFish()));
            result = false;
        }
        try {
            if (Integer.parseInt(guppyFishSpawnTime.getCharacters().toString()) <= 0)
                throw new Exception();
            Parameters.setSpawnTimeGuppyFish(Integer.parseInt(guppyFishSpawnTime.getCharacters().toString()));
        }
        catch (Exception exception){
            guppyFishSpawnTime.setText(String.valueOf(Parameters.getSpawnTimeGuppyFish()));
            result = false;
        }
        try {
            if (Integer.parseInt(goldFishLifeTime.getCharacters().toString()) <= 0){
                throw new Exception();
            }
            GoldFish.setLifeTime(Integer.parseInt(goldFishLifeTime.getCharacters().toString()));
        }
        catch (Exception exception){
            goldFishLifeTime.setText(String.valueOf(GoldFish.getLifeTime()));
            result = false;
        }
        try {
            if (Integer.parseInt(guppyFishLifeTime.getCharacters().toString()) <= 0){
                throw new Exception();
            }
            GuppyFish.setLifeTime(Integer.parseInt(guppyFishLifeTime.getCharacters().toString()));
        }
        catch (Exception exception){
            guppyFishLifeTime.setText(String.valueOf(GuppyFish.getLifeTime()));
            result = false;
        }

        return result;
    }

    private void setProbParameters(){
        Parameters.setProbGuppyFish(Double.parseDouble(guppyFishProb.getValue().replace("%", "")) / 100);
        Parameters.setProbGoldFish(Double.parseDouble(goldFishProb.getValue().replace("%", "")) / 100);
    }

    private void setAIPriority(){
        goldFishAI.setPriority(Integer.parseInt(goldAIPriorityBox.getValue()));
        guppyFishAI.setPriority(Integer.parseInt(guppyAIPriorityBox.getValue()));
    }


    @FXML
    public void start(ActionEvent actionEvent){
        if (!setSpawnField()){
            error.show();
            return;
        }
        setProbParameters();
        setAIPriority();
        startView();
        startTime.set(new Date().getTime() - simulationTime.getTime() - 1000);
        if (goldAICheckBox.isSelected())
            goldFishAI.activate();
        if (guppyAICheckBox.isSelected())
            guppyFishAI.activate();
        timer.start();
    }


    @FXML
    public void stop(ActionEvent actionEvent){
        if (infoButton.isSelected()){
            timer.stop();
            createStatisticStage();
        }
        else{
            pauseView();
            timer.stop();
        }
        goldFishAI.deactivate();
        guppyFishAI.deactivate();
    }
    public void keyPress() {
        workspace.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.T) {
                    showTimeView(simulationTime.getIsActive());
                    if (!simulationTime.getIsActive()) {
                        simulationTime.show();
                    }
                    else {
                        simulationTime.hide();
                    }
                }
                else if (keyEvent.getCode() == KeyCode.B) {
                    startButton.fire();
                }
                else if (keyEvent.getCode() == KeyCode.E) {
                    stopButton.fire();
                }

            }
        });
    }
    public void showInfo(ActionEvent actionEvent) {
        if (infoMenuItem.isSelected()) {
            infoButton.setSelected(true);
        }
        else {
            infoButton.setSelected(false);
        }
    }
    public void showTime(ActionEvent actionEvent) {
        Robot robot = new Robot();
        robot.keyPress(KeyCode.T);
    }

    public void hideTime(ActionEvent actionEvent) {
        Robot robot = new Robot();
        robot.keyPress(KeyCode.T);
    }

    private void setParameters() {
        Parameters.setProbGoldFish(0.6);
        Parameters.setProbGuppyFish(0.8);
        Parameters.setSpawnTimeGoldFish(2);
        Parameters.setSpawnTimeGuppyFish(5);
        Parameters.setScreenHeight(simulationArea.getPrefHeight());
        Parameters.setScreenWidth(simulationArea.getPrefWidth());
        Parameters.setVelocityFish(5.0);
    }

    private void setDefaultLifeTime(){
        GoldFish.setLifeTime(6);
        GuppyFish.setLifeTime(10);
    }

    final LongProperty lastUpdate = new SimpleLongProperty();
    final long updateInterval = 1000000000;
    AnimationTimer timer = new AnimationTimer(){
        @Override
        public void handle(long now) {
            if (now - lastUpdate.get() > updateInterval) {
                List<Fish> newFishes = model.update(startTime);
                synchronized (FishData.getFishesList()){
                    FishData.getFishesList().addAll(newFishes);
                }
                FishData.getId().addAll(newFishes.stream().map(Fish::getId).toList());
                for (Fish obj : newFishes){
                    FishData.getBirthTime().put(obj.getId(), obj.getBirthTime());
                }
                simulationTime.set(new Date().getTime() - startTime.get());
                synchronized (FishData.getFishesList()){
                    model.deadFish(simulationTime.getTimeInSeconds());
                }
                lastUpdate.set(now);


            }
            simulationArea.getChildren().setAll(FishData.getFishesList().stream().map(Fish::toImageView).toList());
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle rb){
        super.initialize();
        connectionStatistic.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                if (connectionStatistic.get() == 0)
                    return;
                if (connectionStatistic.get() == 1){
                    simulationTime.set(0);
                    FishData.clearData();
                    simulationArea.getChildren().clear();
                    GoldFish.clearCountObjects();
                    GuppyFish.clearCountObjects();
                    stopView();
                    connectionStatistic.set(0);
                }
                if (connectionStatistic.get() == 2){
                    connectionStatistic.set(0);
                    pauseView();
                }
            }
        });
        keyPress();
        setParameters();
        setDefaultLifeTime();
        setFishesProbValue(Parameters.getProbGoldFish(), Parameters.getProbGuppyFish());
        setFishesSpawnTime(Parameters.getSpawnTimeGoldFish(), Parameters.getSpawnTimeGuppyFish());
        timeBox.getChildren().add(simulationTime);
        simulationTime.setStyle("-fx-font-size: 15px; -fx-font-family: Times New Roman;");
        goldFishAI.start();
        guppyFishAI.start();
    }

    @FXML
    public void exit(ActionEvent actionEvent){
        System.exit(0);
    }
}