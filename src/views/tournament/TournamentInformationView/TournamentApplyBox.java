package views.tournament.TournamentInformationView;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import session.AppliedTournaments;
import tournament.ITournament;
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


        //Bind all dimensions to correct dynamic size
        DimensionBinder.bindWidthToPercentageOfContainer(innerPane, 1, this);
        DimensionBinder.bindWidthToPercentageOfContainer(left, 0.5, this);
        DimensionBinder.bindWidthToPercentageOfContainer(right, 0.5, this);

        //Add apply button
        Label label = new Label(tournament.getTournamentMetaInformation().getTourName());
        Button applyButton = new Button("Apply");

        //add all elements
        left.getChildren().add(label);
        right.getChildren().add(applyButton);

        innerPane.setLeft(left);
        innerPane.setRight(right);

        this.getChildren().setAll(innerPane);

        //Set CSS id
        this.setId("tournamentApplyBox");

        /*
                Button action wont work when because of the overlapping ad that, even if removed,
                will not allow things below it to be clicked.
         */

        //Set action event handler
        applyButton.setOnAction(e -> {
            //System.out.println("CLICKED MF!");
            appliedTournaments.applyToTournament(tournament);
        });
    }
}
