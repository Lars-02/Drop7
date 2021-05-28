package model;

import java.util.ArrayList;

public class BoardModel {

    private final ArrayList<BallModel> balls = new ArrayList<>();

    public BoardModel() {

    }

    public void addBallToColumn(BallModel ball, int column) {
        if (balls.isEmpty()) {
            ball.placeBall(column, 6);
            balls.add(ball);
            return;
        }
        int row = 6;
        boolean firstColumnBall = true;
        for (BallModel placedBall : balls) {
            if (column == placedBall.getX()) {
                firstColumnBall = false;
                row = Math.min(row, placedBall.getY());
            }
            if (row == 0)
                break;
        }
        ball.placeBall(column, firstColumnBall ? row : row - 1);
        balls.add(ball);
    }

    public ArrayList<BallModel> getColumnBalls(int column) {
        return new ArrayList<BallModel>() {
            {
                for (BallModel ball : balls) {
                    if (column == ball.getX())
                        add(ball);
                }
            }
        };
    }

    public ArrayList<BallModel> getRowBalls(int row) {
        return new ArrayList<BallModel>() {
            {
                for (BallModel ball : balls) {
                    if (row == ball.getY())
                        add(ball);
                }
            }
        };
    }

    public ArrayList<BallModel> getBalls() {
        return balls;
    }
}
