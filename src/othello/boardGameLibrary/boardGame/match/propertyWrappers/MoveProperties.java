package boardGameLibrary.boardGame.match.propertyWrappers;

import boardGameLibrary.boardGame.move.CalculatedMove;
import boardGameLibrary.eventWrappers.CellClickEvent;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.ArrayList;

/**
 * Created by August on 2016-10-22.
 */
public class MoveProperties {

    private ObjectProperty<ArrayList<CalculatedMove>> legalMovesProperty = new SimpleObjectProperty<>();
    private ObjectProperty<CellClickEvent> cellClickProperty = new SimpleObjectProperty<>();

    public ObjectProperty<ArrayList<CalculatedMove>> legalMovesProperty(){
        return this.legalMovesProperty;
    }

    public ObjectProperty<CellClickEvent> cellClickProperty(){
        return this.cellClickProperty;
    }


}
