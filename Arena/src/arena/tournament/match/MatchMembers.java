package arena.tournament.match;

import arena.users.IPlayer;

import java.util.ArrayList;

/**
 * Wrapper for IPlayer members of a Match.
 */
public class MatchMembers {
    ArrayList<IPlayer> playersInMatch = new ArrayList<>();

    public MatchMembers(){

    }

    ArrayList<IPlayer> getMatchMembersList(){
        return playersInMatch;
    }

}
