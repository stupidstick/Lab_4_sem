package database;
import data.FishData;
import data.Parameters;
import objects.Fish;
import objects.GoldFish;
import objects.GuppyFish;
import sample.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Objects;

public class Database {
    private static final String user = "postgres";
    private static final String password = "admin";
    private static Connection connection;
    private static Statement statement;

    public static boolean connection(String ip, String port){
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://" + ip + ":" + port + "/postgres", user, password);
            statement = connection.createStatement();
            System.out.println("Connection successful ");
            createTable();
            return true;
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }
    public static void createTable(){
        try{
            statement.execute("CREATE TABLE IF NOT EXISTS public.fish (id integer, type character varying, cordx real, cordy real);");
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public static void removeDB(){
        try {
            statement.execute("DELETE FROM public.fish");
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
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
                statement.execute("INSERT INTO public.fish (id, type, cordx, cordy) VALUES " +
                        String.format("('%d', '%s', '%s', '%s')", obj.getId(), "GoldFish", String.valueOf(obj.getCordX()), String.valueOf(obj.getCordY())));
            }
            catch (Exception exception){
                System.out.println(exception.getMessage());
            }
        });
        FishData.getFishesList().stream().filter(obj -> obj instanceof GuppyFish).forEach(obj -> {
            try {
                statement.execute("INSERT INTO public.fish (id, type, cordx, cordy) VALUES " +
                        String.format("('%d', '%s', '%s', '%s')", obj.getId(), "GuppyFish", String.valueOf(obj.getCordX()), String.valueOf(obj.getCordY())));
            }
            catch (Exception exception){
                System.out.println(exception.getMessage());
            }
        });
    }

    public static void readDB() {
        try {
            ResultSet resSet = statement.executeQuery("SELECT * FROM public.fish");
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
                fish.setCordX(resSet.getDouble("cordx"));
                fish.setCordY(resSet.getDouble("cordy"));
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
            statement.close();
            connection.close();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}
