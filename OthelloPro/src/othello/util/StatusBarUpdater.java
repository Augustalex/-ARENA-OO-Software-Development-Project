/*
 * OOMU - laboration 2: Othello
 * Johan - s152754, Patric - s153364 och Simon - s152428
 * Grupp 05
 */
package othello.util;

import othello.model.player.*;

/**
 * Class for collecting arguments to be able to send arguments in more unified
 * form
 * @author Johan
 */
public class StatusBarUpdater {
    /**
     * Attributes to collect in a compound type to send as an argument
     */
    String name1;
    String color1;
    int score1;
    String name2;
    String color2;
    int score2;
    
    /**
     * Constructor initializing attributes
     * @param player1, holding attribute information
     * @param player2, holding attribute information
     * @param score1, current score of player1
     * @param score2, current score of player2
     */
    public StatusBarUpdater(Player player1, Player player2, int score1, int score2){
        name1 = player1.getName();
        color1 = player1.getMarker().getColour();
        this.score1 = score1;
        name2 = player2.getName();
        color2 = player2.getMarker().getColour();
        this.score2 = score2;
    }
    
    /**
     * Getter-method for returning name of player1
     * @return String
     */
    public String getName1(){
        return name1;
    }
    
    /**
     * Getter-method for returning color of player1
     * @return String
     */
    public String getColor1(){
        return color1;
    }
    
    /**
     * Getter-method for returning score of player1
     * @return int
     */
    public int getScore1(){
        return score1;
    }
    
    /**
     * Getter-method for returning name of player 2
     * @return String
     */
    public String getName2(){
        return name2;
    }
    
    /**
     * Getter-method for returning color of player 2
     * @return String
     */
    public String getColor2(){
        return color2;
    }
    
    /**
     * Getter-method for returning score of player 2
     * @return int
     */
    public int getScore2(){
        return score2;
    }
}
