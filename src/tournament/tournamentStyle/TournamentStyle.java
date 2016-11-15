package tournament.tournamentStyle;

/**
 * Created by Hameo on 2016-11-14.
 */
public class TournamentStyle implements ITournamentStyle {
    private String name;
    private String description;
    private int size;
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
    public int getTournamentSize(){
        return this.size;
    }

    @Override
    public void setTournamentSize(int size){
        this.size = size;
    }

    @Override
    public GroupSettings getGroupSettings() {
        return groupSettings;
    }

    @Override
    public void setGroupSettings() {
        this.groupSettings = new GroupSettings();
    }

    @Override
    public EliminationSettings getEliminationSettings() {
        return eliminationSettings;
    }

    @Override
    public void setEliminationSettings() {
        this.eliminationSettings = new EliminationSettings();
    }

    @Override
    public String toString(){
        return this.name;
        /*return("Name: " + this.name
                + "\n Description: " + this.description
                + "\n Size: " + this.size
                + "\n Group Settings: " + groupSettings.getGroupAmount()
                + ", " + groupSettings.getRounds() + ", " + groupSettings.getMaxWinners()
                + " Elimination Settings: " + eliminationSettings.getBestOf());*/
    }
}
