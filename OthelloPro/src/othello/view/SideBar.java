/*
 * OOMU - laboration 2: Othello
 * Johan - s152754, Patric - s153364 och Simon - s152428
 * Grupp 05
 */
package othello.view;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import othello.controller.GameManager;
import othello.view.dialogs.HelpDialog;


/**
 * Class creates a sidebar with buttons for starting a new game, getting help, 
 * and exiting the program. 
 * @author Johan
 */
public class SideBar{
    /**
     * attributes holds the sidebar to be returned to GUI
     */
    private VBox sideBar;
    
    /**
     * constructor creates and sets the layout of the sidebar
     */
    public SideBar(){
        sideBar = new VBox(30);
        sideBar.setFocusTraversable(false);
        setSideBarLayout();
    }
    
    /**
     * @return the sidebar to calling GUI
     */
    public Pane getPane(){
        return sideBar;
    }
    
    /**
     * Method sets the layout of the sidebar and the actionevents associated
     * with the buttons contained in it.
     */
    private void setSideBarLayout(){ 
        sideBar.setStyle("-fx-border-color: black;");
        sideBar.setPadding(new Insets(20, 20, 20, 20));
        Button btNew = new Button("New Game");
        Button btHelp = new Button("Help");
        Button btQuit = new Button("Quit");
        btNew.setFocusTraversable(false);
        btHelp.setFocusTraversable(false);
        btQuit.setFocusTraversable(false);
        sideBar.getChildren().addAll(btNew, btHelp, btQuit);
        
        btNew.setOnAction((ActionEvent event) -> {
            new GameManager();
            System.out.println("New game pressed");
        });
        
        btHelp.setOnAction((ActionEvent event) -> {
            System.out.println("Help pressed");
            new HelpDialog().newHelpDialog();
        });
        
        btQuit.setOnAction((ActionEvent event) -> {
            System.out.println("You chose to quit, bye!");
            System.exit(0);
        });       
    }
}
