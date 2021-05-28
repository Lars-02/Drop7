package model;

import java.util.Random;

public class BallModel {

    private int x;
    private int y;
    private int value;
    private final boolean brokenBall = false;

    public BallModel(int value) {
        this.value = value;
    }

    public BallModel(int value, int x, int y) {
        this.value = value;
        this.x = x;
        this.y = y;
    }

    public void placeBall(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void moveBallUp() {
        y--;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void breakBall() {
        if (value == - 1) {
            value = 0;
            return;
        }
        if (value == 0)
            value = new Random().nextInt(7) + 1;
    }
}
