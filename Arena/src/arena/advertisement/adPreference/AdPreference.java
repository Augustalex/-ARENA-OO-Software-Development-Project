package arena.advertisement.adPreference;

import arena.advertisement.preferences.PreferenceSet.PreferenceSet;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by August on 2016-11-24.
 */
public class AdPreference implements IAdPreference {

    private String preferenceId;
    private int timeLimit;
    private boolean closable;

    public AdPreference(String preferenceId, int timeLimit, boolean closable){
        this.preferenceId = preferenceId;
        this.timeLimit = timeLimit;
        this.closable = closable;
    }

    @Override
    public boolean isPreferable(IAdPreference otherPreference) {
        return otherPreference.getPreferenceId().equals(this.preferenceId);
    }

    @Override
    public PreferenceSet getPreferenceSet() {
        throw new NotImplementedException();
    }

    @Override
    public String getPreferenceId() {
        return this.preferenceId;
    }

    @Override
    public int getTimeLimit(){
        return this.timeLimit;
    }

    @Override
    public boolean isClosable(){
        return this.closable;
    }
}
