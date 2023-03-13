package data;

import javafx.scene.layout.Priority;

public class Parameters {
    private static Double probGoldFish;
    private static Double probGuppyFish;
    private static int spawnTimeGoldFish;
    private static int spawnTimeGuppyFish;
    private static Double screenHeight;
    private static Double screenWidth;


    private static Double velocityFish;
    public static Double getProbGoldFish() {
        return probGoldFish;
    }

    public static Double getProbGuppyFish() {
        return probGuppyFish;
    }

    public static int getSpawnTimeGoldFish() {
        return spawnTimeGoldFish;
    }

    public static int getSpawnTimeGuppyFish() {
        return spawnTimeGuppyFish;
    }

    public static Double getScreenHeight() {
        return screenHeight;
    }

    public static Double getScreenWidth() {
        return screenWidth;
    }

    public static Double getVelocityFish() {
        return velocityFish;
    }

    public static void setProbGoldFish(Double probGoldFish) {
        Parameters.probGoldFish = probGoldFish;
    }

    public static void setProbGuppyFish(Double probGuppyFish) {
        Parameters.probGuppyFish = probGuppyFish;
    }

    public static void setSpawnTimeGoldFish(int spawnTimeGoldFish) {
        Parameters.spawnTimeGoldFish = spawnTimeGoldFish;
    }

    public static void setSpawnTimeGuppyFish(int spawnTimeGuppyFish) {
        Parameters.spawnTimeGuppyFish = spawnTimeGuppyFish;
    }

    public static void setScreenHeight(Double screenHeight) {
        Parameters.screenHeight = screenHeight;
    }

    public static void setScreenWidth(Double screenWidth) {
        Parameters.screenWidth = screenWidth;
    }

    public static void setVelocityFish(Double velocityFish) {
        Parameters.velocityFish = velocityFish;
    }
}
