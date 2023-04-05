package data;

import objects.GoldFish;
import objects.GuppyFish;

import java.io.Serializable;

public class ParametersDTO implements Serializable {
    private int spawnTimeGoldFish;
    private int spawnTimeGuppyFish;
    private double probGoldFish;
    private double probGuppyFish;
    private long lifeTimeGoldFish;
    private long lifeTimeGuppyFish;

    public ParametersDTO(){
        spawnTimeGoldFish = Parameters.getSpawnTimeGoldFish();
        spawnTimeGuppyFish = Parameters.getSpawnTimeGuppyFish();
        probGoldFish = Parameters.getProbGoldFish();
        probGuppyFish = Parameters.getProbGuppyFish();
        lifeTimeGoldFish = GoldFish.getLifeTime();
        lifeTimeGuppyFish = GuppyFish.getLifeTime();
    }

    public int getSpawnTimeGoldFish() {
        return spawnTimeGoldFish;
    }

    public void setSpawnTimeGoldFish(int spawnTimeGoldFish) {
        this.spawnTimeGoldFish = spawnTimeGoldFish;
    }

    public int getSpawnTimeGuppyFish() {
        return spawnTimeGuppyFish;
    }

    public void setSpawnTimeGuppyFish(int spawnTimeGuppyFish) {
        this.spawnTimeGuppyFish = spawnTimeGuppyFish;
    }

    public double getProbGoldFish() {
        return probGoldFish;
    }

    public void setProbGoldFish(double probGoldFish) {
        this.probGoldFish = probGoldFish;
    }

    public double getProbGuppyFish() {
        return probGuppyFish;
    }

    public void setProbGuppyFish(double probGuppyFish) {
        this.probGuppyFish = probGuppyFish;
    }

    public long getLifeTimeGoldFish() {
        return lifeTimeGoldFish;
    }

    public void setLifeTimeGoldFish(long lifeTimeGoldFish) {
        this.lifeTimeGoldFish = lifeTimeGoldFish;
    }

    public long getLifeTimeGuppyFish() {
        return lifeTimeGuppyFish;
    }

    public void setLifeTimeGuppyFish(long lifeTimeGuppyFish) {
        this.lifeTimeGuppyFish = lifeTimeGuppyFish;
    }
}
