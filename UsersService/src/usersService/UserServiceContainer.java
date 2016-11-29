package usersService;

import rest.ReST;
import rest.ReSTContainer;

import java.io.IOException;

/**
 * Created by August on 2016-11-26.
 */
public class UserServiceContainer extends ReSTContainer {

    public UserServiceContainer() throws IOException {
        super(2000);

        ReST api = new UsersAPI(new UsersService());
        this.createContext("/users", api);
    }

    public static void main(String[] args){
        try {
            new UserServiceContainer().start();

            System.out.println("Started service.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
