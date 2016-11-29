package views;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;

/**
 * Enables classes that derives from this class to
 * load FXML files more easily.
 */
public abstract class FXMLViewController implements Initializable{

    public static final String standardDirectory = "/views/";

    /**
     * Loads FXML from given FXML file path.
     * @param fxmlPath
     * @return
     * @throws IOException
     */
    public Parent loadFXML(String fxmlPath) throws IOException {
        return loadFromLoader(new FXMLLoader(getClass().getResource(FXMLViewController.standardDirectory + fxmlPath)));
    }

    /**
     * Loads FXML from given FXML file path and "Controller callback".
     * @param fxmlPath
     * @param controllerCallback
     * @return
     * @throws IOException
     */
    public Parent loadFXML(String fxmlPath, javafx.util.Callback<java.lang.Class<?>, java.lang.Object> controllerCallback) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLViewController.standardDirectory + fxmlPath));

        loader.setControllerFactory(controllerCallback);

        return loadFromLoader(loader);
    }

    /**
     * Loads FXML from a URL resource path to an FXML file as well as a "Controller Callback".
     * @param fxmlURL
     * @param controllerCallback
     * @return
     * @throws IOException
     */
    public Parent loadFXML(URL fxmlURL, javafx.util.Callback<java.lang.Class<?>, java.lang.Object> controllerCallback) throws IOException{
        FXMLLoader loader = new FXMLLoader(fxmlURL);

        loader.setControllerFactory(controllerCallback);

        return loadFromLoader(loader);
    }

    private Parent loadFromLoader(FXMLLoader loader) throws IOException {
        return loader.load();
    }

    public abstract void closeView();
}
