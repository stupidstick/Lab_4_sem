package ai;

import Interfaces.Movable;
import data.FishData;
import data.Parameters;
import objects.Fish;
import objects.GoldFish;

import java.util.List;
import java.util.Map;

public class GoldFishAI extends BaseAI {
    public GoldFishAI(){
        super((Map<Integer, Integer> moveDirection) ->{
                List<Fish> goldFishList = FishData.getFishesList().stream().filter(obj -> obj instanceof GoldFish).toList();
                FishData.getFishesList().forEach(obj->moveDirection.putIfAbsent(obj.getId(), 1));
                goldFishList.forEach(obj -> obj.setCordX(obj.getCordX() + moveDirection.get(obj.getId())*Parameters.getVelocityFish()));
                goldFishList.stream().filter(obj -> (obj.getCordX() + obj.getImageWidth()) >= Parameters.getScreenWidth()).forEach(obj-> moveDirection.put(obj.getId(), -moveDirection.get(obj.getId()) ));
                goldFishList.stream().filter(obj -> obj.getCordX() <= 0).forEach(obj-> moveDirection.put(obj.getId(), -moveDirection.get(obj.getId()) ));
            });
        }

}
