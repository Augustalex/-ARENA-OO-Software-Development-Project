package serviceCenter.reviewServices;

import communication.receiver.Receiver;
import tournament.tournamentConfiguration.ITournamentConfiguration;
import users.User;

/**
 * Contains ReviewObject which operators can collect and
 * approve/disapprove.
 */
public interface Reviewer<T> {

    /**
     * A service that reviews TournamentConfiguration objects.
     */
    static final Reviewer<ITournamentConfiguration> tournamentConfigurationReviewer = new OfflineTournamentConfigurationReviewer();

    void submitForReview(T configuration, User user);
}
