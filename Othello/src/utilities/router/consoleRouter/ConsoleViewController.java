package utilities.router.consoleRouter;

import boardGameLibrary.boardGame.match.GameMatch;
import boardGameLibrary.views.consoleViews.gameView.GameConsoleController;
import boardGameLibrary.views.consoleViews.mainMenu.MainConsoleController;
import boardGameLibrary.views.consoleViews.newGame.NewGameConsoleController;
import utilities.router.ViewController;

import java.io.PrintStream;
import java.util.Map;

/**
 * Created by August on 2016-10-20.
 */
public abstract class ConsoleViewController implements ViewController{

    protected final PrintStream output = System.out;

    public static ConsoleViewController create(String viewId, Map dependencies){
        if(dependencies == null)
            throw new IllegalArgumentException();

        if(dependencies.containsKey("GameMatch")) {
            if (viewId.equals("GameView"))
                return new GameConsoleController((GameMatch)dependencies.get("GameMatch"));
        }

        if(viewId.equals("NewGameView"))
            return new NewGameConsoleController();
        else if(viewId.equals("MainView"))
            return new MainConsoleController();

        throw new IllegalArgumentException("Cannot margin a view from incorrect view id and dependencies.");

    }
}
