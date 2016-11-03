package communication.requests;

/**
 * A request message is encoding as follows:
 * Message: "move[3,5][3,9]" Type: ORDER
 * Encoded request message: "+move[3,5][3,9]"
 */
public class RequestCompiler {

    public static String encodeToString(Request request){
        String output = "";

        output += request.type.getIdentifier();
        output += request.message;

        return output;
    }

    public static Request decode(String request){
        return new Request(
                getMessageWithoutRequestType(request),
                RequestCompiler.getRequestTypeFromRequestMessage(request)
        );
    }

    private static RequestType getRequestTypeFromRequestMessage(String message){
        return RequestType.getRequestType(message.substring(0,1));
    }

    private static String getMessageWithoutRequestType(String requestMessage){
        return requestMessage.substring(1);
    }
}
