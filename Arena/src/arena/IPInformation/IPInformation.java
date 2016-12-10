package arena.IPInformation;

import java.io.Serializable;

/**
 * Contains IP information for connecting to a server o client.
 */
public class IPInformation implements Serializable{

    private final String ipv4Address;
    private final int port;

    public IPInformation(String ipv4Address, int port){
        this.ipv4Address = ipv4Address;
        this.port = port;
    }

    public String getIpv4Address(){
        return this.ipv4Address;
    }

    public int getPort(){
        return port;
    }
}
