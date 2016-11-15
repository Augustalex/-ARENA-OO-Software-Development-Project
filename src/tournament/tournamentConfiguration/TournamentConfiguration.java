package tournament.tournamentConfiguration;

import metaInformation.TournamentMetaInformation;
import serviceCenter.ServiceCenter;
import tournament.tournamentStyle.ITournamentStyle;
import users.User;

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
        return true;
    }

    @Override
    public void submit() {
        if(ServiceCenter.isSet())
            ServiceCenter.getServiceCenter()
                    .getTournamentConfigurationReviewer()
                    .submitForReview(this, message -> System.out.println("NOTIFY!"));
        else
            System.out.println("ServiceCenter not set!");
    }

    @Override
    public String toString(){
        return "Name: " + tournamentMetaInformation.getName() + " Description: " + tournamentMetaInformation.getDescription() + " Start time: " + this.tournamentMetaInformation.getStartDate() + " Style: " + this.tournamentStyle;
    }
}
