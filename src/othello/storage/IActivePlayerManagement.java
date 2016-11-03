package storage;

import boardGameLibrary.players.Player;

/**
 * Created by August on 2016-10-30.
 */
public interface IActivePlayerManagement {

    void addActivePlayer(Player player);

    void removeActivePlayer(Player player);

    Player[] getActivePlayers();
}
