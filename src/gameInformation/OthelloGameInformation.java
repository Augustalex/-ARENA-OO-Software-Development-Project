package gameInformation;

/**
 * A predefined GameInformation class with contents regarding
 * the game "Othello" (also known as "Reversi").
 */
public class OthelloGameInformation extends GameInformation{

    private static final String name = "Othello";
    private static final String description = "Also known as Reversi. \nTwo players face each other in a match to death. \nWinner gets glory and fame.";
    private static final String imageURL = "/othello_image.jpg";

    public OthelloGameInformation() {
        super(OthelloGameInformation.name, OthelloGameInformation.description, OthelloGameInformation.imageURL);
    }
}
