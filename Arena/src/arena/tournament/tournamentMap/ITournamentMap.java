package arena.tournament.tournamentMap;

import arena.users.IPlayer;
import arena.tournament.match.MatchMembers;

import java.util.ArrayList;

/**
 * The arena.tournament map representing the set and order
 * of matches to be played during a arena.tournament.
 */
public interface ITournamentMap {
    void setPlayerList(ArrayList<IPlayer> joinedPlayers);
    ArrayList<IPlayer> getPlayerList();

    void createMap(ArrayList<IPlayer> playerList);
    ArrayList<MatchMembers> getMap();
}
