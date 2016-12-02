package arena.advertisement.preferences.Preference;

/**
 * Creates new preferences from different available data.
 */
public class PreferenceFactory {

    /**
     * Creates a new preference given a String description and a boolean
     * specifying whether the preference is a positive preference:
     * i.e. this ad SHOULD show up in the "PlayView" -> newPreference("PlayView", true)
     *      this ad SHOULD NOT show up in the "MainWindow" -> newPreference("MainWindow", false)
     *
     * @param description a description of the preference.
     * @param isPositive a boolean for if it is a negative or positive preference.
     * @return
     */
    public static Preference newPreference(String description, boolean isPositive){
        if(isPositive)
            return new PositivePreference(description);
        else
            return new NegativePreference(description);
    }
}
