package view;

import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import model.DataModel;

import java.util.ArrayList;

public class BoardPane extends GridPane {

    private final ArrayList<BoardColumnPane> boardColumnPanes = new ArrayList<>();

    public BoardPane(DataModel dataModel) {

        // Set Hgap
        setHgap(8);

        setAlignment(Pos.CENTER);

        // Columns
        for (int column = 0; column < 7; column++) {
            getColumnConstraints().add(new ColumnConstraints(64));
            boardColumnPanes.add(new BoardColumnPane(dataModel, column));
            add(boardColumnPanes.get(column), column, 0);
        }
    }

    public void refreshBoard() {
        // Columns
        for (BoardColumnPane boardColumnPane : boardColumnPanes) {
            boardColumnPane.refreshColumn();
        }
    }

    public ArrayList<BoardColumnPane> getBoardColumnPanes() {
        return boardColumnPanes;
    }
}