package data;

import objects.Fish;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class FishData {
    private static List<Fish> fishesList = new LinkedList<>();
    private static HashSet<Integer> id = new HashSet<>();
    private static TreeMap<Integer, Integer> birthTime = new TreeMap<>();

    public static void clearData(){
        fishesList.clear();
        id.clear();
        birthTime.clear();
    }

    public static HashSet<Integer> getId() {
        return id;
    }

    public static List<Fish> getFishesList() {
        return fishesList;
    }

    public static TreeMap<Integer, Integer> getBirthTime() {
        return birthTime;
    }
}
