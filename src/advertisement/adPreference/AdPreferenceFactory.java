package advertisement.adPreference;

import java.util.HashMap;
import java.util.Map;

/**
 * Creates AdPreference objects from concrete Classes,
 * and hides it behind the Interface {@link AdPreference}.
 */
public class AdPreferenceFactory {

    /**
     * Returns an AdPreference that prefers the PlayView.
     * @return
     */
    public static AdPreference newPlayViewPreference(){
        Map<String, Boolean> sheet = new HashMap<>();
        sheet.put("PlayView", true);

        return new AdPreferenceSheet(sheet);
    }

    /**
     * Returns an AdPreference that prefers the MainWindow.
     */
    public static AdPreference newMainWindowPreference(){
        Map<String, Boolean> sheet = new HashMap<>();
        sheet.put("MainWindow", true);

        return new AdPreferenceSheet(sheet);
    }
}
