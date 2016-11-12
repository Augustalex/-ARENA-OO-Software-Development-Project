package advertisement.adPreference;

import advertisement.preferences.PreferenceSet.PreferenceSet;

/**
 * A preference to where an Ad should be placed within the application.
 */
public interface AdPreference{

    /**
     * Compares the current preference with a given other preference
     * to tell whether the current preference contains all options of the
     * given preference.
     * @param otherPreference
     * @return
     */
    boolean isPreferable(AdPreference otherPreference);

    /**
     * Returns the preference set containing all preferences
     * related to the ad.
     * @return a set of preferences.
     */
    PreferenceSet getPreferenceSet();
}
