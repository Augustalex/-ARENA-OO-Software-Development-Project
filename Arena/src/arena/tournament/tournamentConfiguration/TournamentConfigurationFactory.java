package arena.tournament.tournamentConfiguration;

import arena.metaInformation.tournamentMetaInformation.ITournamentMetaInformationSetter;
import arena.metaInformation.tournamentMetaInformation.TournamentMetaInformation;
import arena.tournament.tournamentStyle.TournamentStyleFactory;
import arena.timeDate.*;

import java.io.Serializable;

/**
 * Creates a TournamentConfiguration given different sets of available arguments.
 */
public class TournamentConfigurationFactory implements Serializable {

    /**
     * Returns a new arena.tournament configuration that is void of settings.
     * @return
     */
    public static ITournamentConfiguration newTournamentConfiguration(){
        return new TournamentConfiguration();
    }

  /*  public static ITournamentConfiguration newConfigurationMock(){
        return new TournamentConfiguration()
                .setMetaInformation(
                        (ITournamentMetaInformation) new TournamentMetaInformation()
                        .setName("BajsTurnering2000")
                        .setDescription("Turnering 2000")
                )
                .setTournamentStyle(TournamentStyleFactory.newMockTournamentStyle());
    }*/

    public static ITournamentConfiguration newConfigurationMock(String tournamentName){

        TournamentConfiguration tournamentConfiguration = new TournamentConfiguration();
        ITournamentMetaInformationSetter tournamentMetaInformation = new TournamentMetaInformation();
        tournamentMetaInformation
                .setStartDate(new TimeDate("ASAP"))
                .setName(tournamentName)
                .setDescription(tournamentName);

        return tournamentConfiguration
                .setMetaInformation(tournamentMetaInformation)
                .setTournamentStyle(TournamentStyleFactory.newMockTournamentStyle());
    }

}
