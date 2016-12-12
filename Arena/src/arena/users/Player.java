package arena.users;

import arena.games.game.Game;
import arena.games.game.IGame;
import arena.games.gameInformation.OthelloGameInformation;
import arena.league.ILeague;
import arena.league.League;
import arena.tournament.ITournament;
import arena.tournament.TournamentFactory;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
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
    private Color color = ((Supplier<Color>)() -> {
        Random random = new Random(System.currentTimeMillis());
        return Color.rgb(
            random.nextInt(255), random.nextInt(255), random.nextInt(255)
        );
    }).get();

    public Player(IUser user) {
        this.user = user;
    }

    public static IPlayer create(String name){
        IPlayer player = Player.newMockPlayerFromUser(User.createMockUser(name));
        return player;
    }
    public static IPlayer createWOTournament(String name){
        IPlayer player = Player.newMockPlayerWOTournament(User.createMockUser(name));
        return player;
    }

    private static IPlayer newMockPlayerWOTournament(IUser mockUser) {
        IPlayer player = new Player(mockUser);
        return player;
    }

    public static IPlayer newMockPlayerFromUser(IUser user){
        IPlayer player = new Player(user);
        player.getLeagues().add(ILeague.globalLeague);
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
    public Color getColor() {
        return this.color;
    }

    @Override
    public IPlayer setColor(Color color) {
        this.color = color;
        return this;
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
