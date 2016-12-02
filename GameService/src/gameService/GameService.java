package gameService;

import hostProviderService.Host;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hameo on 2016-12-02.
 */
public class GameService {

    private List<Host> hosts = new ArrayList<>();

    public Host startNewGame(PlayerHostInformation[] players){
        return null;
    }

    public void setGameAsEnded(String hostAddress){
        hosts.remove(host);
    };

    public Host[] getAllActiveGames(){
        return hosts.stream().toArray(Host[]::new);
    }
}
