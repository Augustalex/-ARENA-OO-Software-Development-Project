package arena.reviewServices;

import arena.tournament.ITournament;
import arena.tournament.Tournament;
import arena.tournament.TournamentFactory;
import arena.tournament.tournamentConfiguration.ITournamentConfiguration;
import arena.users.IUser;
import arena.users.LeagueOwner;

/**
 * Reviews all incoming retrieve objects and approves them, offline.
 */
public class OfflineTournamentConfigurationReviewer implements Reviewer<ITournamentConfiguration> {

    private ReviewQueue<ITournamentConfiguration> reviewQueue = new ReviewQueue<>();

    @Override
    public void submitForReview(ITournamentConfiguration configuration, IUser user) {
        this.reviewQueue.submit(configuration, user);
        ReviewObject reviewObject = this.reviewQueue.retrieve();
        System.out.println("APPROVED: " + reviewObject);
        ((LeagueOwner)user).getOwnedLeague().addTournamentToLeague(TournamentFactory.newTournament(configuration));
    }

}
