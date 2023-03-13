package ai;

import Interfaces.Movable;
import data.FishData;
import data.Parameters;
import javafx.animation.AnimationTimer;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import objects.Fish;

import java.util.*;

public abstract class BaseAI extends Thread {
    private Map<Integer, Integer> moveDirection;
    private Movable movable;
    private boolean isActive;
    private Object checkAi = new Object();

    public Object getCheckAi() {
        return checkAi;
    }

    public BaseAI(Movable movable){
        this.movable = movable;
        moveDirection = new HashMap<>();
        isActive = true;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
    public boolean getActive(){
        return isActive;
    }

    public void activate(){
        isActive = true;
        synchronized (checkAi){
            checkAi.notify();
        }
    }

    public void deactivate(){
        isActive = false;
    }
    @Override
    public void run() {
        long minUpdateInterval = 10;
        long lastUpdate = 0;
        while (true){
            long now = new Date().getTime();
            if (now - lastUpdate > minUpdateInterval){
                synchronized (checkAi){
                    if (!isActive)
                    {
                        try {
                            checkAi.wait();
                        }
                        catch (Exception exception){
                            System.out.println(exception.getMessage());
                        }
                    }
                }
                synchronized (FishData.getFishesList()){
                    movable.move(moveDirection);
                }
                lastUpdate = now;
            }
        }
    }
}
