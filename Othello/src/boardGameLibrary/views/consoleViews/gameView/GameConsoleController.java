package boardGameLibrary.views.consoleViews.gameView;

import boardGameLibrary.boardGame.board.GameBoard;
import boardGameLibrary.boardGame.match.GameMatch;
import boardGameLibrary.eventWrappers.CellChangeEvent;
import boardGameLibrary.eventWrappers.CellClickEvent;
import boardGameLibrary.players.Player;
import boardGameLibrary.views.consoleViews.GameConsoleViewController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;

import java.awt.*;

/**
 * Created by August on 2016-10-20.
 */
public class GameConsoleController extends GameConsoleViewController {

    public GameConsoleController(GameMatch match){
        this.init(match);
        match.run();
    }

    private void init(GameMatch match){
        GameBoard board = match.getBoardMoveMaker().getGameBoard();
        ConsoleViewBoard viewBoard = ConsoleViewBoard.create(board);

        coupleBoards(board, viewBoard);
        printView(viewBoard);

        ObjectProperty<CellClickEvent> consoleInputEvent = new SimpleObjectProperty<>();

    }

    private void coupleBoards(GameBoard board, ConsoleViewBoard viewBoard){
        board.getCellChangeObserver().addListener((ListChangeListener<CellChangeEvent>) c -> {

            for(CellChangeEvent cellChange : c.getList()){
                Point position = cellChange.getPosition();

                Player owner = board.getPawn(position).getOwner();
                String name = owner.getName();

                viewBoard.put(position.x, position.y, name.charAt(0));
            }

            this.printView(viewBoard);
        });
    }

    private void attachClickEvent(ConsoleViewBoard viewBoard, GameBoard board, ObjectProperty<CellClickEvent> cellClickProperty){

        for(int y = 0; y < board.getBoundaries().height; y++){
            for(int x = 0; x < board.getBoundaries().width; x++){

            }
        }
    }

    private static class ConsoleViewBoard {
        Dimension dimension;
        char[][] board;

        public static ConsoleViewBoard create(GameBoard board){
            ConsoleViewBoard viewBoard = new ConsoleViewBoard(board.getBoundaries().width, board.getBoundaries().height);

            for(int y = 0; y < board.getBoundaries().height; y++){
                for(int x = 0; x < board.getBoundaries().width; x++){
                    String playerName = board.getPawn(new Point(x, y)).getOwner().getName();
                    viewBoard.put(x, y, Character.toUpperCase(playerName.charAt(0)));
                }
            }

            return viewBoard;
        }

        public ConsoleViewBoard(int width, int height) {
            this.dimension = new Dimension(width, height);
            this.board = new char[width][height];
        }

        public void put(int x, int y, char identifier) {
            this.board[x][y] = identifier;
        }

        public String getBoardPrint(){
            StringBuilder builder = new StringBuilder();

            for(int y = 0; y < this.dimension.height; y++){
                for(int x = 0; x < this.dimension.width; x++){
                    builder.append(" " + Character.toString(this.board[x][y]) + " |");
                }
                builder.append("\n");
            }

            return builder.toString();
        }
    }

    private void printView(ConsoleViewBoard viewBoard){
        bufferText("\nAUGUST V.S. BJÖRN\n\n");
        bufferText(viewBoard.getBoardPrint());
    }
}
