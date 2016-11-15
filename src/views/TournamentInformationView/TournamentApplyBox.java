package views.TournamentInformationView;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import session.AppliedTournaments;
import session.Session;
import tournament.ITournament;
import tournament.Tournament;
import views.DimensionBinder;

/**
 * Created by Simon on 15/11/2016.
 */
public class TournamentApplyBox extends HBox {

    public TournamentApplyBox(ITournament tournament, AppliedTournaments appliedTournaments){
        BorderPane innerPane = new BorderPane();

        HBox left = new HBox();
        left.setId("tournamentApplyLabelBox");
        HBox right = new HBox();
        right.setId("tournamentApplyButtonBox");

        DimensionBinder.bindWidthToPercentageOfContainer(left, 0.5, this);
        DimensionBinder.bindWidthToPercentageOfContainer(right, 0.5, this);

        Label label = new Label(tournament.getTournamentName());
        Button applyButton = new Button("Apply");

        left.getChildren().add(label);
        right.getChildren().add(applyButton);

        this.getChildren().setAll(left, right);

        this.setId("tournamentApplyBox");

        applyButton.setOnMouseClicked(e -> {
            System.out.println("CLICKED MF!");
            appliedTournaments.applyToTournament(tournament);

        });
    }
}
