package augustsCrazyExperiment.serviceBus;

import augustsCrazyExperiment.Cached;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Reads from a locally stored file containing data in JSON format.
 *
 */
public class OfflineServiceDirectory implements ServiceDirectory{

    private static final String filePath = "services.json";

    private Cached<List<Service>> cache = new Cached<List<Service>>(getServiceListSupplier());

    /**
     * Returns all Services specified in a local JSON file.
     *
     * If the services is stored in the cache, then the cache is returned.
     *
     * @return
     */
    @Override
    public List<Service> getServices() {
        return this.cache.get();
    }

    public static void main(String[] args){
        OfflineServiceDirectory directory = new OfflineServiceDirectory();

        System.out.println("From file load:");
        try {
            directory.loadServices().forEach(service -> System.out.println(service.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Loaded from cache for first time: ");
        directory.cache.get().forEach(service -> System.out.println(service.toString()));

    }

    /**
     * Loads services from JSON file on disk (at predefined file path).
     * @return
     * @throws IOException
     */
    private List<Service> loadServices() throws IOException {
        byte[] file = Files.readAllBytes(Paths.get(OfflineServiceDirectory.filePath));
        String servicesJSON = new String(file, Charset.defaultCharset());

        return Stream.of(new Gson().fromJson(servicesJSON, Service[].class))
                .collect(Collectors.toList());
    }

    /**
     * Returns a Java Supplier for updating the local cache (i.e. this.loadServices() ).
     * @return
     */
    private Supplier<List<Service>> getServiceListSupplier(){
        return () -> {
            try {
                return loadServices();
            } catch (IOException e) {
                e.printStackTrace();
                return new ArrayList<Service>(){};
            }
        };
    }

}
