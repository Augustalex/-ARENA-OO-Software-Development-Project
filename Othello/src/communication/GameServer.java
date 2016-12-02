package communication;

import boardGameLibrary.players.Player;
import communication.connection.inputConnections.InputConnection;
import communication.connection.inputConnections.RequestInputSocket;
import communication.receiver.delivery.Delivery;
import communication.requests.GameRequest;
import storage.activePlayersManagement.ActivePlayersManager;

import java.io.IOException;

/**
 * Created by August on 2016-10-30.
 */
public class GameServer {

    private InputConnection requestConnection;
    private RequestReceiver requestReceiver;
    private ActivePlayersManager activePlayersManager;

    public GameServer(int port) throws IOException {
        this.requestConnection = new RequestInputSocket(port);
        this.requestReceiver = new RequestReceiver(this.requestConnection);
    }

    public ActivePlayersManager activeplayersManager(){
        return this.activePlayersManager;

    }

    public boolean canConnect(Player player){
        return false;
    }

    public Delivery<GameRequest> requestConnection(Player player){
        return null;
    }



}
