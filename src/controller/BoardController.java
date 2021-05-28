package controller;

import main.GUI;
import model.BallModel;
import model.BoardModel;
import model.DataModel;
import model.GameModel;

import java.util.ArrayList;

public class BoardController {

    private final GUI gui;
    private final DataModel dataModel;

    public BoardController(GUI gui) {
        this.gui = gui;
        this.dataModel = gui.getDataModel();

    }

    public void placePlayBall(int x) {


        // Get gameModel
        GameModel gameModel = dataModel.getGameModel();

        // Get board
        BoardModel board = gameModel.getBoardModel();

        // Get gameController
        GameController gameController = gui.getGameController();

        // Stop timer
        gameController.stopTimer();

        // Check if column is full
        if (board.getColumnBalls(x) == null || board.getColumnBalls(x).size() == 7)
            return;

        // Place ball
        board.addBallToColumn(gameModel.playBallProperty().get(), x);
        gameController.setNewPlayBall();

        // Check board
        checkBoard();

        // Check if board is empty
        if (board.getBalls().isEmpty())
            gameModel.scoreProperty().set(gameModel.scoreProperty().get() + 100);

        // Decrease ballsLeft
        gameModel.ballsLeftProperty().set(gameModel.ballsLeftProperty().get() - 1);

        // Check for gameOver
        if (board.getBalls().size() >= 49) {
            gameController.gameOver("Your board was full");
            return;
        }

        // Reset chain
        dataModel.getGameModel().setChain(1);

        // Refresh board
        gameController.refreshBoard();

        // Start timer
        gameController.startTimer();
    }

    public void checkBoard() {

        // Get board
        BoardModel boardModel = gui.getDataModel().getGameModel().getBoardModel();

        // Reset chain
        dataModel.getGameModel().setChain(1);

        // Make loop until no more breaks
        while (true) {

            // Break ball list
            ArrayList<BallModel> breakBalls = new ArrayList<>();

            // Check columns
            for (int column = 0; column < 7; column++) {
                final int columnSize = boardModel.getColumnBalls(column).size();
                for (BallModel ballModel : boardModel.getColumnBalls(column)) {
                    if (ballModel.getValue() == columnSize)
                        breakBalls.add(ballModel);
                }
            }

            // Check rows
            for (int row = 0; row < 7; row++) {
                int rowLength = 0;
                for (int column = 0; column < 7; column++) {
                    boolean empty = true;
                    for (BallModel ballModel : boardModel.getRowBalls(row)) {
                        if (ballModel.getX() != column)
                            continue;
                        rowLength++;
                        empty = false;
                        break;
                    }
                    // Check short rows
                    if (rowLength != 0 && empty) {
                        for (int columnToCheck = column - rowLength; columnToCheck < column; columnToCheck++) {
                            for (BallModel ballModel : boardModel.getRowBalls(row)) {
                                if (breakBalls.contains(ballModel) || ballModel.getX() != columnToCheck || ballModel.getValue() != rowLength)
                                    continue;
                                breakBalls.add(ballModel);
                                break;
                            }
                        }
                        rowLength = 0;
                    }
                }
                // Check last row
                if (rowLength != 0) {
                    for (int columnToCheck = 7 - rowLength; columnToCheck < 7; columnToCheck++) {
                        for (BallModel ballModel : boardModel.getRowBalls(row)) {
                            if (ballModel.getX() == columnToCheck) {
                                if (ballModel.getValue() == rowLength) {
                                    breakBalls.add(ballModel);
                                }
                                break;
                            }
                        }
                    }
                }
            }

            // Check for full and half
            for (BallModel ballModel : boardModel.getBalls()) {
                if (ballModel.getValue() != 0 && ballModel.getValue() != -1)
                    continue;
                // Check column breaks
                for (BallModel otherBallModel : boardModel.getColumnBalls(ballModel.getX())) {
                    // Check if ball is broken
                    if (!breakBalls.contains(otherBallModel))
                        continue;
                    if (ballModel.getY() - 1 == otherBallModel.getY() || ballModel.getY() + 1 == otherBallModel.getY()) {
                        ballModel.breakBall();
                        break;
                    }
                }
                if (breakBalls.contains(ballModel))
                    continue;
                // Check row breaks
                for (BallModel otherBallModel : boardModel.getRowBalls(ballModel.getY())) {
                    // Check if ball is broken
                    if (!breakBalls.contains(otherBallModel))
                        continue;
                    if (ballModel.getX() - 1 == otherBallModel.getX() || ballModel.getX() + 1 == otherBallModel.getX()) {
                        ballModel.breakBall();
                        break;
                    }
                }
            }

            // Break the balls
            for (BallModel breakBall : breakBalls) {
                boardModel.getBalls().remove(breakBall);
            }

            // Check if there are broken balls
            if (breakBalls.size() == 0)
                break;

            // Check gravity
            checkGravity();

            // Add to score
            gui.getGameController().addScore(breakBalls.size());
            dataModel.getGameModel().setChain(dataModel.getGameModel().getChain() + 1);
        }
    }

    public void checkGravity() {

        // Get board
        BoardModel boardModel = gui.getDataModel().getGameModel().getBoardModel();

        // Check columns
        for (int column = 0; column < 7; column++) {
            for (int row = 5; row >= 0; row--) {
                for (BallModel ballModel : boardModel.getColumnBalls(column)) {
                    if (ballModel.getY() != row)
                        continue;
                    int lowestEmpty = 6;
                    for (BallModel otherBallModel : boardModel.getColumnBalls(column)) {
                        if (ballModel.equals(otherBallModel) || otherBallModel.getY() < ballModel.getY())
                            continue;
                        lowestEmpty = Math.max(Math.min(lowestEmpty, otherBallModel.getY() - 1), ballModel.getY());
                    }
                    ballModel.setY(lowestEmpty);
                }
            }
        }
    }

    public void addRowOfBalls() {

        // Get board
        BoardModel boardModel = gui.getDataModel().getGameModel().getBoardModel();

        // Move all balls up
        for (BallModel ballModel : boardModel.getBalls()) {
            ballModel.moveBallUp();
        }

        // Add new balls
        for (int column = 0; column < 7; column++) {
            boardModel.getBalls().add(new BallModel(-1, column, 6));
        }

        // Check board
        checkBoard();
    }
}
