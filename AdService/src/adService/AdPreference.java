package adService;

/**
 * Created by August on 2016-11-24.
 */
public class AdPreference {

    private String preferenceId;
    private int timeLimit;
    private boolean closable;

    public AdPreference(String preferenceId, int timeLimit, boolean closable){
        this.preferenceId = preferenceId;
        this.timeLimit = timeLimit;
        this.closable = closable;
    }

    public String getPreferenceId() {
        return this.preferenceId;
    }

    public int getTimeLimit(){
        return this.timeLimit;
    }

    public boolean isClosable(){
        return this.closable;
    }

}
