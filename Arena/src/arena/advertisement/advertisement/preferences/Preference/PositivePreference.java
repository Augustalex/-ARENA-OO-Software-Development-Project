package arena.advertisement.advertisement.preferences.Preference;

/**
 * Represents a positive preference.
 *
 * For example:
 * PositivePreference tournamentLobby = new PositivePreference("tournamentLobby");
 *  - This is a preference describing that something should be inside the tournamentLobby.
 *
 *  The style of defining a description needs to be agreed upon by the multiple arena.users of the
 *  classes, as "tournamentLobby" and "Tournament Lobby" is not equal preferences and will fail
 *  in a isPreferable method.
 */
public class PositivePreference extends StringPreference{


    public PositivePreference(String preference){
        super(preference);
    }

    @Override
    public boolean isPositivePreference() {
        return true;
    }
}
