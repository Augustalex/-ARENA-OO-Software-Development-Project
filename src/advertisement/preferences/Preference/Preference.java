package advertisement.preferences.Preference;

/**
 * Represents a certain preference condition.
 */
public interface Preference<T> {

    /**
     * Returns the string represented description of the preference.
     * @return
     */
    T getPreference();

    /**
     * Returns true if the preference represents a positive preference.
     * @return
     */
    boolean isPositivePreference();

    /**
     * Returns true if the given preference matches this preference.
     * @param preference
     * @return
     */
    boolean isPreferable(Preference preference);

}
