package communication.requests;

/**
 * Created by August on 2016-10-27.
 */
public class Request {

    public String message;
    public RequestType type;

    public Request(String message, RequestType type){
        this.message = message;
        this.type = type;
    }

    public static boolean validateRequestMessage(String requestMessage){

        String typeIdentifier = requestMessage.substring(0, 1);

        if(!RequestType.validateTypeIdentifier(typeIdentifier))
            return false;

        return true;
    }
}
