package users;

import league.ILeague;
import tournament.ITournament;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the IUser type IPlayer.
 */
public interface IPlayer extends IUser {

    ArrayList<ILeague> getLeagues();

    List<ITournament> getAvailibleTournaments();

}
