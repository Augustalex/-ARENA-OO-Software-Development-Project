package spectate;

import games.game.IGame;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Simon on 24/11/2016.
 */
public class AvailibleGamesList implements Serializable{

    ArrayList<IGame> availibleGames = new ArrayList<>();

    public AvailibleGamesList(){

    }

    public void viewAvailibeGames(){
        for(int i = 0; i < availibleGames.size(); i++){
            System.out.println(availibleGames.get(i).getGameInformation().getGameName());
        }
    }
}
