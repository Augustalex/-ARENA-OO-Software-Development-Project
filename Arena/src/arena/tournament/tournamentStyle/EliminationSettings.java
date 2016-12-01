package arena.tournament.tournamentStyle;


import java.io.Serializable;

/**
 * Defines the settings for a tournaments Elimination settings
 */
public class EliminationSettings implements Serializable{
    private int bestOf;

    public void setBestOf(int value){this.bestOf = value;};
    public int getBestOf(){return this.bestOf;};
}
