package arena.advertisement.advertisement.preferences.PreferenceSet;

import arena.advertisement.advertisement.preferences.Preference.Preference;
import arena.advertisement.advertisement.preferences.Preference.PreferenceFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Facilitates creation of PreferenceSets. Especially from different sources, such as
 * Maps.
 */
public class PreferenceSetFactory {

    /**
     * Creates a new PreferenceSet from a Map of specified Preferences.
     *
     * The map must be of a String-Boolean pair, such as the String represents
     * the description of the preference and the boolean represents whether the
     * preference is positive or negative.
     * @param preferences the collections of String-Boolean represented Preferences.
     * @return a Preference Set.
     */
    public static PreferenceSet newPreferenceSet(Map<String, Boolean> preferences){
        return new PreferenceCollectionSet(PreferenceSetFactory.fromMapToList(preferences));
    }

    private static List<Preference> fromMapToList(Map<String, Boolean> map){
        return map.entrySet().parallelStream()
                .map(entry -> PreferenceFactory.newPreference(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
