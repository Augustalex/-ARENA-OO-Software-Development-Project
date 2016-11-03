package model;

/**
 * Created by Simon on 03/11/2016.
 */
public class GameInformation {
    String gameName;
    String imageURL;
    String gameDescription;

    public GameInformation(String gameName, String gameDescription, String imageURL){
        this.setGameName(gameName);
        this.setGameDescription(gameDescription);
        this.setImageURL(imageURL);
    }

    public GameInformation setGameName(String gameName){
        this.gameName = gameName;
        return this;
    }

    /**
     * Sets a description for the game.
     * @param gameDescription
     * @return GameInformation object
     */
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
