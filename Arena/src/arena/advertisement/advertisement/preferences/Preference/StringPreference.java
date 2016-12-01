package arena.advertisement.advertisement.preferences.Preference;

/**
 * Represents a Preference interface description as a String.
 *
 */
public abstract class StringPreference implements Preference<String> {

    private final String description;

    public StringPreference(String description){
        this.description = description;
    }

    @Override
    public String getPreference() {
        return this.description;
    }

    /**
     * Compares another preference to see if the description matches with this preference
     * description. If so, are both positive? If that is the case, the method returns true.
     * @param preference
     * @return
     */
    @Override
    public boolean isPreferable(Preference preference) {
        return
                this.getPreference().equals(preference.getPreference())
                        && (preference.isPositivePreference() && this.isPositivePreference());
    }
}
