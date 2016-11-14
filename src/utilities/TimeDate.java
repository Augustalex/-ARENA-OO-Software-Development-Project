package utilities;

/**
 * Specifies a specific time and date.
 */
public class TimeDate {
    private String dateString;

    public TimeDate(String dateString){
        this.dateString = dateString;
    }

    @Override
    public String toString(){
        return this.dateString;
    }
}