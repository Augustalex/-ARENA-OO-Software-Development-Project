package serviceCenter;

import serviceCenter.reviewServices.TournamentConfigurationReviewer;
import tournament.tournamentConfiguration.ITournamentConfiguration;

/**
 * Interface for global services. Implementations will mostly use some kind
 * of an online server for distributing messages (commands) to different services.
 * As well as managing the returned information from the services.
 *
 * The preferred way of managing
 */
public interface ServiceCenter {

    void submitTournamentConfiguration(ITournamentConfiguration tournamentConfiguration);

    TournamentConfigurationReviewer getTournamentConfigurationReviewer();

}
