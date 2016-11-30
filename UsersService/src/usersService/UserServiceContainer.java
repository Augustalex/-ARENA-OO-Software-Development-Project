package usersService;

import rest.ReST;
import rest.ReSTContainer;

import java.io.IOException;

/**
 * Created by August on 2016-11-26.
 */
public class UserServiceContainer extends ReSTContainer {

    public UserServiceContainer(int port) throws IOException {
        super(port);

        ReST api = new UsersAPI(new UsersService());
        this.createContext("/", api);
    }

}
