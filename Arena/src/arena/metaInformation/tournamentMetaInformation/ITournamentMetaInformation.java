package arena.metaInformation.tournamentMetaInformation;

import arena.metaInformation.IMetaInformation;
import arena.timeDate.TimeDate;

/**
 * Meta information about the domain object "Tournament".
 *
 * Contains basic meta information such as name, description and start date.
 */
public interface ITournamentMetaInformation extends IMetaInformation {

    /**
     * Returns a TimeDate object that represents when the arena.tournament should kick off.
     * @return
     */
    TimeDate getStartDate();

}
