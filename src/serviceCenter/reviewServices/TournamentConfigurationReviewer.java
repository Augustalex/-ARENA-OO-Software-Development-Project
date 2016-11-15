package serviceCenter.reviewServices;

import tournament.tournamentConfiguration.ITournamentConfiguration;
import users.User;

/**
 * Review Service for a TournamentConfiguration Object.
 *
 * The object will be sent to a queue for a reviewer to either approve or
 * disapprove the configuration of the object.
 *
 * If the object is not approved, the creator will be notified.
 */
public interface TournamentConfigurationReviewer {

    void submitForReview(ITournamentConfiguration configuration, User user);
}
