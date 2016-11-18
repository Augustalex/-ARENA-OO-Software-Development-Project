package users;

import league.ILeague;
import league.League;
import tournament.ITournament;
import tournament.Tournament;
import tournament.TournamentFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by Simon on 14/11/2016.
 */
public class Player implements IPlayer{

    private ArrayList<ILeague> leagues = new ArrayList<>();
    //private List<ITournament> availibleTournaments;
    private String playerName;

    public Player(String name){
        this.playerName = name;

        ILeague league = new League("League1", 10);
        ITournament tour = TournamentFactory.newTournament();
        tour.setTournamentName("August Unleashed");


        ITournament tour2 = TournamentFactory.newTournament();
        tour2.setTournamentName("Simon Unleashed");


        ITournament tour3 = TournamentFactory.newTournament();
        tour3.setTournamentName("Carlos Unleashed");

        league.addTournamentToLeague(tour);
        league.addTournamentToLeague(tour2);
        league.addTournamentToLeague(tour3);
        leagues.add(league);
    }

    @Override
    public ArrayList<ILeague> getLeagues() {
        return leagues;
    }

    public IPlayer createMockPlayerAugust(){
        IPlayer player = new Player("August");
        return player;
    }

    @Override
    public List<ITournament> getAvailibleTournaments(){
        return leagues.stream()
                .flatMap(l -> l.getTournamentsInLeague().stream())
                .collect(Collectors.toList());

    }

    @Override
    public void notify(String message) {
        System.out.println("Notifiaction");
    }

}
