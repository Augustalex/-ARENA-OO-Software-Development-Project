package serviceCenter.reviewServices;

import tournament.tournamentConfiguration.ITournamentConfiguration;
import users.User;

/**
 * Reviews all incoming retrieve objects and approves them, offline.
 */
public class OfflineTournamentConfigurationReviewer implements Reviewer {

    private ReviewQueue<ITournamentConfiguration> reviewQueue = new ReviewQueue<>();

    @Override
    public void submitForReview(ITournamentConfiguration configuration, User user) {
        this.reviewQueue.submit(configuration, user);
        ReviewObject reviewObject = this.reviewQueue.retrieve();
        System.out.println("APPROVED: " + reviewObject);
    }
}
