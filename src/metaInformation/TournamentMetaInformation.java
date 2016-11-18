package metaInformation;

import utilities.TimeDate;

/**
 * Contains {@link MetaInformation} about a Tournament domain object.
 */
public class TournamentMetaInformation extends MetaInformation {
    private TimeDate startDate;
    private String tourName;
    public TimeDate getStartDate(){
        return startDate;
    }

    public TournamentMetaInformation setStartDate(TimeDate timeDate){ startDate = timeDate; return this; }

    public String getTourName(){
        return tourName;
    }

    public void setTourName(String name){
        this.tourName = name;
    }
}
