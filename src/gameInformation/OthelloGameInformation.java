package gameInformation;

/**
 * Created by Simon on 03/11/2016.
 */
public class OthelloGameInformation extends GameInformation{

    static final String name = "Othello";
    static final String description = "Also known as Reversi. \nTwo players face eachother in a match to death. \nWinner gets glory and fame.";
    static final String imageURL = "/othello_image.jpg";

    public OthelloGameInformation() {
        super(OthelloGameInformation.name, OthelloGameInformation.description, OthelloGameInformation.imageURL);
    }
}
