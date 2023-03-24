package objects;

import javafx.scene.image.Image;

import java.io.FileInputStream;

public class GuppyFish extends Fish {
    private static int countObjects = 0;
    private static long lifeTime;
    private static Image imageGuppyFish;

    static {
        countObjects = 0;
        try {
            imageGuppyFish = new Image(new FileInputStream(createPathToImage("GuppyFish.png")));
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
    public GuppyFish(double height, double width){
        super(height, width, 0.08, imageGuppyFish);
        countObjects++;
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

    public static Image getImageGuppyFish() {
        return imageGuppyFish;
    }

    public static void setCountObjects(int countObjects) {
        GuppyFish.countObjects = countObjects;
    }
}
