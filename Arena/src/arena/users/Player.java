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
 * Implements interface IPlayer that extends the IUser interface.
 *
 * It acts as an IUser by delegation as opposed to inheritance. This is
 * as the IUser has no public constructor, preventing the client from instantiating
 * fake user objects. The only public method for creating a new user is either through
 * the static mock factory method or the static method for creating a new user
 * via getting user details from a UsersService (given that the correct user login
 * credentials are provided to the method parameters).
 */
public class Player implements IPlayer{

    private final IUser user;

    private ArrayList<ILeague> leagues = new ArrayList<>();

    private Player(IUser user) {
        this.user = user;
    }

    public static IPlayer newMockPlayer(){
        IPlayer player = new Player(User.createMockUser("August"));
        player.getLeagues().add(ILeague.createMockLeague("Mock League AF"));
        return player;
    }

    @Override
    public ArrayList<ILeague> getLeagues() {
        return leagues;
    }

    @Override
    public List<ITournament> getAvailableTournaments(){
        return leagues.stream()
                .flatMap(l -> l.getTournaments().stream())
                .collect(Collectors.toList());
    }

    @Override
    public void notify(String message) {
        System.out.println("Notification");
    }

    @Override
    public String getName() {
        return user.getName();
    }

    @Override
    public int getId() {
        return user.getId();
    }


}
