package utilities.router.stateRestorer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by August on 2016-10-25.
 */
public class StateLoader {

    private String dumpURL;

    public StateLoader(){
        this.dumpURL = "state.dump";
    }

    public StateLoader(String dumpURL){
        this.dumpURL = dumpURL;
    }

    public Object restoreState() throws IOException, ClassNotFoundException {
        return new ObjectInputStream(new BufferedInputStream(new FileInputStream(this.dumpURL))).readObject();
    }
}
