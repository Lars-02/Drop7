package controller;

import javafx.scene.media.AudioClip;
import main.GUI;
import model.DataModel;
import mouseEvent.onQuitMouseClickEvent;
import mouseEvent.onRestartMouseClickEvent;
import view.GameOverScreen;

public class GameOverController {

    private final GUI gui;
    private final DataModel dataModel;

    public GameOverController(GUI gui) {
        this.gui = gui;
        this.dataModel = gui.getDataModel();
    }

    public GameOverScreen getGameOverScreen() {
        GameOverScreen gameOverScreen = new GameOverScreen(dataModel);

        // Play sound
        AudioClip sound = new AudioClip(getClass().getResource("/sounds/gameover.mp3").toString());
        sound.play(0.3);

        // Set mainMenu button
        gameOverScreen.getMainMenuButton().setOnMouseClicked(new onQuitMouseClickEvent(gui));

        // Set restart button
        gameOverScreen.getRestartButton().setOnMouseClicked(new onRestartMouseClickEvent(gui));

        return gameOverScreen;
    }
}
