package ai;

import data.FishData;
import data.Parameters;
import objects.Fish;
import objects.GuppyFish;

import java.util.List;
import java.util.Map;

public class GuppyFishAI extends BaseAI{
    public GuppyFishAI(){
        super((Map<Integer, Integer> moveDirection) ->{
            List<Fish> guppyFishList = FishData.getFishesList().stream().filter(obj -> obj instanceof GuppyFish).toList();
            FishData.getFishesList().forEach(obj->moveDirection.putIfAbsent(obj.getId(), 1));
            guppyFishList.forEach(obj -> obj.setCordY(obj.getCordY() + moveDirection.get(obj.getId())*Parameters.getVelocityFish()));
            guppyFishList.stream().filter(obj -> (obj.getCordY() + obj.getImageHeight()) >= Parameters.getScreenHeight()).forEach(obj-> moveDirection.put(obj.getId(), -moveDirection.get(obj.getId()) ));
            guppyFishList.stream().filter(obj -> obj.getCordY() <= 0).forEach(obj-> moveDirection.put(obj.getId(), -moveDirection.get(obj.getId()) ));
        });
    }
}

