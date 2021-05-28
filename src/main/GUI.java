package main;

import controller.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.DataModel;

public class GUI extends Application {

    private final String title = "PROG ASS - Drop7 - Lars Meeuwsen";
    private final DataModel dataModel = new DataModel();
    private MainController mainController;
    private GameController gameController;
    private BoardController boardController;
    private GameOverController gameOverController;
    private DifficultyController difficultyController;
    private Stage stage;

    void startup(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        this.stage = stage;
        try {

        createControllers();

        // Set stage
        stage.setTitle(title);
        stage.setWidth(540);
        stage.setHeight(900);
        stage.setMinWidth(540);
        stage.setMinHeight(900);
        stage.setScene(new Scene(mainController.getMainScreen()));

        // Show stage
        stage.show();

        // Set icon
            stage.getIcons().add(new Image(getClass().getResource("/images/logo.png").toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeScreen(Pane pane) {
        stage.getScene().setRoot(pane);
    }

    public void stop() {
        Platform.exit();
        System.exit(0);
    }

    private void createControllers() {
        this.mainController = new MainController(this);
        this.gameController = new GameController(this);
        this.boardController = new BoardController(this);
        this.gameOverController = new GameOverController(this);
        this.difficultyController = new DifficultyController(this);
    }

    public MainController getMainController() {
        return mainController;
    }

    public GameController getGameController() {
        return gameController;
    }

    public BoardController getBoardController() {
        return boardController;
    }

    public DataModel getDataModel() {
        return dataModel;
    }

    public DifficultyController getDifficultyController() {
        return difficultyController;
    }

    public GameOverController getGameOverController() {
        return gameOverController;
    }
}
