package controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import main.GUI;
import model.BallModel;
import model.DataModel;
import model.GameModel;
import mouseEvent.onMuteMouseClickEvent;
import mouseEvent.onQuitMouseClickEvent;
import mouseEvent.onRestartMouseClickEvent;
import view.BoardColumnPane;
import view.GameScreen;

import java.util.Random;

public class GameController {

    private final GUI gui;
    private final DataModel dataModel;
    private GameScreen gameScreen;
    private Timeline timeline;

    public GameController(GUI gui) {
        this.gui = gui;
        this.dataModel = gui.getDataModel();
    }

    public GameScreen getGameScreen() {
        if (gameScreen == null) {
            gameScreen = new GameScreen(dataModel);

            // Change playBall
            gameScreen.setOnKeyPressed(e -> {
                switch (e.getCode()) {
                    case DIGIT9:
                        setNewPlayBall(-1);
                        break;
                    case DIGIT0:
                        setNewPlayBall(0);
                        break;
                    case DIGIT1:
                        setNewPlayBall(1);
                        break;
                    case DIGIT2:
                        setNewPlayBall(2);
                        break;
                    case DIGIT3:
                        setNewPlayBall(3);
                        break;
                    case DIGIT4:
                        setNewPlayBall(4);
                        break;
                    case DIGIT5:
                        setNewPlayBall(5);
                        break;
                    case DIGIT6:
                        setNewPlayBall(6);
                        break;
                    case DIGIT7:
                        setNewPlayBall(7);
                        break;
                }
            });

            // Create muteButtonClick
            gameScreen.getOptionButtonsPane().getMuteButton().setOnMouseClicked(new onMuteMouseClickEvent(gui));

            // Create restartButtonClick
            gameScreen.getOptionButtonsPane().getRestartButton().setOnMouseClicked(new onRestartMouseClickEvent(gui));

            // Create quitButtonClick
            gameScreen.getOptionButtonsPane().getQuitButton().setOnMouseClicked(new onQuitMouseClickEvent(gui));

            // Create boardColumnPane hover and click
            for (BoardColumnPane boardColumnPane : gameScreen.getBoardPane().getBoardColumnPanes()) {
                // Change playBall location on hover
                boardColumnPane.setOnMouseEntered(e -> {
                    boardColumnPane.setBackground(new Background(new BackgroundFill(Color.grayRgb(0, 0.2), null, null)));
                    setHoverColumn(boardColumnPane.getColumn());
                });
                // Initialize place ball
                boardColumnPane.setOnMouseClicked(e -> {
                    if (!e.getButton().equals(MouseButton.PRIMARY))
                        return;
                    AudioClip sound = new AudioClip(getClass().getResource("/sounds/dropball.mp3").toString());
                    sound.play(0.5);
                    gui.getBoardController().placePlayBall(boardColumnPane.getColumn());
                });
            }

            // Set gameOver
            dataModel.getGameModel().placementTimeLeftProperty().addListener((obj, old, current) -> {
                double progress = current == null ? 0 : current.doubleValue();
                if (progress <= 0) {
                    gameOver("Time's up!");
                } else if (progress < 0.4) {
                    gameScreen.getProgressBar().setStyle("-fx-accent: #E71500;");
                } else if (progress < 0.6) {
                    gameScreen.getProgressBar().setStyle("-fx-accent: #E7E300;");
                } else {
                    gameScreen.getProgressBar().setStyle("-fx-accent: #003EE7;");
                }
            });

            // Refresh ballsLeft on placement
            dataModel.getGameModel().ballsLeftProperty().addListener((obj, old, current) -> {
                gameScreen.getBallsLeftPane().refreshPane();
            });

            // Refresh playBall on placement
            dataModel.getGameModel().playBallProperty().addListener((obj, old, current) -> {
                gameScreen.getPlayBallPane().refreshPane();
            });

            // Check ballsLeft change
            dataModel.getGameModel().ballsLeftProperty().addListener((obj, old, current) -> {
                if (current.intValue() == 0)
                    dataModel.getGameModel().levelUp();
            });

            // Check level up change
            dataModel.getGameModel().levelProperty().addListener((obj, old, current) -> {
                if (!dataModel.getGameModel().getBoardModel().getRowBalls(0).isEmpty()) {
                    gameOver("No free row for level up");
                    return;
                }
                AudioClip sound = new AudioClip(getClass().getResource("/sounds/levelup.mp3").toString());
                sound.play(0.7);
                gui.getBoardController().addRowOfBalls();
            });
        }
        return gameScreen;
    }

