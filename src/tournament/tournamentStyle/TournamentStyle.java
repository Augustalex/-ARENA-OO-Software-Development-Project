package tournament.tournamentStyle;

/**
 * Created by Hameo on 2016-11-14.
 */
public class TournamentStyle implements ITournamentStyle {
    private String name;
    private String description;
    private GroupSettings groupSettings;
    private EliminationSettings eliminationSettings;


    @Override
    public String getTournamentStyleName() {
        return name;
    }

    @Override
    public void setTournamentStyleName(String name) {
        this.name = name;
    }

    @Override
    public String getTournamentStyleDescription() {
        return description;
    }

    @Override
    public void setTournamentStyleDescription(String description) {
        this.description = description;
    }

    @Override
    public GroupSettings getGroupSettings() {
        return groupSettings;
    }

    @Override
    public void setGroupSettings(GroupSettings groupSettings) {
        this.groupSettings = groupSettings;
    }

    @Override
    public EliminationSettings getEliminationSettings() {
        return eliminationSettings;
    }

    @Override
    public void setEliminationSettings(EliminationSettings eliminationSettings) {
        this.eliminationSettings = eliminationSettings;
    }
}
