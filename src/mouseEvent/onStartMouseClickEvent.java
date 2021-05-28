package mouseEvent;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import main.GUI;

public class onStartMouseClickEvent implements EventHandler<MouseEvent> {

    private final GUI gui;
    private final int difficulty;
    private final String difficultyString;

    public onStartMouseClickEvent(GUI gui, int difficulty, String difficultyString) {
        this.gui = gui;
        this.difficulty = difficulty;
        this.difficultyString = difficultyString;
    }

    @Override
    public void handle(MouseEvent e) {

        // Check left click
        if (!e.getButton().equals(MouseButton.PRIMARY))
            return;

        // Play sound
        AudioClip sound = new AudioClip(getClass().getResource("/sounds/click.mp3").toString());
        sound.play(0.5);

        // Create game
        gui.getGameController().createNewGame(difficulty, difficultyString);

        // Change screen
        gui.changeScreen(gui.getGameController().getGameScreen());
    }
}
