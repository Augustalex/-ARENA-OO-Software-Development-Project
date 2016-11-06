package advertisement.adPreference;

import java.util.Map;

/**
 * A preference to where an Ad should be placed within the application.
 */
public interface AdPreference {

    /**
     * Compares the current preference with a given other preference
     * to tell whether the current preference contains all options of the
     * given preference.
     * @param otherPreference
     * @return
     */
    boolean isPreferable(AdPreference otherPreference);

    /**
     * Returns the current objects Map of boolean preferences.
     * @return
     */
    Map<String, Boolean> getPreferenceSheet();
}
