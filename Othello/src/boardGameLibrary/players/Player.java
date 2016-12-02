package boardGameLibrary.players;

import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.boardGame.match.propertyWrappers.MoveProperties;
import javafx.scene.paint.Color;

import java.io.Serializable;

/**
 * Created by August on 2016-09-30.
 */
public abstract class Player implements Serializable{

    protected String name;
    private double red;
    private double green;
    private double blue;
    private double opacity;

    public Player(String name, Color color){
        this.name = name;

        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();
        this.opacity = color.getOpacity();
    }

    public abstract void makeMove(BoardMoveMaker boardMoveMaker, MoveProperties moveProperties);

    public Color getColor(){
        return Color.color(this.red, this.green, this.blue, this.opacity);
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String toString(){
        return this.getName();
    }
}
