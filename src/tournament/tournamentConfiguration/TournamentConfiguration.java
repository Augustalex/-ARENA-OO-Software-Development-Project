package tournament.tournamentConfiguration;

import metaInformation.TournamentMetaInformation;
import tournament.tournamentStyle.ITournamentStyle;

/**
 * Implements the Tournament Configuration Interface.
 */
public class TournamentConfiguration implements ITournamentConfiguration {
    private ITournamentStyle tournamentStyle;
    private TournamentMetaInformation tournamentMetaInformation;

    @Override
    public ITournamentConfiguration setTournamentStyle(ITournamentStyle ITournamentStyle) {
        this.tournamentStyle = ITournamentStyle;
        return this;
    }

    @Override
    public ITournamentConfiguration setMetaInformation(TournamentMetaInformation tournamentMetaInformation) {
        this.tournamentMetaInformation = tournamentMetaInformation;
        return this;
    }

    public ITournamentStyle getITournamentStyle() {
        return this.tournamentStyle;
    }

    @Override
    public TournamentMetaInformation getMetaInformation() {
        return tournamentMetaInformation;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public void submit() {

    }

    @Override
    public String toString(){
        return "Name: " + tournamentMetaInformation.getName() + " Description: " + tournamentMetaInformation.getDescription() + " Start time: " + this.tournamentMetaInformation.getStartDate() + " Style: " + this.tournamentStyle;
    }
}
