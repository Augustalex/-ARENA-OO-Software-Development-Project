package tournament.tournamentStyle;

import java.util.ArrayList;
import java.util.Random;

/**
 * Pre defined Tournament Style representing the Tournament Style
 * of a Tree Tournament.
 */
public class TreeTournamentStyle implements ITournamentStyle {
    private final int tournamentSize = 64;
    private final boolean groupRound = false;
    private final boolean eliminationRound = true;
    //private GroupSettings groupSettings;
    @Override
    public void setGroupRound(boolean value) {
        // this.leagueRound = value;
    }

    @Override
    public boolean getGroupRound() {
        return this.groupRound;
    }

    @Override
    public void setEliminationRound(boolean value) {
        // this.eliminationRound = value;
    }

    @Override
    public boolean getEliminationRound() {
        return this.eliminationRound;
    }
    public int getTournamentSize(){
        return this.tournamentSize;
    }
    public void setTournamentSize(int value){
        // this.tournamentSize = value;
    }
    @Override
    public ITournamentStyle getTournamentStyle() {
        return this;
    }

    /*@Override
    public void setTournamentStyle(ITournamentStyle tournamentStyle) {

    }*/

    @Override
    public void createMapPreference() {

    }
    public void createGroupMap(){

    }

}
