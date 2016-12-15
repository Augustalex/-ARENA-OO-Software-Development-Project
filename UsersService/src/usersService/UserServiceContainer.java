package usersService;

import rest.ReSTContainer;

/**
 * Created by August on 2016-11-26.
 */
public class UserServiceContainer extends ReSTContainer {

    public UserServiceContainer(IUsersService service, int port) {
        super(port);

        this.createContext("/", new UsersServiceAPI(service));
        this.createContext("/id/", new UsersServiceSingleUserAPI(service));
    }

}
