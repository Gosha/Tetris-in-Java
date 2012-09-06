package se.liu.ida.geoza435.TDDC69.lab2;

import java.util.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: Gosha
 * Date: 2012-09-01
 * Time: 16:24
 * Handles the board
 */
public class Board extends Observable {
    private final SquareColor[][] board;
    private final int width;
    private final int height;

    public static enum Move {LEFT, DOWN, RIGHT}

    FallingBlock fallingBlock;

    public Board() {
        this(10, 20);
    }

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new SquareColor[height][width];
        clear();
    }

    public synchronized SquareColor getSquare(int col, int row) {
        return board[row][col];
    }

    public synchronized void clear() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                board[row][col] = null;
            }
        }
        setChanged();
        notifyObservers();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public synchronized void setSquare(int row, int col, SquareColor color) {
        board[row][col] = color;
        setChanged();
        notifyObservers();
    }

    public synchronized void addBlock(Poly poly) {
        fallingBlock = null;
        fallingBlock = new FallingBlock(new SquarePos(getWidth() / 2, 1), poly);
        setChanged();
        notifyObservers();
    }

    public synchronized void stickFallingBlock() {
        for (SquarePos pos : fallingBlock.poly.getBlocks()) {
            board[fallingBlock.position.y + pos.y][fallingBlock.position.x + pos.x]
                    = fallingBlock.poly.color;
        }
        setChanged();
        notifyObservers();
    }

    public void moveFallingBlock(Move direction) {
        switch (direction) {
            case DOWN:
                fallingBlock.moveDown();
                break;
            case LEFT:
                fallingBlock.moveLeft();
                break;
            case RIGHT:
                fallingBlock.moveRight();
                break;
        }
        setChanged();
        notifyObservers();
    }

}
