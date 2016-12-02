package communication.requests;

/**
 * Types of request that can be sent to another host/client.
 *
 * A question expects a response from the receiver in form of an answer,
 * often a boolean yes or no.
 *
 * An order prompts an action, if possible, at the other end. An order expects
 * a resource to be sent back from the receiving end. An order does not expect
 * error messages and a Question type request might be sent before hand to
 * check for possible errors.
 *
 * A demand prompts an action, if possible, at the other end. A demand does not
 * expect anything to be sent back from the receiving end.
 */
public enum RequestType {
    QUESTION("?"), ORDER("+"), DEMAND("!");

    private String identifier;

    RequestType(String identifier){
        this.identifier = identifier;
    }

    public static boolean validateTypeIdentifier(String typeIdentifier){
        for(RequestType type : RequestType.values())
            if(type.getIdentifier().equals(typeIdentifier))
                return true;

        return false;
    }

    public static RequestType getRequestType(String identifier){
        for(RequestType type : RequestType.values())
            if(type.getIdentifier().equals(identifier))
                return type;

        throw new IllegalArgumentException(identifier);
    }

    public String getIdentifier(){
        return this.identifier;
    }
}
