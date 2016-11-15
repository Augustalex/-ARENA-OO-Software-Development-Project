package serviceCenter.reviewServices;

import tournament.tournamentConfiguration.ITournamentConfiguration;
import users.User;

/**
 * Contains ReviewObject which operators can collect and
 * approve/disapprove.
 */
public interface Reviewer {

    void submitForReview(ITournamentConfiguration configuration, User user);
}
