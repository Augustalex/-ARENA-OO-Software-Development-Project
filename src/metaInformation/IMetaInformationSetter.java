package metaInformation;

/**
 * Extends IMetaInformation with setter methods.
 */
public interface IMetaInformationSetter extends IMetaInformation {

    /**
     * Set the name of the object described in this class.
     *
     * @param name
     * @return
     */
    public MetaInformation setName(String name);

    /**
     * Set the object description of the object represented in this class.
     *
     * @param description
     * @return
     */
    public MetaInformation setDescription(String description);

}