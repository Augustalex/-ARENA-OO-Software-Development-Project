package views.systemLogin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Johan on 2016-12-02.
 */
public class SystemLoginViewController implements Initializable {
    @FXML
    private VBox loginWindow;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Text newPassword;

    @FXML
    private Button guestButton;

    @FXML
    private Button loginButton;

    @FXML
    private Button cancelButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginWindow.setPadding(new Insets(20,500,0,15));
        newPassword.setUnderline(true);
        setNewPasswordEvents();
    }

    private void setNewPasswordEvents() {
        newPassword.setOnMouseEntered(e->{newPassword.setStyle("-fx-font-weight: bold;");});
        newPassword.setOnMouseExited(e->{newPassword.setStyle("-fx-font-weight: normal");});
        newPassword.setOnMouseClicked(e->setNewPasswordDialog());
    }

    private void setNewPasswordDialog() {
        TextInputDialog passwordDialog = new TextInputDialog("E-mail or username");
        passwordDialog.setTitle("Change password");
        passwordDialog.setHeaderText("Enter registered E-mail or username\n" +
                "and a new password will be sent to you.");
        Optional<String> result = passwordDialog.showAndWait();
    }
}
