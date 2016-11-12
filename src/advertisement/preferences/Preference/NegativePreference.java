package advertisement.preferences.Preference;

/**
 * Represents a preference with a string and as a negative preference.
 *
 * For further details see {@link PositivePreference}.
 */
public class NegativePreference extends StringPreference{

    public NegativePreference(String description) {
        super(description);
    }

    @Override
    public boolean isPositivePreference() {
        return false;
    }
}
