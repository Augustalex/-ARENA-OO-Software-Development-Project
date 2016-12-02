package communication.connection.inputConnections;

import communication.connection.Connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by August on 2016-10-29.
 */
public abstract class SocketConnection implements Connection {

    private ServerSocket serverSocket = null;
    protected Socket connection = null;

    public SocketConnection(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    @Override
    public void connect() throws Exception {
        this.connection = this.serverSocket.accept();
    }

    @Override
    public void close() {
        try {
            if(this.serverSocket != null)
                this.connection.close();
            if(this.connection != null)
                this.connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
