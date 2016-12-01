package arena.advertisement.advertisement.preferences.PreferenceSet.PreferenceIterator;

import arena.advertisement.advertisement.preferences.Preference.Preference;
import arena.advertisement.advertisement.preferences.PreferenceSet.PreferenceSet;

/**
 * Iterates over a PreferenceSet.
 */
public class PreferenceSetIterator implements IPreferenceSetIterator{

    private final PreferenceSet preferenceSet;
    private int cursor = 0;

    public PreferenceSetIterator(PreferenceSet preferenceSet){
        this.preferenceSet = preferenceSet;
    }

    @Override
    public void reset() {
        this.cursor = 0;
    }

    @Override
    public boolean hasNext() {
        return cursor < preferenceSet.getSize();
    }

    @Override
    public Preference next() {
        if(this.hasNext())
            return preferenceSet.getPreference(cursor++);
        else
            return null;
    }
}
