package metaInformation.tournamentMetaInformation;

import metaInformation.MetaInformation;
import utilities.TimeDate;

/**
 * Contains {@link MetaInformation} about a Tournament domain object.
 */
public class TournamentMetaInformation extends MetaInformation {

    private TimeDate startDate;

    public TimeDate getStartDate(){
        return startDate;
    }

    public TournamentMetaInformation setStartDate(TimeDate timeDate){ startDate = timeDate; return this; }

}
