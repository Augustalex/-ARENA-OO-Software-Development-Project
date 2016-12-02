package utilities.router.routerState;

import utilities.router.stateRestorer.StateLoader;
import utilities.router.stateRestorer.StateWriter;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by August on 2016-10-25.
 */
public class StateHistory<T> implements Serializable{

    private ArrayList<T> history = new ArrayList<>();

    private int currentStateIndex = -1;

    public void store(T state){
        if(currentStateIndex < this.history.size()-1)
            this.history.set(this.currentStateIndex, state);
        else
            this.history.add(state);

        this.currentStateIndex++;
    }

    public T getCurrent(){
        System.out.println(this.history.toString());
        return this.history.get(this.currentStateIndex);
    }

    public void previous(){
        System.out.println(this.history.toString());
        if(currentStateIndex <= 1)
            throw new IndexOutOfBoundsException();
        else
            this.currentStateIndex -= 1;
    }

    public void next(){
        System.out.println(this.history.toString());
        if(this.currentStateIndex >= this.history.size())
            throw new IndexOutOfBoundsException();
        else
            this.currentStateIndex++;
    }

    public void revertTo(int index){
        System.out.println(this.history.toString());
        if(index > this.history.size()-1 || index < 0)
            throw new IndexOutOfBoundsException();
        else
            this.currentStateIndex = index;
    }

    public void saveState(){
        StateWriter<StateHistory<T>> writer = new StateWriter<>();
        writer.writeState(this);
    }

    public StateHistory<T> loadState() throws IOException, ClassNotFoundException {
        StateLoader loader = new StateLoader();

        return (StateHistory<T>)loader.restoreState();
    }
}
