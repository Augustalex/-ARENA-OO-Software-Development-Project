package serviceCenter;

import serviceCenter.reviewServices.Reviewer;

/**
 * Interface for global services. Implementations will mostly use some kind
 * of an online server for distributing messages (commands) to different services.
 * As well as managing the returned information from the services.
 *
 * The preferred way of managing
 */
public abstract class ServiceCenter {

    private static ServiceCenter serviceCenter = null;

    public static boolean isSet(){
        return ServiceCenter.serviceCenter != null;
    }

    public static void setApplicationServiceCenter(ServiceCenter serviceCenter){
        ServiceCenter.serviceCenter = serviceCenter;
    }

    public static ServiceCenter getServiceCenter(){
        return ServiceCenter.serviceCenter;
    }

    public abstract Reviewer getTournamentConfigurationReviewer();

}
