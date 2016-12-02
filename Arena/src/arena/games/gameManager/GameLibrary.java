package arena.games.gameManager;

import arena.games.game.IGame;

/**
 * Stores and manage arena.games.
 */
public interface GameLibrary {

    void addGame(IGame game);

    IGame getGame(String gameName);
}
