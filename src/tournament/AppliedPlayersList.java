package tournament;

import users.IPlayer;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Simon on 18/11/2016.
 */
public class AppliedPlayersList {
    ArrayList<IPlayer> appliedPlayersList = new ArrayList<>();

    public AppliedPlayersList(){

    }

    public void applyPlayerToList(IPlayer player){
        appliedPlayersList.add(player);
    }

    public void viewAppliedPlayers(){
        for(int i = 0; i < appliedPlayersList.size(); i++){
            System.out.println(appliedPlayersList.get(i).getName());
        }
    }
}
