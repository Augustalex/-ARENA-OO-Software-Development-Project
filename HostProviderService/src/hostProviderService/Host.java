package hostProviderService;

import java.io.Serializable;

/**
 * Created by August on 2016-11-29.
 */
public class Host implements Serializable{

    public String address;
    public int port;

    public Host(String address, int port){
        this.address = address;
        this.port = port;
    }

    public String getURL(){
        return "http://" + address + ":" + port + "/";
    }
}
