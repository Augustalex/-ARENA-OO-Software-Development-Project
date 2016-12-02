package arena.league;

import arena.games.game.GameFactory;
import arena.games.game.IGame;
import arena.tournament.ITournament;
import arena.tournament.TournamentFactory;
import arena.users.IPlayer;

import java.util.List;

/**
 * Interface for League
 */
public interface ILeague {

    public static ILeague createMockLeague(String name){
        ILeague league = new League(name, -1);
        league.setGame(GameFactory.newMockOthelloGame());

        league.addTournamentToLeague(TournamentFactory.newTournamentMock("Coca Cola Tournament"));
        league.addTournamentToLeague(TournamentFactory.newTournamentMock("HB tournament"));
        league.addTournamentToLeague(TournamentFactory.newTournamentMock("Ostbågar Tournament"));
        league.addTournamentToLeague(TournamentFactory.newTournamentMock("Redbull Sugarfree"));

        return league;
    }

    String getLeagueName();

    int getLeagueID();

    /**
     * Method for getting a list of the tournaments in
     * the current arena.league.
     * @return List<ITournament> tournaments
     */
    List<ITournament> getTournaments();

    /**
     * Method for adding a arena.tournament to the current
     * arena.league.
     * @param tournament
     */
    void addTournamentToLeague(ITournament tournament);

    void addPlayerToLeague(IPlayer player);

    List<IPlayer> getPlayersInLeague();

    void printTournamentIDs();

    /**
     * Method for pritning out the names of the tournaments
     * in the arena.league.
     */
    void printTournamentNames();

    void setGame(IGame game);
    IGame getGame();
}
