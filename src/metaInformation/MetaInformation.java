package metaInformation;

/**
 * Contains key information about an Object of domain importance.
 * i.e. a Tournament object or a Player object.
 *
 * But the actual use is not specified and is purely utilitarian.
 */
public abstract class MetaInformation {
    private String name;
    private String description;

    /**
     * Set the object description of the object represented in this class.
     * @param description
     * @return
     */
     public MetaInformation setDescription(String description){
         this.description = description;
         return this;
     }

    /**
     * Set the name of the object described in this class.
     * @param name
     * @return
     */
     public MetaInformation setName(String name){
         this.name = name;
         return this;
     }

    /**
     * Retrieve the object description.
     * @return
     */
     public String getDescription(){
         return description;
     }

    /**
     * Retrieve the name representation of the object.
     * @return
     */
     public String getName(){
         return name;
     }
}
