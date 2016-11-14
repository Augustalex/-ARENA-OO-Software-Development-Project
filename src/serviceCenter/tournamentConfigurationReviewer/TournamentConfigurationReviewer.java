package serviceCenter.tournamentConfigurationReviewer;

import tournament.tournamentConfiguration.ITournamentConfiguration;

/**
 * Created by Patric on 2016-11-14.
 */
public interface TournamentConfigurationReviewer extends Reviewer {

    void submitForReview(ITournamentConfiguration configuration);
}
