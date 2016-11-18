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
    public String getName();
    /**
     * Retrieve the object description.
     * @return
     */
    public String getDescription();

    /**
     * Unique identifier for a tournament.
     * @return
     */
    public long getID();
}
