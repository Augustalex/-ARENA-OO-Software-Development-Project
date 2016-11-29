package advertisement.preferences.PreferenceSet.PreferenceIterator;

import advertisement.preferences.Preference.Preference;

import java.util.Iterator;

/**
 * Iterates over a collection of preferences.
 */
public interface IPreferenceSetIterator extends Iterator<Preference>{

    /**
     * Resets the iterator to the state at creation.
     * i.e. the cursor is set to index 0.
     */
    void reset();

}
