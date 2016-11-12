package tournament.tournamentMap;

import users.Player;
import tournament.match.MatchMembers;

import java.util.ArrayList;

/**
 * The tournament map representing the set and order
 * of matches to be played during a tournament.
 */
public interface ITournamentMap {
    void setPlayerList(ArrayList<Player> joinedPlayers);
    ArrayList<Player> getPlayerList();

    void createMap(ArrayList<Player> playerList);
    ArrayList<MatchMembers> getMap();
}
