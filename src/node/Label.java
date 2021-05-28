package node;

import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Label extends javafx.scene.control.Label {

    public Label() {
        this(null, 0, false);
    }

    public Label(String text) {
        this(text, 0, false);
    }

    public Label(String text, int size) {
        this(text, size, false);
    }

    public Label(String text, int size, boolean bold) {
        // Set text
        setText(text);
        setTextFill(Color.WHITE);
        setFont(Font.font("Arial", bold ? FontWeight.BOLD : FontWeight.NORMAL, size == 0 ? 20 : size));

        // Set set prefSize & Alignment
        setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        setAlignment(Pos.CENTER);
    }
}
