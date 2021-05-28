package controller;

import javafx.scene.input.MouseButton;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import main.GUI;
import model.DataModel;
import mouseEvent.onMuteMouseClickEvent;
import view.MainScreen;

public class MainController {

    private final DataModel dataModel;
    private final GUI gui;
    private final MediaPlayer backgroundMusic;
    private MainScreen mainScreen;

    public MainController(GUI gui) {
        this.gui = gui;
        this.dataModel = gui.getDataModel();

        // Play background music
        backgroundMusic = new MediaPlayer(new Media(getClass().getResource("/sounds/background.mp3").toString()));
        backgroundMusic.setVolume(0.2);
        backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundMusic.play();

        // Toggle backgroundMusic
        dataModel.muteProperty().addListener((obj, old, current) -> {
            if (current)
                backgroundMusic.pause();
            else
                backgroundMusic.play();
        });
    }


    public MainScreen getMainScreen() {

        // Create mainScreen
        if (mainScreen == null) {
            mainScreen = new MainScreen(dataModel);

            // Bind timerSlider
            dataModel.timerSecondsProperty().bindBidirectional(mainScreen.getSliderValueProperty());

            // Create muteButtonClick
            mainScreen.getMuteButton().setOnMouseClicked(new onMuteMouseClickEvent(gui));

            // Create newGameButtonClick
            mainScreen.getNewGameButton().setOnMouseClicked(e -> {
                if (!e.getButton().equals(MouseButton.PRIMARY))
                    return;
                AudioClip sound = new AudioClip(getClass().getResource("/sounds/click.mp3").toString());
                sound.play(0.5);
                gui.changeScreen(gui.getDifficultyController().getDifficultyScreen());
            });

            // Create cheatModeButtonClick
            mainScreen.getCheatModeButton().setOnMouseClicked(e -> {
                if (!e.getButton().equals(MouseButton.PRIMARY))
                    return;
                AudioClip sound = new AudioClip(getClass().getResource("/sounds/click.mp3").toString());
                sound.play(0.5);
                dataModel.cheatModeProperty().set(!dataModel.cheatModeProperty().get());
                dataModel.timerSecondsProperty().set(dataModel.getInitialValueTimerSecond());
            });
        }
        return mainScreen;
    }

    public void stopBackgroundMusic() {
        backgroundMusic.pause();
    }

    public void startBackgroundMusic() {
        if (!dataModel.muteProperty().get())
            backgroundMusic.play();
    }
}
