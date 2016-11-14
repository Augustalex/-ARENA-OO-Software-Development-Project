package tournament.tournamentMap;

import users.IPlayer;
import tournament.match.MatchMembers;

import java.util.ArrayList;

/**
 * The tournament map representing the set and order
 * of matches to be played during a tournament.
 */
public interface ITournamentMap {
    void setPlayerList(ArrayList<IPlayer> joinedPlayers);
    ArrayList<IPlayer> getPlayerList();

    void createMap(ArrayList<IPlayer> playerList);
    ArrayList<MatchMembers> getMap();
}
