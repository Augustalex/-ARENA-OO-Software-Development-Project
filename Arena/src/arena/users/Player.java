package arena.users;

import arena.games.game.Game;
import arena.games.game.IGame;
import arena.games.gameInformation.OthelloGameInformation;
import arena.league.ILeague;
import arena.league.League;
import arena.tournament.ITournament;
import arena.tournament.TournamentFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implements interface IPlayer
 */
public class Player implements IPlayer{

    private ArrayList<ILeague> leagues = new ArrayList<>();
    private String playerName;

    private Player() {
    }

    public static IPlayer newMockPlayer(){

        IGame mockGame = new Game();
        mockGame.setGameInformation(new OthelloGameInformation());
        IPlayer player = new Player().createMockPlayerAugust();
        ILeague league = new League("mock arena.league", 1);
        league.setGame(mockGame);

        league.addTournamentToLeague(TournamentFactory.newTournamentMock("Coca Cola Tournament"));
        league.addTournamentToLeague(TournamentFactory.newTournamentMock("HB tournament"));
        league.addTournamentToLeague(TournamentFactory.newTournamentMock("Ostbågar Tournament"));
        league.addTournamentToLeague(TournamentFactory.newTournamentMock("Redbull Sugarfree"));
        player.getLeagues().add(league);
        return player;
    }

    @Override
    public ArrayList<ILeague> getLeagues() {
        return leagues;
    }

    public IPlayer createMockPlayerAugust(){
        IPlayer player = new Player();
        player.setName("August");
        return player;
    }


    @Override
    public List<ITournament> getAvailibleTournaments(){
        return leagues.stream()
                .flatMap(l -> l.getTournaments().stream())
                .collect(Collectors.toList());
    }

    @Override
    public void notify(String message) {
        System.out.println("Notifiaction");
    }

    @Override
    public String getName() {
        return playerName;
    }

    @Override
    public int getId() {
        return -1;
    }

    @Override
    public void setName(String name){
        this.playerName = name;
    }


}
