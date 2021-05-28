package mouseEvent;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import main.GUI;

public class onQuitMouseClickEvent implements EventHandler<MouseEvent> {

    private final GUI gui;

    public onQuitMouseClickEvent(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void handle(MouseEvent e) {
        // Check left click
        if (!e.getButton().equals(MouseButton.PRIMARY))
            return;

        // Play sound
        AudioClip sound = new AudioClip(getClass().getResource("/sounds/click.mp3").toString());
        sound.play(0.5);

        gui.getGameController().stopTimer();
        gui.getGameController().checkHighscore();
        gui.getMainController().startBackgroundMusic();
        gui.changeScreen(gui.getMainController().getMainScreen());
    }
}
