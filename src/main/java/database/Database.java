package database;

import com.mysql.fabric.FabricConnection;
import data.FishData;
import data.Parameters;
import objects.Fish;
import objects.GoldFish;
import objects.GuppyFish;
import sample.Main;

import java.sql.*;
import java.util.Objects;

public class Database {
    private static Connection connection;
    private static String location;
    private static Statement statement;

    static {
        location = Objects.requireNonNull(Database.class.getResource("database.db")).toExternalForm();
    }

    public static void connection(){
        try{
            //DriverManager.registerDriver();
            connection = DriverManager.getConnection("jdbc:sqlite:" + location);
            statement = connection.createStatement();
            System.out.println("Connection successful ");
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public static void drop(){
        try {
            statement.execute("DROP TABLE 'fish'");
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
    public static void createDB(){
        try {
            statement.execute("CREATE TABLE if not exists 'fish' ('id' INT, 'type' text, 'cordX' REAL, 'cordY' REAL) ");
        }
        catch (Exception exception){
            System.out.println(exception.getMessage() + "aboba");
        }
    }

    public static void removeDB() throws SQLException{
        statement.execute("DELETE FROM fish");
    }

    public static void writeDB(){
        try{
            removeDB();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        FishData.getFishesList().stream().filter(obj -> obj instanceof GoldFish).forEach(obj -> {
            try {
                statement.execute("INSERT INTO 'fish' ('id', 'type', 'cordX', 'cordY') VALUES " +
                        String.format("('%d', '%s', '%f', '%f')", obj.getId(), "GoldFish", obj.getCordX(), obj.getCordY()));
            }
            catch (Exception exception){
                System.out.println(exception.getMessage());
            }
        });
        FishData.getFishesList().stream().filter(obj -> obj instanceof GuppyFish).forEach(obj -> {
            try {
                statement.execute("INSERT INTO 'fish' ('id', 'type', 'cordX', 'cordY') VALUES " +
                        String.format("('%d', '%s', '%f', '%f')", obj.getId(), "GuppyFish", obj.getCordX(), obj.getCordY()));
            }
            catch (Exception exception){
                System.out.println(exception.getMessage());
            }
        });
    }

    public static void readDB() {
        try {
            ResultSet resSet = statement.executeQuery("SELECT * FROM fish");
            while (resSet.next()){
                Fish fish;
                if (resSet.getString("type").equals("GoldFish")){
                    fish = new GoldFish(0.9 * Parameters.getScreenHeight(), Parameters.getScreenWidth());
                }
                else{
                    fish = new GuppyFish(0.9 * Parameters.getScreenHeight(), Parameters.getScreenWidth());
                }
                int id = resSet.getInt("id");
                fish.setId(id);
                fish.setCordX(resSet.getDouble("cordX"));
                fish.setCordY(resSet.getDouble("cordY"));
                FishData.getFishesList().add(fish);
                FishData.getId().add(id);
            }
            resSet.close();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public static void closeDB(){
        try{
            connection.close();
            statement.close();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}
