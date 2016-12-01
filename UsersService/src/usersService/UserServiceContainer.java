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

        UsersService service = new UsersService();
        this.createContext("/", new UsersServiceAPI(service));
        this.createContext("/id/", new UsersServiceSingleUserAPI(service));
    }

}
