package arena.tournament.tournamentConfiguration;

import arena.metaInformation.tournamentMetaInformation.ITournamentMetaInformation;
import arena.reviewServices.Reviewer;
import arena.tournament.tournamentStyle.ITournamentStyle;
import arena.users.IUser;

/**
 * Implements the Tournament Configuration Interface.
 */
public class TournamentConfiguration implements ITournamentConfiguration {
    private ITournamentStyle tournamentStyle;
    private ITournamentMetaInformation tournamentMetaInformation;

    @Override
    public ITournamentConfiguration setTournamentStyle(ITournamentStyle ITournamentStyle) {
        this.tournamentStyle = ITournamentStyle;
        return this;
    }

    @Override
    public ITournamentConfiguration setMetaInformation(ITournamentMetaInformation tournamentMetaInformation) {
        this.tournamentMetaInformation = tournamentMetaInformation;
        return this;
    }

    public ITournamentStyle getITournamentStyle() {
        return this.tournamentStyle;
    }

    @Override
    public ITournamentMetaInformation getMetaInformation() {
        return tournamentMetaInformation;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public void submit() {
        Reviewer.tournamentConfigurationReviewer
                .submitForReview(this, IUser.newMockUser());
    }

    @Override
    public String toString(){
        return "Name: " + tournamentMetaInformation.getName() + " Description: " + tournamentMetaInformation.getDescription() + " Start time: " + this.tournamentMetaInformation.getStartDate() + " Style: " + this.tournamentStyle;
    }
}
