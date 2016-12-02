package utilities.router.routerState;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by August on 2016-10-25.
 */
public class RouterState implements Serializable {

    private Map dependencies;
    private String viewId;

    public RouterState(String viewId, Map dependencies){
        this.dependencies = dependencies;
        this.viewId = viewId;
    }

    public Map getDependencies(){
        return this.dependencies;
    }

    public String getViewId(){
        return this.viewId;
    }

    @Override
    public String toString(){
        return "[" + viewId + ", dependencies: " + this.dependencies + "] ";
    }
}
