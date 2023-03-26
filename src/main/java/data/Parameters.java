package data;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Priority;
import objects.GoldFish;
import objects.GuppyFish;

import java.io.*;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Parameters {
    private static DoubleProperty probGoldFish = new SimpleDoubleProperty();
    private static Double probGuppyFish;
    private static int spawnTimeGoldFish;
    private static int spawnTimeGuppyFish;
    private static Double screenHeight;
    private static Double screenWidth;


    private static Double velocityFish;
    public static Double getProbGoldFish() {
        return probGoldFish.get();
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
        Parameters.probGoldFish.set(probGoldFish);;
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

    public static DoubleProperty probGoldFishProperty() {
        return probGoldFish;
    }

    public static void consoleDispatcher() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        Pattern patternSetProb = Pattern.compile("^set gold fish probability ((0\\.\\d)|(1))");
        Pattern patternGetProb = Pattern.compile("^get gold fish probability");

        if (patternSetProb.matcher(command).matches()) {
            setProbGoldFish(Double.parseDouble(command.split(" ")[command.split(" ").length - 1]));
            System.out.println("Probability has been set successfully");
        }
        else if (patternGetProb.matcher(command).matches()) {
            System.out.println("Gold fish spawn probability is " + getProbGoldFish());
        }
        else {
            System.out.println("Wrong command");
        }
    }

    public static void saveParameters() throws IOException {
        FileWriter fileWriter = new FileWriter("parameters.txt", false);
        fileWriter.write(getProbGoldFish() + "\n");
        fileWriter.write(getProbGuppyFish() + "\n");
        fileWriter.write(getSpawnTimeGoldFish() + "\n");
        fileWriter.write(getSpawnTimeGuppyFish() + "\n");
        fileWriter.write(GoldFish.getLifeTime() + "\n");
        fileWriter.write(GuppyFish.getLifeTime() + "\n");
        fileWriter.close();
    }

    public static void readParameters() throws IOException {
        FileReader fileReader = new FileReader("parameters.txt");
        char[] buf = new char[256];
        int c;
        while ((c = fileReader.read(buf)) != -1) {
            if (c < 256) {
                buf = Arrays.copyOf(buf, c);
            }
        }
        String[] values = String.valueOf(buf).split("\n");

        setProbGoldFish(Double.parseDouble(values[0]));
        setProbGuppyFish(Double.parseDouble(values[1]));
        setSpawnTimeGoldFish(Integer.parseInt(values[2]));
        setSpawnTimeGuppyFish(Integer.parseInt(values[3]));
        GoldFish.setLifeTime(Integer.parseInt(values[4]));
        GuppyFish.setLifeTime(Integer.parseInt(values[5]));

        fileReader.close();
    }

    public static String parametersToString(){
        return String.valueOf(getProbGoldFish()) + " " + String.valueOf(probGuppyFish) + " " + String.valueOf(spawnTimeGoldFish) + " " + String.valueOf(spawnTimeGuppyFish) + " " +  String.valueOf(GoldFish.getLifeTime()) + " " + String.valueOf(GuppyFish.getLifeTime());
    }
    public static void setParametersFromString(String input) {
        String[] parameters = input.split(" ");
        setProbGoldFish(Double.parseDouble(parameters[0]));
        setProbGuppyFish(Double.parseDouble(parameters[1]));
        setSpawnTimeGoldFish(Integer.parseInt(parameters[2]));
        setSpawnTimeGuppyFish(Integer.parseInt(parameters[3]));
        GoldFish.setLifeTime(Integer.parseInt(parameters[4]));
        GuppyFish.setLifeTime(Integer.parseInt(parameters[5]));
    }
}
