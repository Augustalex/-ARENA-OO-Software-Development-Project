package serviceCenter.reviewerServices;

import tournament.tournamentConfiguration.ITournamentConfiguration;
import tournament.tournamentConfiguration.TournamentConfiguration;
import users.User;

/**
 * Reviews all incoming retrieve objects and approves them, offline.
 */
public class OfflineTournamentConfigurationReviewer implements TournamentConfigurationReviewer {

    ReviewQueue<ITournamentConfiguration> reviewQueue = new ReviewQueue<>();

    @Override
    public void submitForReview(ITournamentConfiguration configuration, User user) {
        this.reviewQueue.submit(configuration, user);
        this.reviewQueue.retrieve();
    }
}
