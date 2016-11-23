package games.gameManager;

/**
 * Installs a game given a path to a package containing a game.
 */
public interface GameInstaller {

    void installGame(String gamePackagePath);
}
