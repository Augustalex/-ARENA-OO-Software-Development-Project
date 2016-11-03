package model;

/**
 * Created by Simon on 03/11/2016.
 */
public class GameInformation {
    String gameName;
    String imageURL;
    String gameDescription;

    public GameInformation(String gameName, String gameDescription, String imageURL){}

    public GameInformation setGameName(String gameName){
        this.gameName = gameName;
        return this; // vad ska den returnera
    }

    public GameInformation setGameDescription(String gameDescription){
        this.gameDescription = gameDescription;
        return this;
    }

    public GameInformation setImageURL(String imageURL){
        this.imageURL = imageURL;
        return this;
    }

    public String getGameName(){
        return gameName;
    }

    public String getGameDescription(){
        return gameDescription;
    }

    public String getImageURL(){
        return imageURL;
    }
}
