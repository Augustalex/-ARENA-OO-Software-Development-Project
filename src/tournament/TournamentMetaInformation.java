package tournament;

import java.util.Date;

/**
 * Created by Patric on 2016-11-11.
 */
public class TournamentMetaInformation extends MetaInformation {
    private Date startDate;

    public Date getStartDate(){
        return startDate;
    }

    public TournamentMetaInformation setStartDate(Date dateTime){ startDate = dateTime; return this; }
}
