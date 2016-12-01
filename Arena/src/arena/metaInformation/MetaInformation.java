package arena.metaInformation;

/**
 * Contains key information about an Object of domain importance.
 * i.e. a Tournament object or a IPlayer object.
 *
 * But the actual use is not specified and is purely utilitarian.
 */
public abstract class MetaInformation implements IMetaInformationSetter {
    private long id;
    private String name;
    private String description;

    private static long nextId = 0;

    public MetaInformation(){
        this.id = MetaInformation.nextId++;
    }

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

    /**
     * Unique identifier for a arena.tournament.
     * @return
     */
     public long getID(){
         return this.id;
     }
}
