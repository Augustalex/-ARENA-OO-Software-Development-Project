package metaInformation.tournamentMetaInformation;

import metaInformation.IMetaInformationSetter;
import utilities.TimeDate;

/**
 * Interface for setting tournament meta information.
 */
public interface ITournamentMetaInformationSetter extends ITournamentMetaInformation, IMetaInformationSetter{

    /**
     * Sets start date of Tournament.
     * @param startDate
     * @return
     */
    ITournamentMetaInformationSetter setStartDate(TimeDate startDate);

}
