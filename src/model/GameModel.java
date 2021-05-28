package model;

import javafx.beans.property.*;

public class GameModel {
    // Constants
    private final int baseBallsLeft = 30;
    private final int minimumBallsLeft = 5;
    private final int lessBallsFromDifficulty = 3;
    private final int loseBallsFromLevel = 2;
    private final int scoreFromLevel = 7000;

    // Variables
    private final int starterBallsLeft;
    private final int difficulty;
    private final String difficultyString;
    private final BoardModel boardModel = new BoardModel();
    private int chain = 0;
    private boolean newHighscore;
    private int highestChain;
    private String gameOverReason;
    private final IntegerProperty score = new SimpleIntegerProperty();
    private final IntegerProperty level = new SimpleIntegerProperty(1);
    private final IntegerProperty ballsLeft = new SimpleIntegerProperty();
    private final DoubleProperty placementTimeLeft = new SimpleDoubleProperty();
    private final ObjectProperty<BallModel> playBall = new SimpleObjectProperty<>();

    public GameModel(int difficulty, String difficultyString) {
        this.difficulty = difficulty;
        this.difficultyString = difficultyString;
        this.starterBallsLeft = baseBallsLeft + lessBallsFromDifficulty - difficulty * lessBallsFromDifficulty;
        ballsLeft.set(starterBallsLeft);
    }

    public void levelUp() {
        level.set(level.get() + 1);
        ballsLeft.set(Math.max(minimumBallsLeft, starterBallsLeft - ((level.get() - 1) * loseBallsFromLevel)));
        scoreProperty().set(scoreProperty().get() + scoreFromLevel);
    }

    public int getUsedBalls() {
        return Math.max(minimumBallsLeft, starterBallsLeft - ((level.get() - 1) * loseBallsFromLevel)) - ballsLeft.get();
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getDifficultyString() {
        return difficultyString;
    }

    public IntegerProperty ballsLeftProperty() {
        return ballsLeft;
    }

    public IntegerProperty levelProperty() {
        return level;
    }

    public IntegerProperty scoreProperty() {
        return score;
    }

    public ObjectProperty<BallModel> playBallProperty() {
        return playBall;
    }

    public BoardModel getBoardModel() {
        return boardModel;
    }

    public int getChain() {
        return chain;
    }

    public void setChain(int chain) {
        this.chain = chain;
        highestChain = Math.max(highestChain, chain - 1);
    }

    public DoubleProperty placementTimeLeftProperty() {
        return placementTimeLeft;
    }

    public String getGameOverReason() {
        return gameOverReason;
    }

    public void setGameOverReason(String gameOverReason) {
        this.gameOverReason = gameOverReason;
    }

    public boolean isNewHighscore() {
        return newHighscore;
    }

    public void setNewHighscore(boolean newHighscore) {
        this.newHighscore = newHighscore;
    }

    public int getHighestChain() {
        return highestChain;
    }
}
