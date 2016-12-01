package arena.reviewServices;

import arena.tournament.tournamentConfiguration.ITournamentConfiguration;
import arena.users.IUser;

/**
 * Contains ReviewObject which operators can collect and
 * approve/disapprove.
 */
public interface Reviewer<T> {

    /**
     * A service that reviews TournamentConfiguration objects.
     */
    static final Reviewer<ITournamentConfiguration> tournamentConfigurationReviewer = new OfflineTournamentConfigurationReviewer();

    void submitForReview(T configuration, IUser IUser);
}
