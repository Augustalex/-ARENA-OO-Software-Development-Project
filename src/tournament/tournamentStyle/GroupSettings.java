package tournament.tournamentStyle;

/**
 * Created by Hameo on 2016-11-14.
 */
public class GroupSettings {

    private int groupAmount;
    private int rounds;
    private int maxPromoters; // The amount that qualifies to continue the elimationround.

    public void setGroupAmount(int input){
        this.groupAmount = input;
    }
    public int getGroupAmount(){
        return this.groupAmount;
    }

    public void setMaxGames(int input){
        this.rounds = input;
    }
    public int getMaxGames(){
        return this.rounds;
    }
    public void setMaxPromoters(int value){
        this.maxPromoters = value;
    }
    public int getMaxPromoters(){
        return this.maxPromoters;
    }
}
