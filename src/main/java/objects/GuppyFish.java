package objects;

import javafx.scene.image.Image;

import java.io.FileInputStream;

public class GuppyFish extends Fish {
    private static int countObjects = 0;
    private static long lifeTime;
    public GuppyFish(double height, double width){
        super(height, width, 0.08);
        countObjects++;
        try {
            image = new Image(new FileInputStream(createPathToImage("GuppyFish.png")));
        }
        catch (Exception ex){
            image = null;
        }
    }

    public static int getCountObjects(){
        return countObjects;
    }
    public static void clearCountObjects(){countObjects = 0;}

    public static void setLifeTime(long lifeTime) {
        GuppyFish.lifeTime = lifeTime;
    }

    public static long getLifeTime() {
        return lifeTime;
    }
}
