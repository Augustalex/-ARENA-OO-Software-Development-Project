package tournament.tournamentConfiguration;

import metaInformation.tournamentMetaInformation.ITournamentMetaInformation;
import metaInformation.tournamentMetaInformation.TournamentMetaInformation;
import tournament.tournamentStyle.TournamentStyleFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static ITournamentConfiguration newConfigurationMock(){
        return new TournamentConfiguration()
                .setMetaInformation(
                        (ITournamentMetaInformation) new TournamentMetaInformation()
                        .setName("BajsTurnering2000")
                        .setDescription("Turnering 2000")
                )
                .setTournamentStyle(TournamentStyleFactory.newMockTournamentStyle());
    }

    public static ITournamentConfiguration newConfigurationMock(String tournamentName){
        return new TournamentConfiguration()
                .setMetaInformation(
                        (ITournamentMetaInformation) new TournamentMetaInformation()
                                .setName(tournamentName + "2000")
                                .setDescription(tournamentName)
                )
                .setTournamentStyle(TournamentStyleFactory.newMockTournamentStyle());
    }

}
