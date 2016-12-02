package adService;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Stores AdPreference objects.
 */
public class AdService{

    private Map<String, AdPreference> preferences = new HashMap<>();

    AdPreference getAdPreference(String preferenceId){
        return preferences.get(preferenceId);
    }

    AdPreference[] getAllAdPreferences(){
        return preferences.values().stream().toArray(AdPreference[]::new);
    }

    void removeAdPreference(String preferenceId){
        preferences.remove(preferenceId);
    }

    void addAdPreference(AdPreference adPreference){
        preferences.put(adPreference.getPreferenceId(), adPreference);
    }

}
