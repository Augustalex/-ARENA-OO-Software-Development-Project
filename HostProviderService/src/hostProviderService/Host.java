package hostProviderService;

/**
 * Created by August on 2016-11-29.
 */
public class Host {

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
