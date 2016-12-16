package hoster;

import com.google.gson.Gson;
import hostProviderService.Host;
import hostProviderService.HostService;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import serviceInitiatorService.ServiceInitiatorContainer;

import java.io.IOException;
import java.net.Inet4Address;

/**
 * Created by August on 2016-11-30.
 */
public class Hoster {

    public static void main(String[] args) throws IOException {
        Hoster hoster = new Hoster();
        hoster.start();
    }

    public void start() throws IOException {

        String ip = Inet4Address.getLocalHost().getHostAddress().toString();

        Inet4Address.getLocalHost().getHostAddress();

        ServiceInitiatorContainer initiatorContainer_2005 = new ServiceInitiatorContainer(2010);
        initiatorContainer_2005.start();
        ServiceInitiatorContainer initiatorContainer_2006 = new ServiceInitiatorContainer(2011);
        initiatorContainer_2006.start();

        HostService host1 = new HostService("", new Host(ip, 2005), new Host(ip, 2010));
        HostService host2 = new HostService("", new Host(ip, 2006), new Host(ip, 2011));

        try{
            addHost(host1);
            addHost(host2);
        }
        catch(IOException ex){
            ex.printStackTrace();
            System.out.println("Could not add as host.");
        }
    }

    private void addHost(HostService hostService) throws IOException {
        System.out.println(
                Request.Post("http://172.20.10.3:2000/")
                .bodyString(new Gson().toJson(hostService), ContentType.APPLICATION_JSON)
                .execute()
                .returnResponse().toString()
        );
    }
}
