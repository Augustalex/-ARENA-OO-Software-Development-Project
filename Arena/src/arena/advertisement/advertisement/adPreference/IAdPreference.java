package arena.advertisement.advertisement.adPreference;

import arena.advertisement.advertisement.preferences.PreferenceSet.PreferenceSet;

/**
 * A preference to where an Ad should be placed within the application.
 */
public interface IAdPreference {

    /**
     * Compares the current preference with a given other preference
     * to tell whether the current preference contains all options of the
     * given preference.
     * @param otherPreference
     * @return
     */
    boolean isPreferable(IAdPreference otherPreference);

    /**
     *
     * @deprecated
     * Returns the preference set containing all preferences
     * related to the ad.
     * @return a set of preferences.
     */
    PreferenceSet getPreferenceSet();

    String getPreferenceId();

    public int getTimeLimit();

    public boolean isClosable();

}
