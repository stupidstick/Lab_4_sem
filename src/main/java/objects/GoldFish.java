package objects;

import javafx.scene.image.Image;

import java.io.FileInputStream;

public class GoldFish extends Fish {
    private static int countObjects;
    private static long lifeTime;
    private static Image imageGoldFish;

    static {
        countObjects = 0;
        try {
            imageGoldFish = new Image(new FileInputStream(createPathToImage("GoldFish.png")));
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
    public GoldFish(double height, double width){
        super(height, width, 0.08, imageGoldFish);
        countObjects++;
    }
    public static int getCountObjects(){
        return countObjects;
    }
    public static void clearCountObjects(){countObjects = 0;}

    public static void setLifeTime(long lifeTime) {
        GoldFish.lifeTime = lifeTime;
    }

    public static long getLifeTime() {
        return lifeTime;
    }

    public static Image getImageGoldFish() {
        return imageGoldFish;
    }

    public static void setCountObjects(int countObjects) {
        GoldFish.countObjects = countObjects;
    }

}
