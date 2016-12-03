package arena.tournament;



import arena.games.game.IGame;
import arena.games.gameInformation.GameInformation;
import arena.metaInformation.tournamentMetaInformation.ITournamentMetaInformation;
import arena.tournament.match.IMatch;
import arena.tournament.tournamentConfiguration.ITournamentConfiguration;
import arena.users.IPlayer;

import java.io.Serializable;
import java.util.List;

/**
 * Implements the Tournament interface.
 */
public class Tournament implements ITournament, Serializable {

    AppliedPlayersList appliedPlayers = new AppliedPlayersList();
    private ITournamentConfiguration configuration;
    private IGame game;

    public Tournament(ITournamentConfiguration configuration){
        this.configuration = configuration;
    }

    public Tournament(ITournamentConfiguration configuration, IGame game){
        this.configuration = configuration;
        setGame(game);
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

    @Override
    public ITournament setGame(IGame game) {
        this.game = game;
        return this;
    }

    @Override
    public GameInformation getGameInformation() {
        return game.getGameInformation();
    }

    @Override
    public String toString(){
        return this.getTournamentMetaInformation().getName();
    }

}
