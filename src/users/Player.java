package users;

import league.League;
import tournament.ITournament;
import tournament.Tournament;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by Simon on 14/11/2016.
 */
public class Player implements IPlayer {

    ArrayList<League> leagues;
    List<ITournament> availibleTournaments;
    ArrayList<ITournament> joinedTournaments = new ArrayList<>();

    public Player(){

    }

    @Override
    public ArrayList<League> getLeagues() {
        //SQL shit
        return leagues;
    }

    @Override
    public List<ITournament> getAvailibleTournaments(ArrayList<League> leagues){
        return leagues.stream()
                .flatMap(l -> l.getTournamentsInLeague().stream())
                .collect(Collectors.toList());

    }

    private void tournamentHandler(){

        availibleTournaments = getAvailibleTournaments(getLeagues());
        for(int i = 0; i < leagues.size(); i++){
            leagues.get(i).printTournamentIDs();
        }
        Scanner in = new Scanner(System.in);
        int tourID = 0;
        in.nextInt();
        addTournament(availibleTournaments.get(tourID));
    }

    @Override
    public void addTournament(ITournament tour) {

        //add tournament

    }
}
