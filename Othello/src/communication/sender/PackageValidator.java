package communication.sender;

import communication.requests.Request;

import java.util.StringTokenizer;

/**
 * Validates the correctness of a Package. That is, if the encoded message is a
 * legal representation of a Package object. If not, it cannot be reconstructed.
 */
public class PackageValidator {

    public static boolean validatePackage(String message){
        StringTokenizer tokenizer = new StringTokenizer(message, message.substring(0,1));
        String senderIP = tokenizer.nextToken();
        String receiverIP = tokenizer.nextToken();

        if(!PackageValidator.validateIPAddress(senderIP) || !PackageValidator.validateIPAddress(receiverIP))
            return false;

        while(tokenizer.hasMoreTokens())
            if(!Request.validateRequestMessage(tokenizer.nextToken()))
                return false;

        return true;
    }

    public static boolean validateIPAddress(String address){
        StringTokenizer tokenizer = new StringTokenizer(address, ".");

        if(tokenizer.countTokens() > 4)
            return false;

        while(tokenizer.hasMoreTokens())
            if(tokenizer.nextToken().length() != 3)
                return false;

        return true;
    }
}
