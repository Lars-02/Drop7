package model;

import javafx.beans.property.*;

import java.util.TreeMap;

public class DataModel {

    private final int initialValueTimerSecond = 2;
    private GameModel gameModel;
    private final TreeMap<Integer, Integer> highscores = new TreeMap<Integer, Integer>(){{
        put(1, 0);
        put(2, 0);
        put(3, 0);
        put(4, 0);
        put(5, 0);
    }};
    private final BooleanProperty muteBackgroundMusic = new SimpleBooleanProperty();
    private final BooleanProperty cheatMode = new SimpleBooleanProperty();
    private final IntegerProperty highscore = new SimpleIntegerProperty();
    private final IntegerProperty timerSeconds = new SimpleIntegerProperty(initialValueTimerSecond);

    public GameModel getGameModel() {
        return gameModel;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public TreeMap<Integer, Integer> getHighscores() {
        return highscores;
    }

    public int getInitialValueTimerSecond() { return initialValueTimerSecond; }

    public IntegerProperty highscoreProperty() {
        return highscore;
    }

    public IntegerProperty timerSecondsProperty() {
        return timerSeconds;
    }

    public BooleanProperty muteProperty() {
        return muteBackgroundMusic;
    }

    public BooleanProperty cheatModeProperty() {
        return cheatMode;
    }
}
