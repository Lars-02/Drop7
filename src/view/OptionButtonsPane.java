package view;

import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import model.DataModel;
import node.Button;

public class OptionButtonsPane extends GridPane {

    private final Button muteButton = new Button(null, 18, Color.TRANSPARENT);
    private final Button restartButton = new Button("Restart", 18, Color.TRANSPARENT);
    private final Button quitButton = new Button("Quit", 18, Color.TRANSPARENT);

    public OptionButtonsPane(DataModel dataModel) {

        // Columns and rows
        int columns = 3;
        int rows = 1;
        for (int x = 0; x < columns; x++) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(100.0 / columns);
            getColumnConstraints().add(col);
        }
        for (int x = 0; x < rows; x++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100.0 / rows);
            getRowConstraints().add(row);
        }

        // Align
        setAlignment(Pos.CENTER);

        // Create restart button
        add(restartButton, 0, 0);

        // Create quit button
        quitButton.setMinSize(50, 20);
        add(quitButton, 1, 0);

        // Create mute/unmute button
        dataModel.muteProperty().addListener((obj, old, current) -> {
            muteButton.setText(current ? "Unmute" : "Mute");
        });
        muteButton.setText(dataModel.muteProperty().get() ? "Unmute" : "Mute");
        add(muteButton, 2, 0);
    }

    public Button getMuteButton() {
        return muteButton;
    }

    public Button getRestartButton() {
        return restartButton;
    }

    public Button getQuitButton() {
        return quitButton;
    }
}
