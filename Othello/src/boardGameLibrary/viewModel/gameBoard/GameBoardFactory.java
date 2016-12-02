/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardGameLibrary.viewModel.gameBoard;


import boardGameLibrary.viewModel.gameBoard.cell.Cell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


/**
 *
 * @author S132063
 */
public class GameBoardFactory {

    public static GridPane createBoard(Pane container, int size){
        GridPane pane = new GridPane();
        pane.setId("gameBoard");

        pane.minWidthProperty().bind(container.widthProperty());
        pane.maxWidthProperty().bind(container.widthProperty());

        pane.minHeightProperty().bind(pane.widthProperty());
        pane.maxHeightProperty().bind(pane.widthProperty());

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++){
                Cell cell = new Cell();

                cell.minWidthProperty().bind(pane.widthProperty().divide(size));
                cell.maxWidthProperty().bind(pane.widthProperty().divide(size));
                cell.minHeightProperty().bind(pane.widthProperty().divide(size));
                cell.maxHeightProperty().bind(pane.widthProperty().divide(size));
                pane.add(cell, j, i);

                if((i%2 == 0 && j%2 == 1) || (i%2==1 && j%2 == 0))
                    cell.setStyle("-fx-background-color:forestgreen");
                else
                    cell.setStyle("-fx-background-color: darkgreen");
            }

        return pane;
    }
    
}
