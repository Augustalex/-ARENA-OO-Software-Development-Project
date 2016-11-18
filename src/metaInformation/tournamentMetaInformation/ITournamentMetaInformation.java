package metaInformation.tournamentMetaInformation;

import metaInformation.IMetaInformation;
import utilities.TimeDate;

/**
 * Created by August on 2016-11-18.
 */
public interface ITournamentMetaInformation extends IMetaInformation {

    /**
     * Returns a TimeDate object that represents when the tournament should kick off.
     * @return
     */
    TimeDate getStartDate();

}
