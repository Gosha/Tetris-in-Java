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
        this(10, 20);
    }

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new SquareColor[height][width];
        clear();
        this.state = State.PLAYING;
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

    public void removeFullRows () {
        for (int i = getHeight()-1; i >= 0; i--) {
            for (int j = 0; j < getWidth(); j++) {
                if(board[i][j] == null) {
                    break;
                }
                if(j == getWidth()-1) {
                    moveRowsDown(i);
                    i++;
                }
            }
        }
        setChanged();
        notifyObservers();
    }

    private void moveRowsDown (int to) {
        for (int i = to; i > 0; i--) {
            board[i] = board[i-1];
        }
        for (int i = 0; i < getWidth(); i++) {
            board[0][i] = null;
        }
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return this.state;
    }
}
