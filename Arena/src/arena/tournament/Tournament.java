package arena.tournament;



import arena.games.game.IGame;
import arena.games.gameInformation.GameInformation;
import arena.metaInformation.tournamentMetaInformation.ITournamentMetaInformation;
import arena.tournament.match.IMatch;
import arena.tournament.match.Match;
import arena.tournament.tournamentConfiguration.ITournamentConfiguration;
import arena.users.IPlayer;
import arena.users.IUser;
import arena.users.Player;
import arena.users.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Implements the Tournament interface.
 */
public class Tournament implements ITournament, Serializable {

    AppliedPlayersList appliedPlayers = new AppliedPlayersList();
    private ITournamentConfiguration configuration;
    private IGame game;
    private List<IMatch> matchesInTournament = new ArrayList<>();


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
        mockMatches();
        //throw new UnsupportedOperationException();
        return matchesInTournament;
    }

    private void mockMatches(){
        String[] players = new String[]{
                "August",
                "Simon",
                "Carlos",
                "Johan",
                "Patric",
                "Bjorn",
                "Robin",
                "MössJohan",
                "Viktor",
                "Nick",
                "Röset"
        };

        Arrays.stream(players)
                .forEach(name ->
                        appliedPlayers.applyPlayerToList(Player.newMockPlayer(name)));

        for (int i = 1; i < appliedPlayers.lenght(); i+=2){
            IMatch match = new Match();
            match.setUpMatch(appliedPlayers.getPlayerFromList(i - 1), appliedPlayers.getPlayerFromList(i));
            matchesInTournament.add(match);
        }

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
