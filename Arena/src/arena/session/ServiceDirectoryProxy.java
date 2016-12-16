package arena.session;

import com.google.gson.Gson;
import hostProviderService.Host;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.util.EntityUtils;
import rest.Delivery;
import rest.PropertyDelivery;
import usersService.UsersService;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by August on 2016-12-15.
 */
public class ServiceDirectoryProxy {

    private final Host connectionDetails;

    public ServiceDirectoryProxy(Host connectionDetails){
        this.connectionDetails = connectionDetails;
    }

    public Delivery<Host> getUsersService(){
        Delivery<Host> result = new PropertyDelivery<>();

        new Thread(() -> {
            try {
                Response response = Request.Get(connectionDetails.getURL() + "/id/UsersService/")
                        .execute();

                HttpResponse responseContent = response.returnResponse();
                String stringContent = String.valueOf(EntityUtils.toString(responseContent.getEntity()));

                if(responseContent.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_BAD_REQUEST)
                    result.cancel();
                else
                    result.deliver(new Gson().fromJson(stringContent, Host.class));

            } catch (IOException e) {
                System.out.println("Could not send request.");
                result.cancel();
                e.printStackTrace();
            }
        }).start();

        return result;
    }

    //TODO generify this function!!!! DRY
    public Delivery<Host> getLiveStreamService(){
        Delivery<Host> result = new PropertyDelivery<>();

        new Thread(() -> {
            try{
                Response response = Request.Get(connectionDetails.getURL() + "/id/SubStream/")
                        .execute();

                HttpResponse responseContent = response.returnResponse();
                String stringContent = String.valueOf(EntityUtils.toString(responseContent.getEntity()));

                if(responseContent.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_BAD_REQUEST)
                    result.cancel();
                else
                    result.deliver(new Gson().fromJson(stringContent, Host.class));

            } catch (IOException e) {
                System.out.println("Could not send request.");
                e.printStackTrace();
            }
        }).start();

        return result;
    }

}
