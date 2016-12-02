package gameService;

import hostProviderService.Host;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hameo on 2016-12-02.
 */
public class GameService {

    private int nextId = 0;

    private Map<String, Host> hosts = new HashMap<>();

    public String startNewGame(PlayerHostInformation[] players){
        String id = getNextId();
        hosts.put(id, null);

        return id;
    }

    public void setGameAsEnded(String hostId){
        hosts.remove(hostId);
    };

    public Host[] getAllActiveGames(){
        return hosts.values().stream().toArray(Host[]::new);
    }

    private String getNextId(){
        return String.valueOf(this.nextId++);
    }
}
