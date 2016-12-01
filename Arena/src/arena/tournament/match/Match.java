package arena.tournament.match;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import arena.tournament.match.result.IResult;
import arena.users.IPlayer;

import java.util.ArrayList;

/**
 * Created by Simon on 25/11/2016.
 */
public class Match implements IMatch {

    ArrayList<IPlayer> playersInMatch = new MatchMembers().getMatchMembersList();
    ObjectProperty<IResult> result = new SimpleObjectProperty<>();


    public Match(){
        setUpMatch();
    }

    private void getMatchmakingDataMock(){
        IPlayer player1, player2;

    }

    @Override
    public void setUpMatch() {
        getMatchmakingDataMock();

    }

    @Override
    public ObjectProperty<IResult> resultProperty() {
        return this.result;
    }


    @Override
    public void addPlayerToMatch(IPlayer player) {
        playersInMatch.add(player);
        System.out.println(player.getName() + " added to match");
    }
}
