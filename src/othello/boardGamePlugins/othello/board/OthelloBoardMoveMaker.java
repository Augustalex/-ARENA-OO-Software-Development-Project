package boardGamePlugins.othello.board;

import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.boardGame.board.Direction;
import boardGameLibrary.boardGame.move.CalculatedMove;
import boardGameLibrary.boardGame.move.Move;
import boardGameLibrary.boardGame.move.PlayerAction;
import boardGameLibrary.eventWrappers.BoardMoveEvent;
import boardGameLibrary.players.Player;
import boardGamePlugins.othello.pawn.OthelloPawn;
import boardGamePlugins.othello.board.exceptions.IllegalMoveException;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by August on 2016-10-02.
 */
public class OthelloBoardMoveMaker extends BoardMoveMaker {

    /**
     * Receives an instantiated {@link OthelloBoard} that is accessible from
     * a protected field inside this class.
     * @param board
     */
    public OthelloBoardMoveMaker(OthelloBoard board) {
        super(board);
    }

    private final Object key = new Object();

    /**
     * Checks if a move made by a player is legal.
     *
     * For all possible directions it passes down a straight path from the starting position.
     * If a player owned pawn is crossed beyond a distance of 1 cell, it is a legal move. Otherwise,
     * it is a illegal move.
     *
     * @param player {@link Player}
     * @param move {@link Move}
     * @return boolean
     */
    @Override
    public boolean isLegalMove(Player player, Move move) {
        if(!isLegalOthelloMoveContainer(move))
            throw new IllegalMoveException(move);

        //Require position of new pawn placement.
        Point startPosition = new Point(move.getActions()[0].getX(), move.getActions()[0].getY());

        //Check in all directions for any legal move.
        for(Direction direction : Direction.values())
            if(isLegalMoveInDirection(player, startPosition, direction))
                return true;

        return false;
    }

    /**
     * Check in a direction from a given direction and in perspective of a certain {@link Player}
     * whether arguments represent a legal move.
     *
     * @param player The Player performing the move.
     * @param position The origin position of the new pawn.
     * @param direction The heading direction of the current legal move search.
     * @return Whether arguments represents a legal move.
     */
    private boolean isLegalMoveInDirection(Player player, Point position, Direction direction){
        /*  If the move is on an already occupied cell, then it is not a legal move.
            If the move is outside the game board, it is not a legal move. */
        if(!this.board.withinBounds(position) || !this.board.isEmpty(position))
            return false;

        /*  The next player owned pawn need to separated from the new pawn by at least 1 cell.
            Thus a minimum distance of 2. */
        final int minLegalMoveDistance = 2;
        int distance = 0;

        /*  Traverses the board in specified direction as long as the current position at any given
            loop is not void of player ownership (pawn is empty). */
        do{
            position = movePoint(direction, position);
            distance++;

            //If the new position is out of bounds, then it is not a legal move.
            if(!this.board.withinBounds(position))
                break;

            /*  If a player owned pawned is encountered the move is legal depending on
                the current distance from the position of the new pawn. */
            if(this.board.getPawn(position).getOwner().equals(player))
                return distance >= minLegalMoveDistance;

        }while(!this.board.isEmpty(position));

        //If no legal move was found during the traversal loop, it is not a legal move.
        return false;
    }

    /**
     * Checks whether a move is a legal Othello {@link Move}. That is, that the move only contains
     * one single {@link PlayerAction}.
     *
     * @param move Move class containing player actions.
     * @return boolean
     */
    private boolean isLegalOthelloMoveContainer(Move move){
        return (move.getActions().length == 1);
    }

