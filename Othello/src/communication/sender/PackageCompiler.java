package communication.sender;

import communication.receiver.exceptions.InvalidPackageException;

import java.util.StringTokenizer;

/**
 * Created by August on 2016-10-29.
 */
public class PackageCompiler {

    /**
     * Takes a {@link Package} and turns it into a formatted String.
     *
     * The structure of the encoded the String is as follows:
     * |senderAddress|receiverAddress|requestMessage|requestMessage
     * With the first character defining the delimiter for message tokenization.
     * There may be any number of requestMessages, even 0.
     * Any sign that is not in the requestMessages may be used as a delimiter, as long
     * as it is specified by being the first character in the encoded message.
     *
     * @param payload the Package object.
     * @return an encoded String representing the deconstructed Package.
     */
    public static String encode(Package payload){
        String output = "";
        String delimiter = payload.getDelimiter();

        output += delimiter;
        output += payload.getReceiverAddress() + delimiter;
        output += payload.getSenderAddress() + delimiter;

        for(String request : payload.getRequests())
            output += request + delimiter;

        return output;
    }

    /**
     * Takes an encoded Package and returns a new Package containing
     * the information of the encoded Package.
     *
     * @param payload the encoded Package (as a String)
     * @return a new decoded Package.
     */
    public static Package decode(String payload){
        String delimiter = Package.getDelimiter(payload);
        StringTokenizer tokenizer = new StringTokenizer(payload, delimiter);

        if(tokenizer.countTokens() < 2)
            throw new InvalidPackageException();

        return new Package()
                .senderAddress(tokenizer.nextToken())
                .receiverAddress(tokenizer.nextToken())
                .setRequests(
                        tokenizer.nextToken("").split(delimiter)
                );
    }
}
