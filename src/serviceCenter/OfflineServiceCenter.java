package serviceCenter;

import serviceCenter.reviewServices.TournamentConfigurationReviewer;
import tournament.tournamentConfiguration.ITournamentConfiguration;

/**
 * Offline implementation of ServiceCenter. Will offer all Services as
 * mock versions that works without a database and internet connection.
 */
public class OfflineServiceCenter extends ServiceCenter {

    @Override
    public TournamentConfigurationReviewer getTournamentConfigurationReviewer() {
        return null;
    }
}
