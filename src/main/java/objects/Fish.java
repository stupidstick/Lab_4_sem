package objects;

import Interfaces.IBehaviour;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;


public abstract class Fish implements IBehaviour {
    private double cordX, cordY;
    private double imageHeight, imageWidth;
    private int id;
    private int birthTime;
    protected Image image;
    Fish(double height, double width, double multiply){
        id = GoldFish.getCountObjects() + GuppyFish.getCountObjects();
        imageWidth = (width * multiply);
        imageHeight = (width * multiply);
        cordX = Math.random() * (width - imageWidth);
        cordY = Math.random() * (height - imageHeight);
    }
    public double getCordX(){
        return cordX;
    }
    public double getCordY(){
        return cordY;
    }

    public void setCordX(double cordX) {
        this.cordX = cordX;
    }

    public void setCordY(double cordY) {
        this.cordY = cordY;
    }

    public Image getImage(){
        return image;
    }

    public double getImageHeight(){
        return imageHeight;
    }

    public double getImageWidth(){
        return imageWidth;
    }
    public int getId(){
        return id;
    }

    public int getBirthTime() {
        return birthTime;
    }

    public void setBirthTime(int birthTime) {
        this.birthTime = birthTime;
    }

    protected String createPathToImage(String imgName){
        return String.format("%s\\src\\main\\resources\\images\\%s", new File("").getAbsolutePath(), imgName);
    }

    public ImageView toImageView(){
        ImageView imgView = new ImageView();
        imgView.setImage(image);
        imgView.setLayoutX(cordX);
        imgView.setLayoutY(cordY);
        imgView.setFitWidth(imageWidth);
        imgView.setFitHeight(imageHeight);
        return imgView;
    }
}
