package view;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.DataModel;

public class BallsLeftPane extends HBox {

    private final DataModel dataModel;

    public BallsLeftPane(DataModel dataModel) {

        // Save dataModel
        this.dataModel = dataModel;

        // Center
        setAlignment(Pos.CENTER);

        // Set spacing
        setSpacing(5);

        // Refresh ballsLeft
        refreshPane();
    }

    public void refreshPane() {
        // Clear
        getChildren().clear();

        // Get ballsLeft
        final int ballsLeft = dataModel.getGameModel().ballsLeftProperty().get();

        // Add full balls
        for (int balls = 0; balls < ballsLeft; balls++) {
            Circle circle = new Circle(4, Color.WHITE);
            circle.setStroke(Color.WHITE);
            circle.setStrokeWidth(3);
            getChildren().add(circle);
        }


        // Add used balls
        for (int usedBalls = 0; usedBalls < dataModel.getGameModel().getUsedBalls(); usedBalls++) {
            Circle circle = new Circle(4, Color.TRANSPARENT);
            circle.setStroke(Color.WHITE);
            circle.setStrokeWidth(3);
            getChildren().add(circle);
        }
    }
}
