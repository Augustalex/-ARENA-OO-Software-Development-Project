package arena.games.gameManager;

import arena.games.game.GameFactory;
import arena.games.game.IGame;

import java.util.HashMap;
import java.util.Map;

/**
 * Acts as both the IGame Library and the IGame Installer.
 *
 */
public class ArenaGameManager implements GameLibrary, GameInstaller{

    private Map<String, IGame> games = new HashMap<>();
    private GameFactory gameFactory = new GameFactory();

    @Override
    public void addGame(IGame game) {
        this.games.put(game.getGameInformation().getGameName(), game);
    }

    @Override
    public void installGame(String gamePackagePath) {
        IGame game = this.gameFactory.newGame(gamePackagePath);
        this.addGame(game);
    }
}
