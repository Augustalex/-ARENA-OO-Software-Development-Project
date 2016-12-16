package subStream;

import rest.ReSTContainer;

/**
 * Created by August on 2016-12-15.
 */
public class SubStreamContainer extends ReSTContainer {

    public SubStreamContainer(SubStream subStream, int httpPort) {
        super(httpPort);

        createContext("/", new SubStreamAPI(subStream));
    }
}
