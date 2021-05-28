package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.*;
import model.DataModel;
import node.Label;

public class GameScreen extends GridPane {

    private final OptionButtonsPane optionButtonsPane;
    private final PlayBallPane playBallPane;
    private final BoardPane boardPane;
    private final BallsLeftPane ballsLeftPane;
    private final ProgressBar progressBar = new ProgressBar();

    public GameScreen(DataModel dataModel) {

        // Columns and rows
        ColumnConstraints column20 = new ColumnConstraints();
        column20.setPercentWidth(20);
        getColumnConstraints().addAll(column20, column20, column20, column20, column20);

        RowConstraints row5 = new RowConstraints();
        RowConstraints row10 = new RowConstraints();
        RowConstraints row60 = new RowConstraints();
        row5.setPercentHeight(5);
        row10.setPercentHeight(10);
        row60.setPercentHeight(60);
        getRowConstraints().addAll(row10, row10, row5, row5, row10, row60);

        // Align
        setAlignment(Pos.CENTER);

        // Set background
        setStyle("-fx-background-color: linear-gradient(to bottom right, #191970, #4169E1)");

        // Add buttons
        optionButtonsPane = new OptionButtonsPane(dataModel);
        add(optionButtonsPane, 0, 0, 3, 1);

        // Add info
        VBox infoPane = new VBox();
        infoPane.setSpacing(5);
        // Blank label
        Label fill = new Label();
        // Add mode & best labels
        infoPane.getChildren().addAll(
                fill,
                new Label("Mode - " + dataModel.getGameModel().getDifficultyString(), 18),
                new Label("Best - " + dataModel.highscoreProperty().get(), 18)
        );
        // Add cheat mode label
        if (dataModel.cheatModeProperty().get())
            infoPane.getChildren().add(new Label("Cheat mode", 18));
        add(infoPane, 3, 0, 2, 1);

        // Add score label
        VBox scorePane = new VBox();
        Label scoreLabel = new Label(null, 50, true);
        scoreLabel.textProperty().bind(dataModel.getGameModel().scoreProperty().asString());
        scorePane.getChildren().addAll(scoreLabel, new Label("POINTS"));
        add(scorePane, 0, 1, 5, 1);

        // Add level counter
        ballsLeftPane = new BallsLeftPane(dataModel);
        add(ballsLeftPane, 0, 2, 5, 1);

        // Add level
        Label levelLabel = new Label(null, 20, true);
        levelLabel.textProperty().bind(dataModel.getGameModel().levelProperty().asString("LEVEL %d"));
        add(levelLabel, 2, 3);

        // Add progressBar
        progressBar.progressProperty().bind(dataModel.getGameModel().placementTimeLeftProperty());
        progressBar.setPrefSize(Double.MAX_VALUE, 25);
        progressBar.setPadding(new Insets(0, 40, 0, 40));
        add(progressBar, 3, 3, 2, 1);

        // Add playBall
        playBallPane = new PlayBallPane(dataModel);
        add(playBallPane, 0, 4, 5, 1);

        // Add board
        boardPane = new BoardPane(dataModel);
        add(boardPane, 0, 5, 5, 1);
    }

    public OptionButtonsPane getOptionButtonsPane() {
        return optionButtonsPane;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public PlayBallPane getPlayBallPane() {
        return playBallPane;
    }

    public BallsLeftPane getBallsLeftPane() {
        return ballsLeftPane;
    }

    public BoardPane getBoardPane() {
        return boardPane;
    }
}
