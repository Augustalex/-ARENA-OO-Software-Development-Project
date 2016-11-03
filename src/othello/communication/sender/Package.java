package communication.sender;

/**
 * A package contains header information such as:
 *  - Sender IP address
 *  - Receiver IP address
 *  - Array of Request messages
 */
public class Package {

    private String delimiter = "|";
    private String senderAddress = "";
    private String receiverAddress = "";
    private String[] requests = new String[]{""};

    public static String getDelimiter(String payload){
        return payload.substring(0,1);
    }

    public Package senderAddress(String address){
        this.senderAddress = address;
        return this;
    }

    public Package receiverAddress(String address){
        this.receiverAddress = address;
        return this;
    }

    public Package setRequests(String[] requests){
        this.requests = requests;
        return this;
    }

    public String getSenderAddress(){
        return this.senderAddress;
    }

    public String getReceiverAddress(){
        return this.receiverAddress;
    }

    public String[] getRequests(){
        return this.requests;
    }

    public String getDelimiter(){
        return this.delimiter;
    }

    public String toString(){
        return PackageCompiler.encode(this);
    }
}
