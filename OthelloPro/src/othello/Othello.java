/*
 * OOMU - laboration 2: Othello
 * Johan - s152754, Patric - s153364 och Simon - s152428
 * Grupp 05
 */
package othello;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import othello.view.GameFrame;

/**
 * This application creates a GUI-apllication for playing the game Othello.
 * A singleton object returns the GUI-object for the scene to the put in the 
 * primarystage
 * 
 * @author Patric
 */
public class Othello extends Application {
    /**
     * The start application starts the game.
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(GameFrame.getGameFrame().returnGameFrame(), 700, 600);

        primaryStage.setTitle("Othello");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
