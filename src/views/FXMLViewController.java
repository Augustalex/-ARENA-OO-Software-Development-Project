package views;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import java.io.IOException;

/**
 * Enables classes that derives from this class to
 * load FXML files more easily.
 */
public abstract class FXMLViewController implements Initializable{

    /**
     * Loads FXML from given FXML file path.
     * @param fxmlPath
     * @return
     * @throws IOException
     */
    public Parent loadFXML(String fxmlPath) throws IOException {
        return loadFromLoader(new FXMLLoader(getClass().getResource("/views/" + fxmlPath)));
    }

    /**
     * Loads FXML from given FXML file path and "Controller callback".
     * @param fxmlPath
     * @param controllerCallback
     * @return
     * @throws IOException
     */
    public Parent loadFXML(String fxmlPath, javafx.util.Callback<java.lang.Class<?>, java.lang.Object> controllerCallback) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/GameInformationView/GameInformationView.fxml"));

        loader.setControllerFactory(controllerCallback);

        return loadFromLoader(loader);
    }

    private Parent loadFromLoader(FXMLLoader loader) throws IOException {
        return loader.load();
    }

    public abstract void closeView();
}
