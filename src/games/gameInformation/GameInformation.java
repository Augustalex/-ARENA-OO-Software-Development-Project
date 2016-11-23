package games.gameInformation;

/**
 * Template containing key information about a game.
 */
public class GameInformation {
    private String gameName;
    private String imageURL;
    private String gameDescription;

    public GameInformation(String gameName, String gameDescription, String imageURL){
        this.setGameName(gameName);
        this.setGameDescription(gameDescription);
        this.setImageURL(imageURL);
    }

    /**
     * Method for setting a name to a game.
     * @param gameName
     * @return this.GameInformation
     */
    public GameInformation setGameName(String gameName){
        this.gameName = gameName;
        return this;
    }

    /**
     * Sets a description for the game.
     * @param gameDescription
     * @return this.GameInformation object
     */
    public GameInformation setGameDescription(String gameDescription){
        this.gameDescription = gameDescription;
        return this;
    }

    /**
     * Method for setting a image URL to a game.
     * @param imageURL
     * @return this.gameLibrary.gameInformation object
     */
    public GameInformation setImageURL(String imageURL){
        this.imageURL = imageURL;
        return this;
    }

    /**
     * Method for getting the name of a game
     * @return String gameName
     */
    public String getGameName(){
        return gameName;
    }

    /**
     * Method for getting the description of a game.
     * @return String gameDescription
     */
    public String getGameDescription(){
        return gameDescription;
    }

    /**
     * Method for getting the image URL of the game.
     * @return String imageURL
     */
    public String getImageURL(){
        return imageURL;
    }
}
