package communication.requests;

import communication.sender.Package;
import communication.sender.Sender;

/**
 * Created by August on 2016-10-27.
 */
public class RequestSender {

    private Sender sender;
    private String senderAddress;

    public RequestSender(String senderAddress){
        this.sender = new Sender();
        this.senderAddress = senderAddress;
    }

    public void sendRequest(String receiverAddress, String request){
        this.sender.send(
                new Package()
                .senderAddress(this.senderAddress)
                .receiverAddress(receiverAddress)
                .setRequests(new String[]{request})
        );
    }

    public void sendRequests(String receiverAddress, String[] requests){
        this.sender.send(
                new Package()
                .senderAddress(this.senderAddress)
                .receiverAddress(receiverAddress)
                .setRequests(requests)
        );
    }
}
