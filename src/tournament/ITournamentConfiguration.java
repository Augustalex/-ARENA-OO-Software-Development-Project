package tournament;

/**
 * Created by Patric on 2016-11-11.
 */
public interface ITournamentConfiguration {

    ITournamentConfiguration setTournamentStyle(TournamentStyle tournamentStyle);

    ITournamentConfiguration setMetaInformation(TournamentMetaInformation tournamentMetaInformation);

    TournamentStyle getTournamentStyle();

    TournamentMetaInformation getMetaInformation();

}
