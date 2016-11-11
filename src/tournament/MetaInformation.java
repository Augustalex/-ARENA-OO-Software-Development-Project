package tournament;

/**
 * Created by Patric on 2016-11-11.
 */
public abstract class MetaInformation {
    private String name;
    private String description;

     public MetaInformation setDescription(String description){
         this.description = description;
         return this;
     }

     public MetaInformation setName(String name){
         this.name = name;
         return this;
     }

     public String getDescription(){
         return description;
     }

     public String getName(){
         return name;
     }
}
