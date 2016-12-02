package boardGameLibrary.playerProfileStore;

import javafx.scene.paint.Color;
import utilities.dataStore.Profile;

import java.io.Serializable;

/**
 * Created by August on 2016-10-25.
 */
public class PlayerProfile implements Profile, Serializable{

    private double red;
    private double green;
    private double blue;
    private String name;

    public PlayerProfile(String name, Color color){
        this.setName(name);
        this.setColor(color);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Color getColor() {
        return Color.color(this.red, this.green, this.blue);
    }

    @Override
    public void setColor(Color color) {
        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();
    }

    @Override
    public Profile copy() {
        return new PlayerProfile(this.getName(), this.getColor());
    }

    @Override
    public String toString(){
        return this.getName();
    }
}
