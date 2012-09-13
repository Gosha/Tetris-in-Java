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
    private State state;
    private final SquareColor[][] board;
    private final int width;
    private final int height;

    public static enum Move {LEFT, DOWN, RIGHT, ROTATE_RIGHT, ROTATE_LEFT}

    FallingBlock fallingBlock;

    public Board() {
        this(10, 22);
    }

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new SquareColor[height][width];
        clear();
        this.state = State.PLAYING;
    }

    public SquareColor getSquare(int row, int col) {
        return board[row][col];
    }

    public void clear() {
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

    public void setSquare(int row, int col, SquareColor color) {
        board[row][col] = color;
        setChanged();
        notifyObservers();
    }

    public void addFallingBlock(Poly poly) {
        fallingBlock = null;
        fallingBlock = new FallingBlock(new SquarePos(getWidth() / 2, 1), poly);
        setChanged();
        notifyObservers();
    }

    public void stickFallingBlock() {
        for (SquarePos pos : fallingBlock.getBlocks()) {
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
            case ROTATE_RIGHT:
                fallingBlock.rotate(FallingBlock.Direction.RIGHT);
                break;
            case ROTATE_LEFT:
                fallingBlock.rotate(FallingBlock.Direction.LEFT);
                break;
        }
        setChanged();
        notifyObservers();
    }

    public void removeFullRows() {
        for (int row = getHeight() - 1; row >= 0; row--) {
            for (int col = 0; col < getWidth(); col++) {
                if (board[row][col] == null) {
                    break;
                }
                if (col == getWidth() - 1) {
                    moveRowsDown(row);
                    row++;
                }
            }
        }
        setChanged();
        notifyObservers();
    }

    private void moveRowsDown(int to) {
        for (int row = to; row > 0; row--) {
            board[row] = board[row - 1];
        }

        board[0] = new SquareColor[width];

        for (int col = 0; col < getWidth(); col++) {
            board[0][col] = null;
        }
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return this.state;
    }
}
