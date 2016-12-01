package arena.advertisement.advertisement.preferences.PreferenceSet;

import arena.advertisement.advertisement.preferences.Preference.Preference;

/**
 * Represents a set of Preferences.
 */
public interface PreferenceSet extends Iterable<Preference> {

    Preference getPreference(int index);

    /**
     * Returns the amount of preferences contained in this set.
     * @return
     */
    int getSize();

    /**
     * Returns true if a given set is a subset of this
     * set.
     * @param otherPreferenceSet
     * @return
     */
    boolean isPreferable(PreferenceSet otherPreferenceSet);
}
