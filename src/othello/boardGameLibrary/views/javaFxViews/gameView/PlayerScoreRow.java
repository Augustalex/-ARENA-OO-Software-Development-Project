package boardGameLibrary.views.javaFxViews.gameView;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Created by August on 2016-10-26.
 */
public class PlayerScoreRow extends HBox {

    private final static String labelStyleClass = "playerStatsLabel";

    private Label nameLabel = new Label();
    private Label scoreLabel = new Label();

    public PlayerScoreRow(){
        setLabelStyleClass();

        this.getChildren().addAll(nameLabel, scoreLabel);
    }

    private void setLabelStyleClass(){
        nameLabel.getStyleClass().add(PlayerScoreRow.labelStyleClass);
        scoreLabel.getStyleClass().add(PlayerScoreRow.labelStyleClass);
    }

    public void setName(String text){
        this.nameLabel.setText(text);
    }

    public Label getNameLabel(){
        return this.nameLabel;
    }

    public Label getScoreLabel(){
        return this.scoreLabel;
    }

    public void setScore(int score){
        this.scoreLabel.setText(Integer.toString(score));
    }
}
