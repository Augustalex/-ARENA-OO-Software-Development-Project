package metaInformation.tournamentMetaInformation;

import metaInformation.IMetaInformation;
import utilities.TimeDate;

/**
 * Meta information about the domain object "Tournament".
 *
 * Contains basic meta information such as name, description and start date.
 */
public interface ITournamentMetaInformation extends IMetaInformation {

    /**
     * Returns a TimeDate object that represents when the tournament should kick off.
     * @return
     */
    TimeDate getStartDate();

}
