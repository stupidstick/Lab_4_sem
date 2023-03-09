package controller;

import javafx.scene.text.Text;

public class TimeView extends Text {
    private boolean isActive;
    private long time;
    TimeView(){
        super();
        hide();
        time = 0;
    }

    public void show(){
        setText(String.format("Runtime in seconds: %d", (int) (time / 1000)));
        isActive = true;
    }

    public void hide(){
        setText("");
        isActive = false;
    }

    public int getTimeInSeconds(){
        return (int) (time / 1000);
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void set(long time){
        this.time = time;
        if (isActive)
            show();
    }

    public boolean getIsActive(){
        return isActive;
    }

    public void setIsActive(boolean status){
        isActive = status;
    }
}
