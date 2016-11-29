package advertisement.preferences.PreferenceSet.PreferenceIterator;

import advertisement.preferences.PreferenceSet.PreferenceSet;

/**
 * Facilitates creation of PreferenceSetIterators.
 */
public class PreferenceSetIteratorFactory {

    public static PreferenceSetIterator newPreferenceSet(PreferenceSet preferenceSet){
        return new PreferenceSetIterator(preferenceSet);
    }
}