    /**
     * Returns an array of available {@link Move}s from which a player can make a decision on which move to apply.
     *
     * @return An array of {@link Move}s.
     * @param player The Player planning on making a move.
     */
    @Override
    public ArrayList<CalculatedMove> getAvailableMoves(Player player) {
        ArrayList<Move> legalMoves = new ArrayList<>();

        for(int y = 0; y < this.getGameBoard().getBoundaries().height; y++){
            for(int x = 0; x < this.getGameBoard().getBoundaries().width; x++){
                Move move = new Move(new PlayerAction[]{new PlayerAction(x, y)});
                if(isLegalMove(player, move))
                    legalMoves.add(move);
            }
        }

        return legalMoves.stream()
                .map(move -> new CalculatedMove(move.getActions(), this.getMoveScore(player, move)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Applies a {@link Move} to the board. Uses a recursive approach to find and flip pawns.
     * @param player the {@link Player} applying the move.
     * @param move the {@link Move} being applied.
     */
    @Override
    public void makeMove(Player player, Move move) {
        synchronized (this.key) {
            if(this.getAvailableMoves(player).size() == 0)
                this.BoardMoveEventProperty().set(BoardMoveEvent.noMoreMoves());
            else if (!isLegalMove(player, move))
                this.BoardMoveEventProperty().set(BoardMoveEvent.illegalMove());
            else {

                PlayerAction action = move.getActions()[0];
                Point startPosition = new Point(action.getX(), action.getY());

                this.board.setPawn(startPosition, new OthelloPawn(player));

                for (Direction direction : Direction.values()) {
                    findMove(player, movePoint(direction, startPosition), direction);
                }

                this.BoardMoveEventProperty().set(BoardMoveEvent.legalMove());
            }
        }
    }

    private void findMove(Player player, Point position, Direction direction){
        while(this.board.withinBounds(position) && !this.board.isEmpty(position)){
            if(this.board.getPawn(position).getOwner().equals(player))
                flipPawns(player, movePoint(direction.getOppositeDirection(), position), direction.getOppositeDirection());
            position = movePoint(direction, position);
        }
    }

    private void flipPawns(Player player, Point position, Direction direction){
        while(!this.board.getPawn(position).getOwner().equals(player)){
            this.board.setPawn(position, new OthelloPawn(player));

            /*for(Direction findDirection : Direction.values())
                if(!findDirection.equals(direction) || !findDirection.equals(direction.getOppositeDirection()))
                    findMove(player, movePoint(findDirection, position), findDirection);*/

            position = movePoint(direction, position);
        }
    }

    /**
     * Given a point (position of x and y) and a direction, it will return a new point
     * one unit further in the given direction.
     *
     * @param direction the current heading.
     * @param origin the start position.
     * @return a new point towards the new direction.
     */
    private Point movePoint(Direction direction, Point origin){
        int speed = 1;
        return new Point((int)Math.round(origin.x + speed * Math.cos(direction.getRadianAngle())),
                (int)Math.round(origin.y + speed * Math.sin(direction.getRadianAngle())));
    }

    /**
     * Set game starting positions. Requires all players in the game.
     *
     * @param players all Players in the current game.
     */
    @Override
    public void setStartPawns(Player[] players){
        int topX = (int)Math.floor(this.board.getBoundaries().width / 2) - players.length/2;
        int topY = (int)Math.floor(this.board.getBoundaries().height / 2) - players.length/2;

        int width = players.length;
        int height = players.length;

        int playerIndex = 0;

        for(int i = 0; i < width*height; i++){
            if(playerIndex == players.length)
                playerIndex = 0;

            int x = topX + ((i+1)%width);
            int y = topY + (int)Math.floor(i/width);

            if(y%2 == 0)
                this.board.setPawn(new Point(x, y), new OthelloPawn(players[players.length - playerIndex - 1]));
            else
                this.board.setPawn(new Point(x, y), new OthelloPawn(players[playerIndex]));

            playerIndex++;
        }
    }

    @Override
    public int getMoveScore(Player player, Move move) {
        PlayerAction action = move.getActions()[0];
        Point startPosition = new Point(action.getX(), action.getY());

        int score = 0;

        //Check in all directions
        for(Direction direction : Direction.values()){
            Point position = movePoint(direction, startPosition);
            while(this.board.withinBounds(position) && !this.board.isEmpty(position)){
                if(this.board.getPawn(position).getOwner().equals(player)){
                    score += getMoveScoreInDirection(startPosition, position, direction.getOppositeDirection());
                    break;
                }

                position = movePoint(direction, position);
            }
        }

        return score;
    }

    private int getMoveScoreInDirection(Point startPosition, Point playerOwnedPosition, Direction oppositeDirection){
        int score = 0;
        Point position = movePoint(oppositeDirection, playerOwnedPosition);

        while(this.board.withinBounds(position)){
            if(startPosition.getX() == position.getX() && startPosition.getY() == position.getY())
                return score;
            else if(this.board.isEmpty(position))
                break;

            score += 1;
            position = movePoint(oppositeDirection, position);
        }

        throw new IndexOutOfBoundsException("Cannot get Move Score from incorrect move. Recursion reached end of board.");
    }

    @Override
    public int numberOfPawnsOwned(Player player){
        Dimension boundaries = this.getGameBoard().getBoundaries();

        int numberOfPawns = 0;
        for(int y = 0; y < boundaries.height; y++)
            for(int x = 0; x < boundaries.width; x++)
                if(this.board.getPawn(new Point(x, y)).getOwner().equals(player))
                    numberOfPawns++;

        return numberOfPawns;
    }
}
