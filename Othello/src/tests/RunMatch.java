package tests;

import boardGameLibrary.boardGame.match.MatchSetup;
import boardGameLibrary.players.LocalPlayer;
import boardGameLibrary.players.Player;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import utilities.router.paneRouter.PaneRouter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by August on 2016-11-02.
 */
public class RunMatch {

    public static void runMatch(Pane container){
        PaneRouter router = new PaneRouter(container);
        router.setApplicationRouter(router);

        Player player1 = new LocalPlayer("August", Color.WHITE);
        Player player2 = new LocalPlayer("Johan", Color.BLACK);

        String gameType = "Othello";
        boolean isOnline = false;

        MatchSetup setup = new MatchSetup(gameType, new Player[]{player1, player2});
        setup.setIsOnline(false);


        Map<String, Object> map = new HashMap<>();
        map.put("MatchSetup", setup);
        router.route("GameView", map);
    }
}
