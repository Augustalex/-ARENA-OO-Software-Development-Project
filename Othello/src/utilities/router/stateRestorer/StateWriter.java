package utilities.router.stateRestorer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * Created by August on 2016-10-25.
 */
public class StateWriter<T> {

    private String dumpURL;

    public StateWriter(){
        this.dumpURL = "state.dump";
    }

    public StateWriter(String dumpURL){
        this.dumpURL = dumpURL;
    }

    public void writeState(T object){
        try {
            ObjectOutput output = new ObjectOutputStream(new FileOutputStream(this.dumpURL));
            output.writeObject(object);

            System.out.println("Success.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
