package tournament.tournamentConfiguration;

import metaInformation.tournamentMetaInformation.ITournamentMetaInformationSetter;
import metaInformation.tournamentMetaInformation.TournamentMetaInformation;
import tournament.tournamentStyle.TournamentStyleFactory;
import utilities.TimeDate;

import java.io.Serializable;

/**
 * Creates a TournamentConfiguration given different sets of available arguments.
 */
public class TournamentConfigurationFactory implements Serializable {

    /**
     * Returns a new tournament configuration that is void of settings.
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
