package controller;

import main.GUI;
import mouseEvent.onMuteMouseClickEvent;
import mouseEvent.onStartMouseClickEvent;
import view.DifficultyScreen;

public class DifficultyController {

    private final GUI gui;

    public DifficultyController(GUI gui) {
        this.gui = gui;
    }

    public DifficultyScreen getDifficultyScreen() {
        DifficultyScreen difficultyScreen = new DifficultyScreen(gui.getDataModel());

        // Set Mute button
        difficultyScreen.getMuteButton().setOnMouseClicked(new onMuteMouseClickEvent(gui));

        // Set Noob button
        difficultyScreen.getNoobButton().setOnMouseClicked(new onStartMouseClickEvent(gui, 1, difficultyScreen.getNoobButton().getText()));

        // Set Easy button
        difficultyScreen.getEasyButton().setOnMouseClicked(new onStartMouseClickEvent(gui, 2, difficultyScreen.getEasyButton().getText()));

        // Set Normal button
        difficultyScreen.getNormalButton().setOnMouseClicked(new onStartMouseClickEvent(gui, 3, difficultyScreen.getNormalButton().getText()));

        // Set Hard button
        difficultyScreen.getHardButton().setOnMouseClicked(new onStartMouseClickEvent(gui, 4, difficultyScreen.getHardButton().getText()));

        // Set Impossible button
        difficultyScreen.getImpossibleButton().setOnMouseClicked(new onStartMouseClickEvent(gui, 5, difficultyScreen.getImpossibleButton().getText()));

        return difficultyScreen;
    }
}
