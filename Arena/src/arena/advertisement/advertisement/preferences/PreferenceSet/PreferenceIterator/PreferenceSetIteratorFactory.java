package arena.advertisement.advertisement.preferences.PreferenceSet.PreferenceIterator;

import arena.advertisement.advertisement.preferences.PreferenceSet.PreferenceSet;

/**
 * Facilitates creation of PreferenceSetIterators.
 */
public class PreferenceSetIteratorFactory {

    public static PreferenceSetIterator newPreferenceSet(PreferenceSet preferenceSet){
        return new PreferenceSetIterator(preferenceSet);
    }
}
