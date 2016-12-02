package adService;

import rest.ReSTContainer;

import java.io.IOException;

/**
 * Created by August on 2016-12-01.
 */
public class AdServiceContainer extends ReSTContainer {

    public AdServiceContainer(int port) throws IOException {
        super(port);

        AdService preferenceService = new AdService();
        this.createContext("/", new AdServiceAPI(preferenceService));
        this.createContext("/id/", new AdServiceSinglePreferenceAPI(preferenceService));

    }
}
