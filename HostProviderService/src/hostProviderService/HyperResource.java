package hostProviderService;

import hostProviderService.Host;

/**
 * Used for services that has a direct link from
 * which they are accessible. This link usually includes
 * only the URI. To get the host, one need to retrieve the
 * Host object in which the host and its address is stored.
 */
public interface HyperResource {

    String getResourceAddress();

    Host getHostInformation();
}
