package boardGameLibrary.playerProfileStore;

import boardGameLibrary.playerProfileStore.exceptions.ProfileNotFoundException;
import boardGameLibrary.players.LocalPlayer;
import boardGameLibrary.players.Player;
import utilities.dataStore.DataStore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by August on 2016-10-25.
 */
public class PlayerProfileStore extends DataStore<PlayerProfile>{

    public PlayerProfile get(String profileName) {
        List<PlayerProfile> result = this.getStoreCopy().parallelStream()
                .filter(profile -> profile.getName().equals(profileName))
                .collect(Collectors.toList());

        if(result.size() <= 0)
            throw new ProfileNotFoundException();
        else
            return result.get(0);
    }

    public Player[] toPlayers() {
        return this.getStoreCopy().parallelStream()
                .map(profile -> new LocalPlayer(profile.getName(), profile.getColor()))
                .toArray(Player[]::new);
    }

}
