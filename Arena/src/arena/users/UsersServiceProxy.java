package arena.users;

import com.google.gson.Gson;
import hostProviderService.Host;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import rest.Delivery;
import rest.PropertyDelivery;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;

/**
 * Created by August on 2016-12-15.
 */
public class UsersServiceProxy {


    private final Host serviceConnectionDetails;

    public UsersServiceProxy(Host serviceConnectionDetails){
        System.out.println(serviceConnectionDetails.getURL());
        this.serviceConnectionDetails = serviceConnectionDetails;
    }

    public Delivery<User[]> getAllUsers(){
        Delivery<User[]> result = new PropertyDelivery<>();

        new Thread(() -> {
            try {
                result.deliver(
                        new Gson().fromJson(
                            Request.Get(serviceConnectionDetails.getURL())
                                    .execute()
                                    .returnContent()
                                    .asString(),
                            User[].class)
                );
            } catch (IOException e) {
                System.out.println("Could not retrieve all users.");
                e.printStackTrace();
            }
        }).start();


        return result;
    }

    public Delivery<User> getUser(String username, String password){

        Delivery<User> result = new PropertyDelivery<>();

        new Thread(() -> {
            try {//TODO content type of TEXT.PLAIN could be wrong. Could also be DEFAULT_TEXT
                System.out.println("Username: " + username);
                System.out.println("URL: " + serviceConnectionDetails.getURL());
                Response response = Request
                        .Post(serviceConnectionDetails.getURL() + "/id/" + username)
                        .bodyString(password, ContentType.TEXT_PLAIN)
                        .execute();

                HttpResponse responseContent = response.returnResponse();
                String stringContent = EntityUtils.toString(responseContent.getEntity());
                if(responseContent.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_UNAUTHORIZED)
                    result.cancel();
                else
                    result.deliver(new Gson().fromJson(stringContent, User.class));

            } catch (IOException e) {
                System.out.println("Could not retrieve all users.");
                e.printStackTrace();
            }
        }).start();


        return result;
    }
}
