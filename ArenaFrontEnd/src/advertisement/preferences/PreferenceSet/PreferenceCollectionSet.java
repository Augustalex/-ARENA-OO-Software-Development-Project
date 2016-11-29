package advertisement.preferences.PreferenceSet;

import advertisement.preferences.Preference.Preference;
import advertisement.preferences.PreferenceSet.PreferenceIterator.PreferenceSetIteratorFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a set of preferences with a Java Collections List object.
 */
public class PreferenceCollectionSet implements PreferenceSet {

    private List<Preference> set = new ArrayList<>();

    public PreferenceCollectionSet(List<Preference> preferences){
        this.set = preferences;
    }

    @Override
    public Preference getPreference(int index) {
        return set.get(index);
    }

    @Override
    public int getSize() {
        return this.set.size();
    }

    @Override
    public boolean isPreferable(PreferenceSet otherPreferenceSet) {
        for (Preference preference : this)
            for(Preference otherPreference : otherPreferenceSet)
                if (!preference.isPreferable(otherPreference))
                    return false;

        return true;
    }

    @Override
    public Iterator<Preference> iterator() {
        return PreferenceSetIteratorFactory.newPreferenceSet(this);
    }

}
