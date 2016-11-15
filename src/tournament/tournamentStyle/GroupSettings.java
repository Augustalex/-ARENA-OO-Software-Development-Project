package tournament.tournamentStyle;

/**
 * Created by Hameo on 2016-11-14.
 */
public class GroupSettings {

    private int groupsAmount;
    private int rounds;
    private int maxWinners; // The amount that qualifies to continue the elimationround.

    public void setGroupAmount(int input){
        this.groupsAmount = input;
    }
    public int getGroupAmount(){return this.groupsAmount;}

    public void setRounds(int input){this.rounds = input;}
    public int getRounds(){
        return this.rounds;
    }

    public void setMaxWinners(int value){
        this.maxWinners = value;
    }
    public int getMaxWinners(){
        return this.maxWinners;
    }
}