    public void createNewGame(int difficulty, String difficultyString) {

        // Clear gameScreen
        gameScreen = null;

        // Create new game
        dataModel.setGameModel(new GameModel(difficulty, difficultyString));

        // Get highScore of difficulty
        dataModel.highscoreProperty().set(dataModel.getHighscores().get(difficulty));

        // Set new playBall
        setNewPlayBall();
    }

    protected void setNewPlayBall() {
        GameModel gameModel = dataModel.getGameModel();
        Random rnd = new Random();
        if (rnd.nextInt(20) + 1 <= gameModel.getDifficulty()) {
            gameModel.playBallProperty().set(new BallModel(-1));
            return;
        }
        if (rnd.nextInt(20) + 1 <= gameModel.getDifficulty()) {
            int difficultValue = Math.max(1, rnd.nextInt(8) + 1 - (gameModel.getBoardModel().getBalls().size() / 49 * 6 + 1));
            gameModel.playBallProperty().set(new BallModel(difficultValue));
            return;
        }
        gameModel.playBallProperty().set(new BallModel(rnd.nextInt(7) + 1));
    }

    protected void setNewPlayBall(int value) {
        if (dataModel.cheatModeProperty().get())
            dataModel.getGameModel().playBallProperty().setValue(new BallModel(value));
    }

    protected void setHoverColumn(int column) {
        gameScreen.getPlayBallPane().setHoverColumn(column);
    }

    protected void addScore(int breaks) {

        // Get chain
        int chain = dataModel.getGameModel().getChain();

        // Get score property
        IntegerProperty score = dataModel.getGameModel().scoreProperty();

        // Calculate point earned
        int points = (int) Math.round((8 - (15.21 * chain)) + (13.03 * Math.pow(chain, 2)) + (1.07 * Math.pow(chain, 3)));

        // Set new score
        score.set(score.get() + points * breaks);
    }

    public boolean checkHighscore() {

        // Check for cheat mode
        if (dataModel.cheatModeProperty().get())
            return false;

        // Get gameModel
        GameModel gameModel = dataModel.getGameModel();

        // Check if score is 0
        if (gameModel.scoreProperty().get() == 0)
            return false;


        // Check for higher score
        if (dataModel.getHighscores().get(gameModel.getDifficulty()) != null && dataModel.getHighscores().get(gameModel.getDifficulty()) > gameModel.scoreProperty().get())
            return false;

        // Renew highScore
        dataModel.getHighscores().put(gameModel.getDifficulty(), gameModel.scoreProperty().get());
        dataModel.highscoreProperty().set(gameModel.scoreProperty().get());
        return true;
    }

    public void stopTimer() {
        if (timeline != null)
            timeline.stop();
    }

    public void startTimer() {

        // Check if game is already over
        if (dataModel.getGameModel().getGameOverReason() != null)
            return;

        // Get placementMillisLeftProperty
        DoubleProperty placementTimer = dataModel.getGameModel().placementTimeLeftProperty();

        // Create timeline
        timeline = new Timeline(
                new KeyFrame(Duration.millis(dataModel.timerSecondsProperty().get() * 1000), new KeyValue(placementTimer, 0)),
                new KeyFrame(Duration.millis(0), new KeyValue(placementTimer, 1))
        );

        // Play timeLine
        timeline.play();
    }

    protected void gameOver(String reason) {
        // Stop timer
        stopTimer();

        // Stop background music
        gui.getMainController().stopBackgroundMusic();

        // Set reason
        dataModel.getGameModel().setGameOverReason(reason);

        // Set highScore
        dataModel.getGameModel().setNewHighscore(checkHighscore());

        // Change screen
        gui.changeScreen(gui.getGameOverController().getGameOverScreen());
    }

    public void refreshBoard() {
        gameScreen.getBoardPane().refreshBoard();
    }
}
