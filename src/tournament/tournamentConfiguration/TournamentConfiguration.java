package tournament.tournamentConfiguration;

import metaInformation.TournamentMetaInformation;
import tournament.tournamentStyle.TournamentStyle;

/**
 * Implements the Tournament Configuration Interface.
 */
public class TournamentConfiguration implements ITournamentConfiguration {
    private TournamentStyle tournamentStyle;
    private TournamentMetaInformation tournamentMetaInformation;

    @Override
    public ITournamentConfiguration setTournamentStyle(TournamentStyle tournamentStyle) {
        this.tournamentStyle = tournamentStyle;
        return this;
    }

    @Override
    public ITournamentConfiguration setMetaInformation(TournamentMetaInformation tournamentMetaInformation) {
        this.tournamentMetaInformation = tournamentMetaInformation;
        return this;
    }

    @Override
    public TournamentStyle getTournamentStyle() {
        return tournamentStyle;
    }

    @Override
    public TournamentMetaInformation getMetaInformation() {
        return tournamentMetaInformation;
    }

    @Override
    public String toString(){
        return "Name: " + tournamentMetaInformation.getName() + " Description: " + tournamentMetaInformation.getDescription() + " Start time: " + this.tournamentMetaInformation.getStartDate() + " Style: " + tournamentStyle;
    }
}
