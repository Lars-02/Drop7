package view;

import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.DataModel;
import node.Button;
import node.Label;

public class MainScreen extends GridPane {

    private final Button cheatModeButton = new Button("Cheat Mode Off", 18, Color.ORANGERED);
    private final Button muteButton = new Button(null, 18, Color.TRANSPARENT);
    private final Button newGameButton = new Button("New Game");
    Slider timerSlider = new Slider();

    public MainScreen(DataModel dataModel) {

        // Columns and rows
        ColumnConstraints column20 = new ColumnConstraints();
        ColumnConstraints column60 = new ColumnConstraints();
        column20.setPercentWidth(20);
        column60.setPercentWidth(60);
        getColumnConstraints().addAll(column20, column60, column20);

        RowConstraints row5 = new RowConstraints();
        RowConstraints row10 = new RowConstraints();
        RowConstraints row30 = new RowConstraints();
        row5.setPercentHeight(5);
        row10.setPercentHeight(10);
        row30.setPercentHeight(30);
        getRowConstraints().addAll(row10, row10, row10, row30, row5, row10, row5, row10, row10);

        // Align
        setAlignment(Pos.CENTER);

        // Set background
        setStyle("-fx-background-color: linear-gradient(to bottom right, #191970, #4169E1)");

        // Create mute/unmute button
        dataModel.muteProperty().addListener((obj, old, current) -> {
            muteButton.setText(current ? "Unmute" : "Mute");
        });
        muteButton.setText(dataModel.muteProperty().get() ? "Unmute" : "Mute");
        add(muteButton, 0, 0);

        // Create New Game button
        add(newGameButton, 1, 2);

        // High score pane
        VBox highscorePane = new VBox();
        highscorePane.setSpacing(8);
        // Blank label
        Label fill = new Label();
        // Add high score labels
        Label noob = new Label("Noob - 0", 18);
        Label easy = new Label("Easy- 0", 18);
        Label normal = new Label("Normal - 0", 18);
        Label hard = new Label("Hard - 0", 18);
        Label impossible = new Label("Impossible - 0", 18);

        dataModel.highscoreProperty().addListener((obj, old, current) -> {
            noob.setText("Noob - " + dataModel.getHighscores().get(1));
            easy.setText("Easy - " + dataModel.getHighscores().get(2));
            normal.setText("Normal - " + dataModel.getHighscores().get(3));
            hard.setText("Hard - " + dataModel.getHighscores().get(4));
            impossible.setText("Impossible - " + dataModel.getHighscores().get(5));
        });
        highscorePane.getChildren().addAll(fill, new Label("Highscores", 24, true), noob, easy, normal, hard, impossible);
        add(highscorePane, 1, 3);

        // Add time label
        Label timeLabel = new Label();
        timeLabel.textProperty().bind(dataModel.timerSecondsProperty().asString("%d Seconds"));
        timeLabel.setVisible(false);
        add(timeLabel, 1, 4);

        // Add time slider
        timerSlider.setValue(dataModel.timerSecondsProperty().get());
        timerSlider.setMin(0);
        timerSlider.setMax(10);
        timerSlider.setVisible(false);
        timerSlider.setSnapToTicks(true);
        timerSlider.setMajorTickUnit(1);
        timerSlider.setMinorTickCount(0);
        timerSlider.setShowTickMarks(true);
        timerSlider.setPrefSize(Double.MAX_VALUE, 25);
        timerSlider.setPadding(new Insets(0, 40, 0, 40));
        add(timerSlider, 1, 5);

        // Bind cheatMode
        dataModel.cheatModeProperty().addListener((obj, old, current) -> {
            // Bind cheatModeButton
            cheatModeButton.textProperty().set(current ? "Cheat Mode On" : "Cheat Mode Off");
            cheatModeButton.changeBackground(current ? Color.LIME : Color.ORANGERED);
            // Bind timeLabel & timerSlider
            timeLabel.setVisible(current);
            timerSlider.setVisible(current);
        });
        add(cheatModeButton, 1, 6);
    }

    public Button getCheatModeButton() {
        return cheatModeButton;
    }

    public Button getMuteButton() {
        return muteButton;
    }

    public Button getNewGameButton() {
        return newGameButton;
    }

    public DoubleProperty getSliderValueProperty() {
        return timerSlider.valueProperty();
    }
}
