package metaInformation;

import utilities.TimeDate;

/**
 * Created by Patric on 2016-11-11.
 */
public class TournamentMetaInformation extends MetaInformation {
    private TimeDate startDate;

    public TimeDate getStartDate(){
        return startDate;
    }

    public TournamentMetaInformation setStartDate(TimeDate timeDate){ startDate = timeDate; return this; }
}
