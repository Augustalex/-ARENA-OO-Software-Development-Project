package serviceCenter.reviewServices;

import tournament.tournamentConfiguration.ITournamentConfiguration;
import users.IUser;

/**
 * Reviews all incoming retrieve objects and approves them, offline.
 */
public class OfflineTournamentConfigurationReviewer implements Reviewer<ITournamentConfiguration> {

    private ReviewQueue<ITournamentConfiguration> reviewQueue = new ReviewQueue<>();

    @Override
    public void submitForReview(ITournamentConfiguration configuration, IUser IUser) {
        this.reviewQueue.submit(configuration, IUser);
        ReviewObject reviewObject = this.reviewQueue.retrieve();
        System.out.println("APPROVED: " + reviewObject);
    }
}
