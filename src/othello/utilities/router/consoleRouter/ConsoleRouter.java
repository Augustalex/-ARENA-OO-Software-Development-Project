package utilities.router.consoleRouter;

import utilities.router.Router;

import java.util.Map;

/**
 * Created by August on 2016-10-20.
 */
public class ConsoleRouter extends Router{

    @Override
    public void routeToView(String viewId, Map data) {
        System.out.println("Console Route was called with: " + viewId + ", and a map: " + data.toString());

        ConsoleViewController viewController = ConsoleViewController.create(viewId, data);
        viewController.loadView();
    }
}
