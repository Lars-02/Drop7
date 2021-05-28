package node;

import javafx.animation.ScaleTransition;
import javafx.scene.Cursor;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class Button extends javafx.scene.control.Button {

    private final CornerRadii cornerRadii;

    public Button(String text) {
        this(text, 0, null, 0);
    }

    public Button(String text, int size, Color background) {
        this(text, size, background, 0);
    }

    public Button(String text, int size, Color backgroundColor, int radius) {
        // Set text
        setText(text);
        setTextFill(Color.WHITE);
        setFont(Font.font("Arial", FontWeight.BOLD, size == 0 ? 24 : size));

        // Set prefSize
        setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);

        // Set cornerRadii
        cornerRadii = new CornerRadii(radius == 0 ? 100 : radius);

        // Set background
        setBackground(new Background(new BackgroundFill(backgroundColor == null ? Color.LIME : backgroundColor, cornerRadii, null)));

        // Set hover
        setOnMouseEntered(e -> {
            setCursor(Cursor.HAND);
            setBackground(new Background(new BackgroundFill(backgroundColor == null ? Color.LIME.darker() : backgroundColor.darker(), cornerRadii, null)));
            ScaleTransition transition = new ScaleTransition(Duration.millis(100), this);
            transition.setToX(1.1);
            transition.setToY(1.1);
            transition.play();
        });
        setOnMouseExited(e -> {
            setCursor(Cursor.DEFAULT);
            setBackground(new Background(new BackgroundFill(backgroundColor == null ? Color.LIME : backgroundColor, cornerRadii, null)));
            ScaleTransition transition = new ScaleTransition(Duration.millis(100), this);
            transition.setToX(1);
            transition.setToY(1);
            transition.play();
        });
    }

    public void changeBackground(Color backgroundColor) {

        // Set padding
        setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);

        // Set background
        setBackground(new Background(new BackgroundFill(backgroundColor, cornerRadii, null)));

        // Set hover
        setOnMouseEntered(e -> {
            setCursor(Cursor.HAND);
            setBackground(new Background(new BackgroundFill(backgroundColor.darker(), cornerRadii, null)));
            ScaleTransition transition = new ScaleTransition(Duration.millis(100), this);
            transition.setToX(1.1);
            transition.setToY(1.1);
            transition.play();
        });
        setOnMouseExited(e -> {
            setCursor(Cursor.DEFAULT);
            setBackground(new Background(new BackgroundFill(backgroundColor, cornerRadii, null)));
            ScaleTransition transition = new ScaleTransition(Duration.millis(100), this);
            transition.setToX(1);
            transition.setToY(1);
            transition.play();
        });
    }
}
