package serviceClient.openService;

import rest.ReSTContainer;
import usersService.UsersServiceAPI;

/**
 * Created by August on 2016-12-14.
 */
public class OpenServiceContainer extends ReSTContainer {

    public OpenServiceContainer(int port) {
        super(port);

        createContext("/", new OpenServiceAPI());
    }
}
