package view;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import model.DataModel;
import model.GameModel;
import node.Button;
import node.Label;

public class GameOverScreen extends GridPane {

    private final Button restartButton = new Button("Restart");
    private final Button mainMenuButton = new Button("Main Menu");

    public GameOverScreen(DataModel dataModel) {

        // Get gameModel
        GameModel gameModel = dataModel.getGameModel();

        // Columns and rows
        ColumnConstraints column20 = new ColumnConstraints();
        ColumnConstraints column60 = new ColumnConstraints();
        column20.setPercentWidth(20);
        column60.setPercentWidth(60);
        getColumnConstraints().addAll(column20, column60, column20);

        RowConstraints row5 = new RowConstraints();
        RowConstraints row10 = new RowConstraints();
        RowConstraints row20 = new RowConstraints();
        RowConstraints row25 = new RowConstraints();
        row5.setPercentHeight(5);
        row10.setPercentHeight(10);
        row20.setPercentHeight(20);
        row25.setPercentHeight(25);
        getRowConstraints().addAll(row10, row10, row10, row10, row25, row10, row5, row10, row20);

        // Align
        setAlignment(Pos.CENTER);

        // Set background
        setStyle("-fx-background-color: linear-gradient(to bottom right, #ff2626, #ffba24)");


        // Add score label
        VBox scorePane = new VBox();
        scorePane.getChildren().addAll(new Label(String.valueOf(gameModel.scoreProperty().get()), 50, true), new Label("POINTS", 28));
        add(scorePane, 1, 1);

        // Add new highScore label
        if (dataModel.getGameModel().isNewHighscore())
            add(new Label("New Highscore", 32, true), 1, 2);

        // Add reason label
        add(new Label(dataModel.getGameModel().getGameOverReason(), 28), 1, 3);

        // Add infoPane
        VBox infoPane = new VBox();
        infoPane.setSpacing(8);
        // Add high score label
        infoPane.getChildren().add(new Label("Best - " + dataModel.highscoreProperty().get(), 18));
        // Add level label
        infoPane.getChildren().add(new Label("Level - " + dataModel.getGameModel().levelProperty().get(), 18));
        // Add highest chain label
        infoPane.getChildren().add(new Label("Highest chain - " + dataModel.getGameModel().getHighestChain(), 18));
        // Add mode label
        infoPane.getChildren().add(new Label("Mode - " + dataModel.getGameModel().getDifficultyString(), 18));
        // Add cheat mode label
        if (dataModel.cheatModeProperty().get())
            infoPane.getChildren().add(new Label("Cheat mode", 18));
        add(infoPane, 1, 4);

        // Create restart button
        add(restartButton, 1, 5);

        // Main menu
        add(mainMenuButton, 1, 7);
    }

    public Button getRestartButton() {
        return restartButton;
    }

    public Button getMainMenuButton() {
        return mainMenuButton;
    }
}
