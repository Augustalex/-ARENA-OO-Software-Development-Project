package hostProviderService.idService;

/**
 * Created by August on 2016-12-13.
 */
public class IdService {

    private int nextId;

    public IdService(){
        nextId = 0;
    }

    public IdService(int startId){
        nextId = startId;
    }

    public int getNextId(){
        return nextId++;
    }
}
