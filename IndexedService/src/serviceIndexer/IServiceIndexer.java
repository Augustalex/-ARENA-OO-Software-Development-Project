package serviceIndexer;

import indexedService.IIndexedService;
import javafx.beans.property.BooleanProperty;
import rest.Delivery;

import javax.management.ServiceNotFoundException;
import java.util.List;

/**
 * Created by August on 2016-11-29.
 */
public interface IServiceIndexer {

    IIndexedService getService(int index) throws ServiceNotFoundException;

    Delivery<Boolean> scaleUp(int numberOfInstances);

    IIndexedService[] getAllServices();

    int serviceCount();

    IIndexedService newService();

}
