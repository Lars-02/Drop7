package view;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.BallModel;
import model.DataModel;

public class BoardColumnPane extends GridPane {

    private final DataModel dataModel;
    private final int column;

    public BoardColumnPane(DataModel dataModel, int column) {

        // Save gui and column
        this.dataModel = dataModel;
        this.column = column;

        // Set Vgap
        setVgap(8);

        // Rows
        for (int row = 0; row < 7; row++) {
            getRowConstraints().add(new RowConstraints(64));
        }

        // Add onMouse
        setOnMouseExited(e -> {
            setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
        });

        // Refresh column
        refreshColumn();
    }

    protected void refreshColumn() {
        // Clear children
        getChildren().clear();

        // Square background
        for (int row = 0; row < 7; row++) {
            // Set background
            Rectangle backgroundPane = new Rectangle(64, 64);
            backgroundPane.setFill(Color.grayRgb(4, 0.2));
            add(backgroundPane, 0, row);
        }

        // Add balls
        if (!dataModel.getGameModel().getBoardModel().getColumnBalls(column).isEmpty()) {
            for (BallModel ball : dataModel.getGameModel().getBoardModel().getColumnBalls(column)) {
                // Add ballView
                ImageView imageView = new ImageView(new Image(getClass().getResource("/images/" + ball.getValue() + ".png").toString(), 64, 64, true, false));
                StackPane ballPane = new StackPane();
                ballPane.getChildren().add(imageView);
                StackPane.setAlignment(imageView, Pos.CENTER);
                add(ballPane, 0, ball.getY());
            }
        }
    }

    public int getColumn() {
        return column;
    }
}
