/*
 * OOMU - laboration 2: Othello
 * Johan - s152754, Patric - s153364 och Simon - s152428
 * Grupp 05
 */
package othello.view;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import othello.controller.GameManager.CellHandler;


/**
 * Class represents the gameboard in which a othello-game is played. It also 
 * contains eventhandlers for GUI-input that are activated in the cells that
 * represent squares in the board.
 * @author Johan
 */
public class GameBoard {
    /**
     * Attributes:
     * GridPane represents the form of the board, holding a cell-matrix to 
     * represent every sqaure. Highlighted cell represents marked cell, by mouse
     * or keyboard.
     */
    private GridPane pane;
    private final int GAMEBOARD_SIZE = 8;
    private Cell[][] cell = new Cell[GAMEBOARD_SIZE][GAMEBOARD_SIZE];
    private Cell highlightedCell;
    
    /**
     * initializes the attributes, connectes the cell-matrix to the gridpane and 
     * creates two eventhandlers-instances to be sent to every cell in the board.
     */
    public GameBoard(){
        pane = new GridPane();
        MouseInputHandler mouseHandler = new MouseInputHandler();
        KeyInputHandler keyHandler = new KeyInputHandler();
        for (int i = 0; i < GAMEBOARD_SIZE; i++)
            for (int j = 0; j < GAMEBOARD_SIZE; j++)
                pane.add(cell[i][j] = new Cell(keyHandler, mouseHandler, i, j),j,i);
    }
    
    /**
     * @return the board to the GUI
     */
    public GridPane getPane(){   
        return pane;
    }
    
    /**
     * Method uses a helpermethod to print a single sqaure in the board.
     * @param color, the marker to be painted in square
     * @param i, row of square
     * @param j, column of square
     */
    public void printCell(String color, int i, int j){
        if(!color.equals("")){
            addMarkerPiece(color, i, j);
        }
    }
    
    /**
     * Method initiates board when new game is created: activates inputevents
     * in every cell and set an initial highlighted cell.
     */
    public void initNewBoard(){
        for(int i = 0; i < GAMEBOARD_SIZE; i++){
            for(int j = 0; j < GAMEBOARD_SIZE; j++){
                cell[i][j].setInputEvents();
            }
        }
        highlightedCell = cell[0][0];
        highlightedCell.highlight();
        highlightedCell.requestFocus();
    }
    
    /**
     * @return the size of gameboard
     */
    public int getBoardSize(){
        return GAMEBOARD_SIZE;
    }
    
    /**
     * @return the cell that is currently highlighted
     */
    public Cell getHighlightedCell(){
        return highlightedCell;
    }
    
    /**
     * Method creates and shows a marker in the board.
     * @param color, color of marker to be painted
     * @param i, row of square to be painted
     * @param j, column of square to be painted
     */
    private void addMarkerPiece(String color, int i, int j){
            cell[i][j].getChildren().clear();
            Circle circle = new Circle();
            circle.setStyle("-fx-fill: " + color + "; -fx-stroke: " + color + ";");
            circle.centerXProperty().bind(cell[i][j].widthProperty().divide(2));
            circle.centerYProperty().bind(cell[i][j].heightProperty().divide(2));
            circle.radiusProperty().bind(cell[i][j].widthProperty().divide(2).subtract((10)));
            cell[i][j].getChildren().add(circle);
    }
    
    /**
     * Method sets a new cell as highlighted
     * @param i, row of cell to highlight
     * @param j, column of cell to highlight
     */
    private void setNewHighlightedCell(int i, int j){
        highlightedCell.deHighlight();
        (highlightedCell = cell[i][j]).highlight();
    }
    
    /**
     * Eventhandler lets player change highlighted cell and select a move by
     * using keyboard
     */
    public class KeyInputHandler implements EventHandler<KeyEvent>{
        @Override
        public void handle(KeyEvent event) {
            int i = highlightedCell.getI();
            int j = highlightedCell.getJ();
            switch((KeyCode)event.getCode()){
                case UP:
                    if(i > 0)
                        (highlightedCell = cell[i-1][j]).highlight();
                    break;
                case RIGHT:
                    if(j < 7)
                        (highlightedCell = cell[i][j+1]).highlight();
                    break;
                case DOWN:
                    if(i < 7)
                        (highlightedCell = cell[i+1][j]).highlight();
                    break;
                case LEFT:
                    if(j > 0)
                        (highlightedCell = cell[i][j-1]).highlight();
                    break;
                case ENTER:
                    new CellHandler().handle(event);
            }
            if(cell[i][j] != highlightedCell)
                cell[i][j].deHighlight();
            highlightedCell.requestFocus();
        }       
    }
    
    /**
     * Eventhandler that lets user highlight and mark cells by using mouse
     */
    public class MouseInputHandler implements EventHandler<MouseEvent>{
        @Override
        public void handle(MouseEvent event) {
            Object object = event.getSource();
            int i = ((Cell)object).getI();
            int j = ((Cell)object).getJ();
            if(event.getButton() == MouseButton.PRIMARY)
                new CellHandler().handle(event);
            else
                setNewHighlightedCell(i,j);
            highlightedCell.requestFocus();
        }
        
    }
}
