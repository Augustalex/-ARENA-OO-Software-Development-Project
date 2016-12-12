package tests;

import boardGameLibrary.boardGame.match.MatchSetup;
import boardGameLibrary.players.LocalPlayer;
import boardGameLibrary.players.Player;
import boardGameLibrary.players.RemotePlayer;
import hostProviderService.Host;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import utilities.router.Router;
import utilities.router.paneRouter.PaneRouter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public static void runOnlineMatchTest(Pane container, Map<Host, LocalPlayer> players, LocalPlayer localPlayer){

        Router router = new PaneRouter(container);

        //Remote listening information
        int gamePort = 3000;

        setupGame(
            router,
            newMatchSetup(players, localPlayer, gamePort)
        );
    }

    public static Player newRemotePlayer(Player player, int port){
        return new RemotePlayer(
                player.getName(),
                player.getColor()
        ).setPort(port);
    }

    public static Player newLocalOnlinePlayer(LocalPlayer player, List<Host> hosts, Host localHost){
        List<Host> finalHosts = new ArrayList<>();
        finalHosts.addAll(hosts);
        finalHosts.remove(localHost);
        return player.setToOnlinePlayer(finalHosts.stream().toArray(Host[]::new));
    }

    public static MatchSetup newMatchSetup(Map<Host, LocalPlayer> players, Player localPlayer, int gamePort){
        return new MatchSetup(
                "Othello",
                players.entrySet().stream()
                    .map(entry -> {
                        if(entry.getValue().equals(localPlayer))
                            return newLocalOnlinePlayer(
                                    entry.getValue(),
                                    players.keySet().stream().collect(Collectors.toList()),
                                    entry.getKey()
                            );
                        else
                            return newRemotePlayer(entry.getValue(), gamePort);
                    })
                    .toArray(Player[]::new)
        );
    }

    public static void setupGame(Router router, MatchSetup setup){
        Map<String, Object> map = new HashMap<>();
        map.put("MatchSetup", setup);
        router.route("GameView", map);
    }


}
