package arena.games.gameManager;

import arena.games.game.GameFactory;
import arena.games.game.IGame;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Acts as both the IGame Library and the IGame Installer.
 *
 */
public class ArenaGameManager implements GameLibrary, GameInstaller{

    private static class ArenaGameManagerHolder{
        public static final ArenaGameManager instance = ((Supplier<ArenaGameManager>)() -> {
            ArenaGameManager instance = new ArenaGameManager();
            instance.addGame(GameFactory.newMockOthelloGame());
            instance.addGame(GameFactory.newMockTicTacToe());
            return instance;
        }).get();
    }

    private Map<String, IGame> games = new HashMap<>();
    private GameFactory gameFactory = new GameFactory();

    public static ArenaGameManager get(){
        return ArenaGameManagerHolder.instance;
    }

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
