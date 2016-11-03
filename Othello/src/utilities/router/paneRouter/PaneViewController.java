package utilities.router.paneRouter;

import boardGameLibrary.boardGame.match.MatchSetup;
import boardGameLibrary.playerProfileStore.PlayerProfile;
import boardGameLibrary.playerProfileStore.PlayerProfileStore;
import boardGameLibrary.views.javaFxViews.gameView.GameViewController;
import boardGameLibrary.views.javaFxViews.mainMenu.MainViewController;
import boardGameLibrary.views.javaFxViews.newGame.NewGameViewController;
import boardGameLibrary.views.javaFxViews.settingsView.SettingsViewController;
import boardGameLibrary.views.javaFxViews.settingsView.profileSettings.AddProfileViewController;
import boardGameLibrary.views.javaFxViews.settingsView.profileSettings.EditPlayerViewController;
import javafx.scene.layout.Pane;
import utilities.router.ViewController;

import java.util.Map;

/**
 * Created by August on 2016-10-19.
 */
public abstract class PaneViewController implements ViewController {

    private Pane container = null;

    public static PaneViewController create(Pane container, String viewId, Map dependencies){
        if(dependencies == null)
            throw new IllegalArgumentException();

        if(dependencies.containsKey("MatchSetup")) {
            if (viewId.equals("GameView"))
                return new GameViewController(container, (MatchSetup) dependencies.get("MatchSetup"));
        }
        else if(dependencies.containsKey("PlayerProfileStore")){
            if(viewId.equals("MainView"))
                return new MainViewController(container, (PlayerProfileStore) dependencies.get("PlayerProfileStore"));
            else if(viewId.equals("SettingsView"))
                return new SettingsViewController(container, (PlayerProfileStore) dependencies.get("PlayerProfileStore"));
            else if(viewId.equals("ProfileSettingsView"))
                return new AddProfileViewController(container, (PlayerProfileStore) dependencies.get("PlayerProfileStore"));
            else if(viewId.equals("NewGameView"))
                return new NewGameViewController(container, (PlayerProfileStore) dependencies.get("PlayerProfileStore"));
        }
        else if(dependencies.containsKey("PlayerProfile")){
            if(viewId.equals("ProfileSettingsView"))
                return new EditPlayerViewController(container, (PlayerProfile) dependencies.get("PlayerProfile"));
        }

        if(viewId.equals("MainView"))
            return new MainViewController(container);

        throw new IllegalArgumentException("Cannot margin a view from incorrect view id and dependencies.");
    }

    public void setContainer(Pane container){
        this.container = container;
    }

    protected Pane getContainer(){
        return this.container;
    }
}
