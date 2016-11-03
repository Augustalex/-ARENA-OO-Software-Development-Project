package model;

/**
 * Created by Simon on 03/11/2016.
 */
public class OthelloGameInformation extends GameInformation{

    static String name = "Othello";
    static String description = "Also know as Reversi. \nTwo players face eachother in a match to death. \nWinner gets glory and fame.";
    static String imageURL = "othello_image.jpg";

    public OthelloGameInformation() {
        super(OthelloGameInformation.name, OthelloGameInformation.description, OthelloGameInformation.imageURL);

    }

}
