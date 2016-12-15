package arena.tournament;

import arena.users.IPlayer;

import java.util.ArrayList;

/**
 * Created by Simon on 18/11/2016.
 */

/**
 * Class consists of a list consisting of players applied to a
 * certain arena.tournament.
 */
public class AppliedPlayersList {
    ArrayList<IPlayer> appliedPlayersList = new ArrayList<>();

    public AppliedPlayersList(){

    }

    /**
     * Method for adding a player to the "appliedPlayersList"
     * @param player
     */
    public void applyPlayerToList(IPlayer player){
        appliedPlayersList.add(player);
    }

    /**
     * Help-method for viewing the players applied to this tournament.
     */
    public void viewAppliedPlayers(){
        for(int i = 0; i < appliedPlayersList.size(); i++){
            System.out.println(appliedPlayersList.get(i).getName());
        }
    }

    public IPlayer getPlayerFromList(int index){
        return appliedPlayersList.get(index);
    }

    public int length(){
        return appliedPlayersList.size();
    }
}
