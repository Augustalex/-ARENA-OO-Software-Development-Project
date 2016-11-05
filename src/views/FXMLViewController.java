package views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

/**
 * Enables classes that derrives from this class to
 * load FXML files more easily.
 */
public abstract class FXMLViewController {

    public Parent loadFXML(String fxmlPath) throws IOException {
        return FXMLLoader.load(getClass().getResource("/views/" + fxmlPath));
    }

    public Parent loadFXML(String fxmlPath, javafx.util.Callback<java.lang.Class<?>, java.lang.Object> controllerCallback) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/GameInformationView/GameInformationView.fxml"));

        loader.setControllerFactory(controllerCallback);

        return loader.load();
    }
}
