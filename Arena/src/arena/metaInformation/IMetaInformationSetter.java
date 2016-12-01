package arena.metaInformation;

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
    IMetaInformationSetter setName(String name);

    /**
     * Set the object description of the object represented in this class.
     *
     * @param description
     * @return
     */
    IMetaInformationSetter setDescription(String description);

}