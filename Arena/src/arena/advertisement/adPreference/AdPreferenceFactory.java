package arena.advertisement.adPreference;

import arena.metaInformation.MetaInformation;

import java.util.HashMap;
import java.util.Map;

/**
 * Creates IAdPreference objects from concrete Classes,
 * and hides it behind the Interface {@link IAdPreference}.
 */
public class AdPreferenceFactory {

    /**
     * Returns an IAdPreference that prefers the PlayView.
     * @return
     */
    public static IAdPreference newPlayViewPreference(){
        Map<String, Boolean> sheet = new HashMap<>();
        sheet.put("PlayView", true);

        return new AdPreferenceSet(sheet);
    }

    /**
     * Returns an IAdPreference that prefers the MainWindow.
     */
    public static IAdPreference newMainWindowPreference(){
        Map<String, Boolean> sheet = new HashMap<>();
        sheet.put("MainWindow", true);

        return new AdPreferenceSet(sheet);
    }

    public static IAdPreference newAdPreference(String preferenceId, int timeLimit, boolean closeable){
        return new AdPreference(preferenceId, timeLimit, closeable);
    }
}
