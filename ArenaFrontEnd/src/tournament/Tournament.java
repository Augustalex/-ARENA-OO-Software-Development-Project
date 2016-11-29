package tournament;



import metaInformation.tournamentMetaInformation.ITournamentMetaInformation;
import tournament.match.IMatch;
import tournament.tournamentConfiguration.ITournamentConfiguration;
import users.IPlayer;

import java.io.Serializable;
import java.util.List;

/**
 * Implements the Tournament interface.
 */
public class Tournament implements ITournament, Serializable {

    AppliedPlayersList appliedPlayers = new AppliedPlayersList();
    private ITournamentConfiguration configuration;

    public Tournament(ITournamentConfiguration configuration){
        this.configuration = configuration;
    }


    @Override
    public ITournamentMetaInformation getTournamentMetaInformation() {
        return (ITournamentMetaInformation) this.configuration.getMetaInformation();
    }

    @Override
    public void applyPlayer(IPlayer player) {
        appliedPlayers.applyPlayerToList(player);
    }

    @Override
    public AppliedPlayersList getAppliedPlayerList() {
        return appliedPlayers;
    }

    @Override
    public List<IMatch> getMatches() {
        throw new UnsupportedOperationException();
    }

}
