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

    ArrayList<ILeague> leagues = new ArrayList<>();
    List<ITournament> availibleTournaments;
    ArrayList<ITournament> joinedTournaments = new ArrayList<>();
    String playerName;
    TournamentFactory tFact = new TournamentFactory();
    IPlayer player;

    public Player(String name){
        this.playerName = name;
    }

    @Override
    public ArrayList<ILeague> getLeagues() {
        //temporary
        ILeague league = new League("League1", 10);
        ITournament tour = tFact.newTournament();
        createMockPlayerAugust();
        league.addTournamentToLeague(tour);
        leagues.add(league);
        //temporary

        return leagues;
    }

    public IPlayer createMockPlayerAugust(){
        player = new Player("August");
        return player;
    }

    /*@Override
    public void bindTournamentToPlayer(ITournament tournament, IPlayer player){
        tournament.AddPlayer(player);
        player.addTournament(tournament);
    }
    */

    @Override
    public List<ITournament> getAvailibleTournaments(ArrayList<ILeague> leagues){
        return leagues.stream()
                .flatMap(l -> l.getTournamentsInLeague().stream())
                .collect(Collectors.toList());

    }


   /* @Override
    public void addTournament(ITournament tour) {

        joinedTournaments.add(tour);

    }
    */


    //.--------------------------------------------------------------------------------------.\\
    public void tournamentHandler(){

        availibleTournaments = getAvailibleTournaments(getLeagues());
        for(int i = 0; i < leagues.size(); i++){
            leagues.get(i).printTournamentIDs();
        }
        Scanner in = new Scanner(System.in);
        int tourID = 0;
        in.nextInt();
        //addTournament(availibleTournaments.get(tourID));
    }

    @Override
    public void notify(String message) {
        System.out.println("Notifiaction");
    }
}
