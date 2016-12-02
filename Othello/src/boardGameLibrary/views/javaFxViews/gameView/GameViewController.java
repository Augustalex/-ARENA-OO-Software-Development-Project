package boardGameLibrary.views.javaFxViews.gameView;

import boardGameLibrary.boardGame.board.GameBoard;
import boardGameLibrary.boardGame.match.BoardSnapshot;
import boardGameLibrary.boardGame.match.GameMatch;
import boardGameLibrary.boardGame.match.MatchSetup;
import boardGameLibrary.boardGame.move.CalculatedMove;
import boardGameLibrary.boardGame.pawn.Pawn;
import boardGameLibrary.boardGame.pawn.PawnDisplayModel;
import boardGameLibrary.eventWrappers.CellChangeEvent;
import boardGameLibrary.eventWrappers.CellClickEvent;
import boardGameLibrary.players.Player;
import boardGameLibrary.viewModel.ViewDimensionBinder;
import boardGameLibrary.viewModel.gameBoard.GameBoardFactory;
import boardGameLibrary.viewModel.gameBoard.cell.Cell;
import boardGameLibrary.viewModel.gameBoard.cellMarker.CellMarker;
import boardGameLibrary.views.javaFxViews.FXMLViewController;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by August on 2016-09-29.
 */
public class GameViewController extends FXMLViewController{
    
    private static final String fxmlFileName = "GameView.fxml";

    private GameMatch match;

    private MatchSetup setup;

    @FXML
    private HBox gameBoardContainer;

    @FXML
    private Label playerNameHolder;

    @FXML
    private HBox statsContainer;
    
    public GameViewController(Pane container, MatchSetup setup){
        super(container, GameViewController.fxmlFileName);

        this.setup = setup;
        this.match = GameMatch.setupNewMatch(setup);

        setup.setBoardSnapshot(new BoardSnapshot(this.match.getBoardMoveMaker().getGameBoard().getBoundaries()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Game board boundaries
        Dimension boundaries = this.match.getBoardMoveMaker().getGameBoard().getBoundaries();

        //Creating GameBoard (view object) and adding it to the container.
        Pane gameBoard = GameBoardFactory.createBoard(gameBoardContainer, boundaries.width);
        gameBoardContainer.getChildren().setAll(gameBoard);

        //Bind dimensions of pane containers.
        bindContainerDimensionsToBoard(gameBoardContainer, gameBoard);

        //Setup changing information updates and notifiers
        alertControllerOnBoardViewInteraction(gameBoard, this.match.getBoardMoveMaker().getGameBoard(), match.getMoveProperties().cellClickProperty());
        updateViewOnBoardChanges(this.match.getBoardMoveMaker().getGameBoard(), gameBoard);
        alwaysDisplayAvailableMoves(gameBoard, this.match.getMoveProperties().legalMovesProperty(), boundaries);

        //Display game statistics and information
        displayPlayerNames(this.match.getPlayers(), playerNameHolder);
        displayStats(this.match);

        match.run();
    }

    private void displayPlayerNames(Player[] players, Label nameHolder){
        String nameHolderText = "";

        for(int i = 0; i < players.length; i++)
            if(i == players.length-1)
                nameHolderText += players[i].getName();
            else
                nameHolderText += players[i].getName() + " v.s. ";

        nameHolder.setText(nameHolderText);
    }

    public void bindContainerDimensionsToBoard(Pane boardContainer, Pane board){
        boardContainer.widthProperty().addListener((e) -> ViewDimensionBinder.squareBindTo(board, boardContainer));
        boardContainer.heightProperty().addListener((e) -> ViewDimensionBinder.squareBindTo(board, boardContainer));
        ViewDimensionBinder.squareBindTo(board, boardContainer);
    }

    public void updateViewOnBoardChanges(GameBoard board, Pane boardView){
        Dimension boundaries = board.getBoundaries();
        ObservableList<Node> viewCells = boardView.getChildren();

        board.getCellChangeObserver().addListener((ListChangeListener<CellChangeEvent>) c -> {

            for(CellChangeEvent cellChange : c.getList()){

                Point position = cellChange.getPosition();

                //System.out.println("FLIPPED PAWN AT " + position);

                Pawn pawn = board.getPawn(position);
                Cell cell = (Cell) viewCells.get((position.y * boundaries.width) + position.x);

                Shape shape = pawn.getDisplayModel().getShape();
                shape.setFill(pawn.getDisplayModel().getPaint());

                cell.markCell(CellMarker.create(shape));

                this.setup.getSnapshot().setOwnerAt(pawn.getOwner(), position);

            }

        });
    }

    private void alertControllerOnBoardViewInteraction(Pane boardView, GameBoard board, ObjectProperty<CellClickEvent> cellClickProperty){
        ObservableList<Node> viewCells = boardView.getChildren();

        Dimension boundaries = board.getBoundaries();

        for(int y = 0; y < boundaries.height; y++){
            for(int x = 0; x < boundaries.width; x++){
                final Point position = new Point(x, y);
                viewCells.get((y*boundaries.width) + x).setOnMouseClicked(e -> {
                    System.out.println("CLICK: " + position.x + ", " + position.y);
                    cellClickProperty.set(new CellClickEvent(position));
                });

                Cell cell = (Cell) viewCells.get((y*boundaries.width) + x);

                //Mark cell with new CellMarker from the PawnDisplayModel
                PawnDisplayModel displayModel = board.getPawn(position).getDisplayModel();
                CellMarker marker = CellMarker.create(displayModel);
                cell.markCell(marker);

            }
        }

    }

    private void alwaysDisplayAvailableMoves(Pane boardView, ObjectProperty<ArrayList<CalculatedMove>> availableMoves, Dimension boardBoundaries){
        availableMoves.addListener(e -> {

            boardView.getChildren().stream().filter(node -> node instanceof Cell).forEach(node -> {
                Cell cell = (Cell) node;
                cell.removeHighlight();
            });

            double highestScore = 0;
            for(CalculatedMove move : availableMoves.get())
                if(move.getScore() > highestScore)
                    highestScore = move.getScore();

            for(CalculatedMove move : availableMoves.get()){
                Point position = new Point(move.getActions()[0].getX(), move.getActions()[0].getY());

                Node node = boardView.getChildren().get((position.y * boardBoundaries.width) + position.x);

                if(!(node instanceof Cell))
                    return;

                double highlightOpacity = (double)move.getScore() / highestScore;
                ((Cell)node).highlightCell(CellMarker.newHighlightMarker(highlightOpacity));
            }
        });
    }

    public void displayStats(GameMatch match){
        ScoreStatsController scoreStatsController = new ScoreStatsController();
        statsContainer.getChildren().add(scoreStatsController.getPane());

        double margin = 0.05;
        ViewDimensionBinder.bindWidthWithPadding(
                scoreStatsController.getPane(),
                statsContainer,
                ViewDimensionBinder.margin(margin, margin)
        );
/*
        ViewDimensionBinder.bindOneToOneDimension(
                statsContainer.minWidthProperty(),
                statsContainer.maxWidthProperty(),
                this.getContainer().widthProperty()
        );*/

        for(Player player : match.getPlayers()){
            scoreStatsController.addPlayerScoreRow(player);
            scoreStatsController.updateRowsOnMove(match.getBoardMoveMaker());
        }
    }

}

