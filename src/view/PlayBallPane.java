package view;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import model.DataModel;

public class PlayBallPane extends GridPane {

    private final DataModel dataModel;
    private int column = 3;
    private String playBallValue;

    public PlayBallPane(DataModel dataModel) {

        // Save gui
        this.dataModel = dataModel;

        // Set value
        playBallValue = String.valueOf(this.dataModel.getGameModel().playBallProperty().getValue().getValue());

        // Set Hgap
        setHgap(8);

        // Columns and rows
        for (int i = 0; i < 7; i++) {
            getColumnConstraints().add(new ColumnConstraints(64));
        }

        // Align
        setAlignment(Pos.CENTER);

        // Add playBallView
        setPlayBallView();
    }

    public void refreshPane() {
        // Set value
        playBallValue = String.valueOf(dataModel.getGameModel().playBallProperty().getValue().getValue());

        // Clear children
        getChildren().clear();

        // Add playBallView
        setPlayBallView();
    }

    public void setHoverColumn(int column) {
        this.column = column;
        refreshPane();
    }

    private void setPlayBallView() {
        // Add playBallView
        ImageView imageView = new ImageView(new Image(getClass().getResource("/images/" + playBallValue + ".png").toString(), 64, 64, true, false));
        StackPane imagePane = new StackPane();
        imagePane.getChildren().add(imageView);
        StackPane.setAlignment(imageView, Pos.CENTER);
        add(imagePane, column, 0);
    }
}
