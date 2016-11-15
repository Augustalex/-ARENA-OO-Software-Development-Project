package serviceCenter;

import serviceCenter.reviewServices.OfflineTournamentConfigurationReviewer;
import serviceCenter.reviewServices.Reviewer;

/**
 * Offline implementation of ServiceCenter. Will offer all Services as
 * mock versions that works without a database and internet connection.
 */
public class OfflineServiceCenter extends ServiceCenter {

    @Override
    public Reviewer getTournamentConfigurationReviewer() {
        return new OfflineTournamentConfigurationReviewer();
    }
}
