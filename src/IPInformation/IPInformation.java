package IPInformation;

/**
 * Contains IP information for connecting to a server o client.
 */
public class IPInformation {

    private String ipv4Address;

    public IPInformation(String ipv4Address){
        this.ipv4Address = ipv4Address;
    }

    public String getIpv4Address(){
        return this.ipv4Address;
    }
}
