package arena.tournament;



import arena.games.game.IGame;
import arena.games.gameInformation.GameInformation;
import arena.metaInformation.tournamentMetaInformation.ITournamentMetaInformation;
import arena.tournament.leaderboard.ILeaderboard;
import arena.tournament.leaderboard.Leaderboard;
import arena.tournament.match.IMatch;
import arena.tournament.match.Match;
import arena.tournament.tournamentConfiguration.ITournamentConfiguration;
import arena.users.IPlayer;
import arena.users.Player;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.*;

/**
 * Implements the Tournament interface.
 */
public class Tournament implements ITournament, Serializable {

    AppliedPlayersList appliedPlayers = new AppliedPlayersList();
    private ITournamentConfiguration configuration;
    private IGame game;
    private List<IMatch> matchesInTournament = new ArrayList<>();
    private ILeaderboard leaderboard = new Leaderboard(this);



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
        return matchesInTournament;
    }

    @Override
    public ITournament addMatchToTournament() {
        mockMatches();
        return this;
    }

    /**
     * Method for crating a mock tournament with matchmaking and a bunch of mock-users.
     */
    private void mockMatches(){
        Map<String, Color> players = new HashMap<>();
        players.put("August_cool", Color.TOMATO);
        players.put("Simon", Color.AZURE);
        players.put("Carlos", Color.PERU);
        players.put("Johan", Color.ALICEBLUE);
        players.put("Patric_42", Color.AQUA);
        players.put("Bjorn", Color.BEIGE);
        players.put("Robin", Color.CORAL);
        players.put("MössJohan", Color.FUCHSIA);
        players.put("Viktor", Color.GREEN);
        players.put("Nick", Color.BLACK);
        players.put("Röset", Color.ORANGE);

        players.entrySet()
                .forEach(entry ->
                        appliedPlayers.applyPlayerToList(
                                Player.create(entry.getKey())
                                    .setColor(entry.getValue())
                        ));

        for (int i = 1; i < appliedPlayers.length(); i+=2){
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
    public ILeaderboard getLeaderboard() {
        return leaderboard;
    }

    @Override
    public String toString(){
        return this.getTournamentMetaInformation().getName();
    }

}
