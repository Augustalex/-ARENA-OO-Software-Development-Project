/*
 * OOMU - laboration 2: Othello
 * Johan - s152754, Patric - s153364 och Simon - s152428
 * Grupp 05
 */
package othello.view;

//import javafx.scene.control.TopMenu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import othello.view.dialogs.HelpDialog;

/**
 * Class creates and holds a menubar which can be returned to GUI.
 * @author Patric
 */
public class TopMenu {
    /**
     * attribute holds the menubar created
     */
    private MenuBar menubar;
    
    /**
     * constructor creating the menu with helpermethod
     */
    public TopMenu(){
        menubar = new MenuBar();
        setMenuLayout();
    }
    
    /**
     * @return the menubar to be used in GUI
     */
    public MenuBar getMenu(){
        return menubar;
    }
    
    /**
     * helpermethod to create and set menu layout
     */
    private void setMenuLayout(){
        Menu fileMenu = new Menu("File");
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(actionEvent -> System.exit(0));
        fileMenu.getItems().add(exit);
        
        Menu helpMenu = new Menu("Help");
        MenuItem howToPlay = new MenuItem("How to play");
        howToPlay.setOnAction(actionEvent ->
               new HelpDialog().newHelpDialog()
               );
        helpMenu.getItems().add(howToPlay);
        
        menubar.getMenus().addAll(fileMenu, helpMenu);
    }
}
