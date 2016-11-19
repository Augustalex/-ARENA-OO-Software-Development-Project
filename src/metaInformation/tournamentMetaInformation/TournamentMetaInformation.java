package metaInformation.tournamentMetaInformation;

import metaInformation.MetaInformation;
import utilities.TimeDate;

/**
 * Contains {@link MetaInformation} about a Tournament domain object.
 *
 * "Decorates" the class of Meta Information with a getter and setter for the Start Date of a Tournament.
 */
public class TournamentMetaInformation extends MetaInformation implements ITournamentMetaInformationSetter {

    private TimeDate startDate;

    public TimeDate getStartDate(){
        return startDate;
    }

    public TournamentMetaInformation setStartDate(TimeDate timeDate){ startDate = timeDate; return this; }

}
