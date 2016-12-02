package utilities.router;

import utilities.router.exceptions.NoRouterSetException;
import utilities.router.routerState.RouterState;
import utilities.router.routerState.StateHistory;
import utilities.router.stateRestorer.StateLoader;
import utilities.router.stateRestorer.exceptions.UnableToLoadState;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by August on 2016-10-10.
 */
public abstract class Router implements Serializable{

    private static Router applicationRouter = null;

    /**
     * History object, part of the HistoryTracker package, is a memento like object
     * able to store and recall function calls. In part with this class the History
     * object is used to recall previous routes, thus enabling back and forward routing
     * functionality.
     */
    //protected transient History history;

    protected StateHistory<RouterState> stateHistory;

    public Router(){
        //this.history = new History();
        this.stateHistory = new StateHistory<>();
    }

    public void route(String viewId, Map data) {
        routeToView(viewId, data);

        /*Method method = null;
        for(Method m : this.getClass().getDeclaredMethods()){
            if(m.getName().contains("route"))
                method = m;
        }
*/
        this.stateHistory.store(new RouterState(viewId, data));
        //this.history.store(new ArrayCallback<>(method, this, new Object[]{viewId, data}));
    }

    public void route(RouterState state){
        this.route(state.getViewId(), state.getDependencies());
    }

    public abstract void routeToView(String viewId, Map data);

    public void previous() {
        /*this.history.revertTo(this.history.getCursorPosition()-1);
        Callback call = this.history.get();

        try{
            call.invoke();
        }
        catch(Exception e){
            e.printStackTrace();
        }*/
        this.stateHistory.previous();
        this.route(this.stateHistory.getCurrent());

    }

    public void next() {
        /*this.history.revertTo(this.history.getCursorPosition()+1);
        Callback call = this.history.get();

        try {
            call.invoke();
        }
        catch (Exception e){
            e.printStackTrace();
        }*/
        this.stateHistory.next();
        this.route(this.stateHistory.getCurrent());
    }

    public void reload(){
        /*
        try{
            this.history.get().invoke();
        }
        catch(Exception e){
            e.printStackTrace();
        }*/
        this.route(this.stateHistory.getCurrent());
    }

    public void save(){
        this.stateHistory.saveState();
    }

    public void load() throws UnableToLoadState {
        StateLoader loader = new StateLoader();
        try{
            this.stateHistory = this.stateHistory.loadState();
            RouterState state = this.stateHistory.getCurrent();
            this.routeToView(state.getViewId(), state.getDependencies());
        }
        catch(Exception e){
            e.printStackTrace();
            throw new UnableToLoadState();
        }
    }

    public static void setApplicationRouter(Router router) {
        Router.applicationRouter = router;
    }

    public static Router getApplicationRouter(){
        if(Router.applicationRouter == null)
            throw new NoRouterSetException();

        System.out.println("Router was not null.");
        System.out.println(Router.applicationRouter);

        return Router.applicationRouter;
    }
}
