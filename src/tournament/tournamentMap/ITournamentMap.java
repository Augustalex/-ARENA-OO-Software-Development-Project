package tournament.tournamentMap;

import users.Player;
import tournament.match.MatchMembers;

import java.util.ArrayList;

/**
 * Created by Johan on 2016-11-11.
 */
public interface ITournamentMap {
    void setPlayerList(ArrayList<Player> joinedPlayers);
    ArrayList<Player> getPlayerList();

    void CreateMap(ArrayList<Player> playerList);
    ArrayList<MatchMembers> getMap();
}
