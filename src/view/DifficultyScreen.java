package view;

import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import model.DataModel;
import node.Button;

public class DifficultyScreen extends GridPane {

    private final Button muteButton = new Button(null, 18, Color.TRANSPARENT);
    private final Button noobButton = new Button("Noob");
    private final Button easyButton = new Button("Easy");
    private final Button normalButton = new Button("Normal");
    private final Button hardButton = new Button("Hard");
    private final Button impossibleButton = new Button("Impossible");

    public DifficultyScreen(DataModel dataModel) {

        // Columns and rows
        ColumnConstraints column20 = new ColumnConstraints();
        ColumnConstraints column60 = new ColumnConstraints();
        column20.setPercentWidth(20);
        column60.setPercentWidth(60);
        getColumnConstraints().addAll(column20, column60, column20);

        RowConstraints row5 = new RowConstraints();
        RowConstraints row10 = new RowConstraints();
        RowConstraints row15 = new RowConstraints();
        row5.setPercentHeight(5);
        row10.setPercentHeight(10);
        row15.setPercentHeight(15);
        getRowConstraints().addAll(row10, row5, row10, row5, row10, row5, row10, row5, row10, row5, row10, row15);

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

        // Create Noob button
        add(noobButton, 1, 2);

        // Create Easy button
        add(easyButton, 1, 4);

        // Create Normal button
        add(normalButton, 1, 6);

        // Create Hard button
        add(hardButton, 1, 8);

        // Create Impossible button
        add(impossibleButton, 1, 10);
    }

    public Button getNoobButton() {
        return noobButton;
    }

    public Button getEasyButton() {
        return easyButton;
    }

    public Button getNormalButton() {
        return normalButton;
    }

    public Button getHardButton() {
        return hardButton;
    }

    public Button getImpossibleButton() {
        return impossibleButton;
    }

    public Button getMuteButton() { return muteButton; }
}
