package advertisement.adPreference;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains a sheet (a HashMap) of booleans that describes the preference
 * of the ad placer.
 */
public class AdPreferenceSheet implements AdPreference{

    private Map<String, Boolean> sheet = new HashMap<>();

    public AdPreferenceSheet(){
        sheet.put("PlayView", false);
        sheet.put("MainWindow", false);
    }

    public AdPreferenceSheet(Map<String, Boolean> sheet){
        this.sheet = sheet;
    }

    @Override
    public boolean isPreferable(AdPreference otherPreference) {
        return this.getPreferenceSheet()
                .keySet()
                .containsAll(
                        otherPreference
                                .getPreferenceSheet()
                                .keySet()
                        );
    }

    @Override
    public Map<String, Boolean> getPreferenceSheet() {
        return this.sheet;
    }


}
