package Interfaces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public interface IBehaviour {
    public double getCordX();
    public double getCordY();
    //public Image getImage();
    public double getImageHeight();
    public double getImageWidth();

    public ImageView toImageView();
}
