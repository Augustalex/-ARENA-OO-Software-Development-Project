package arena.advertisement.advertisement.adPreference;

import arena.advertisement.advertisement.preferences.PreferenceSet.PreferenceSet;
import arena.advertisement.advertisement.preferences.PreferenceSet.PreferenceSetFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains a sheet (a HashMap) of booleans that describes the preference
 * of the ad placer.
 */
public class AdPreferenceSet implements IAdPreference {

    private final PreferenceSet preferences;

    public AdPreferenceSet(Map<String, Boolean> sheet){
        this.preferences = PreferenceSetFactory.newPreferenceSet(sheet);
    }

    public AdPreferenceSet(){
        Map<String, Boolean> sheet = new HashMap<>();
        sheet.put("PlayView", false);
        sheet.put("MainWindow", false);

        this.preferences = PreferenceSetFactory.newPreferenceSet(sheet);
    }

    @Override
    public boolean isPreferable(IAdPreference otherPreference) {
        return this.getPreferenceSet().isPreferable(otherPreference.getPreferenceSet());
    }

    @Override
    public PreferenceSet getPreferenceSet() {
        return this.preferences;
    }

    @Override
    public String getPreferenceId() {
        throw new NotImplementedException();
    }

    @Override
    public int getTimeLimit() {
        return 0;
    }

    @Override
    public boolean isClosable() {
        return false;
    }


}
