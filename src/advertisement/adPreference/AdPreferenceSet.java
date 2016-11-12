package advertisement.adPreference;

import advertisement.preferences.PreferenceSet.PreferenceSet;
import advertisement.preferences.PreferenceSet.PreferenceSetFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains a sheet (a HashMap) of booleans that describes the preference
 * of the ad placer.
 */
public class AdPreferenceSet implements AdPreference{

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
    public boolean isPreferable(AdPreference otherPreference) {
        return this.getPreferenceSet().isPreferable(otherPreference.getPreferenceSet());
    }

    @Override
    public PreferenceSet getPreferenceSet() {
        return this.preferences;
    }


}
