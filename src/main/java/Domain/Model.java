package Domain;

import data.FishData;
import data.Parameters;
import javafx.beans.property.LongProperty;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import objects.Fish;
import objects.GoldFish;
import objects.GuppyFish;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Model {

    public void deadFish(int simulationTime){
        List<Fish> deadFishList = new ArrayList<>(FishData.getFishesList().stream().filter(obj -> obj instanceof GoldFish).filter(obj -> (simulationTime - obj.getBirthTime()) >= GoldFish.getLifeTime()).toList());
        deadFishList.addAll(FishData.getFishesList().stream().filter(obj -> obj instanceof GuppyFish).filter(obj -> (simulationTime - obj.getBirthTime()) >= GuppyFish.getLifeTime()).toList());
        FishData.getFishesList().removeAll(deadFishList);
        deadFishList.stream().map(Fish::getId).forEach(FishData.getId()::remove);
        deadFishList.stream().map(Fish::getId).forEach(FishData.getBirthTime()::remove);
    }
    public List<Fish> update(LongProperty startTime){
        List<Fish> fishesList = new ArrayList<>();
        int beginTime = (int)((new Date().getTime() - startTime.get()) / Math.pow(10, 3));
        if (beginTime % Parameters.getSpawnTimeGuppyFish() == 0 && (Math.random() <= Parameters.getProbGuppyFish())){
            GuppyFish guppyFish = new GuppyFish(0.9 * Parameters.getScreenHeight(), Parameters.getScreenWidth());
            guppyFish.setBirthTime(beginTime);
            fishesList.add(guppyFish);
        }
        if (beginTime % Parameters.getSpawnTimeGoldFish() == 0 && (Math.random() <= Parameters.getProbGoldFish())){
            GoldFish goldFish = new GoldFish(0.9 * Parameters.getScreenHeight(), Parameters.getScreenWidth());
            goldFish.setBirthTime(beginTime);
            fishesList.add(goldFish);
        }
        return fishesList;
    }
}
