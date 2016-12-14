package serviceDirectory;

import hostProviderService.idService.IdService;

/**
 * Created by August on 2016-12-14.
 */
public class ServiceTypeEntry {

    private IdService nextId = new IdService(0);
    private String typeName;

    public ServiceTypeEntry(String typeName){
        this.typeName = typeName;
    }

}
