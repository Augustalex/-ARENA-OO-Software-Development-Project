package arena.streamService;

import arena.session.ServiceDirectoryProxy;
import com.google.gson.Gson;
import hostProviderService.Host;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.security.Provider;

/**
 * Created by August on 2016-12-16.
 */
public class StreamServiceProxy {

    private final ServiceDirectoryProxy directoryProxy;

    public StreamServiceProxy(ServiceDirectoryProxy directoryProxy){
        this.directoryProxy = directoryProxy;
    }

    public void sendLiveStream(Host liveStreamConnectionDetails){
        directoryProxy.getLiveStreamService()
                .onCancel(() -> {
                    System.out.println("Could not retrieve service details from Service Directory.");
                })
                .onDelivery(host -> {
                    try{
                        Response response =
                                Request.Post(host.getURL())
                                        .bodyString(new Gson().toJson(liveStreamConnectionDetails), ContentType.APPLICATION_JSON)
                                        .execute();

                        System.out.println("Got response: " + response.returnResponse().getStatusLine().toString());
                    } catch (IOException e) {
                        System.out.println("Could not send Request to Service in Service Directory.");
                        e.printStackTrace();
                    }
                });
    }
}
