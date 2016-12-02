package views.tournament.extendedTournamentView;

import arena.tournament.ITournament;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import views.FXMLViewController;

import java.io.IOException;

/**
 * Created by Simon on 02/12/2016.
 */
public class TournamentLobbyInitiator {

    public void newLobbyView(ITournament tournament) {
        try {
            System.out.println("new window opened");

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/tournament/extendedTournamentView/ExtendedTournamentView.fxml"));
            loader.setControllerFactory(c -> new ExtendedTournamentViewController(tournament));
            Parent parent = loader.load();
            stage.setScene(new Scene(parent, 1600, 600));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            System.out.println("---------------------error opening window--------------------");
            e.printStackTrace();
        }
    }
}
