package metaInformation;

/**
 * Container for Meta information.
 * You can only get information from
 * this interface, and not set it.
 */
public interface IMetaInformation {

    /**
     * Retrieve the name representation of the object.
     *
     * @return
     */
    String getName();
    /**
     * Retrieve the object description.
     * @return
     */
    String getDescription();

    /**
     * Unique identifier for a tournament.
     * @return
     */
    long getID();
}
