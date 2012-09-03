package se.liu.ida.geoza435.TDDC69.lab2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Gosha
 * Date: 2012-09-01
 * Time: 16:24
 * Handles the board
 */
public class Board {
    private final SquareColor[][] board;
    private final int width;
    private final int height;

    private Queue<Action> actionQueue;

    FallingBlock fallingBlock;

    public Board() {
        this(10, 20);
    }

    public Board(int width, int height) {
        actionQueue = new LinkedList<Action>();
        this.width = width;
        this.height = height;
        this.board = new SquareColor[height][width];
        clear();
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
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setSquare(int row, int col, SquareColor color) {
        board[row][col] = color;
    }

    public void randomize() {
        SquareColor[] colors = SquareColor.values();
        Random rand = new Random();

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (rand.nextInt(5) == 0) {
                    setSquare(row, col, colors[rand.nextInt(colors.length)]);
                }
            }
        }
    }

    public void addBlock(Poly poly) {
        /*if (fallingBlock == null) {
            fallingBlock = new FallingBlock(new SquarePos(getWidth() / 2, 0), poly);
        }*/
        fallingBlock = null;
        fallingBlock = new FallingBlock(new SquarePos(getWidth() / 2, 1), poly);
    }

    public void step() {
        if (fallingBlock == null) {
            return;
        }

        fallingBlock.moveDown();

        if (willCollide(fallingBlock.poly, fallingBlock.position)) {
            stickFallingBlock();
            fallingBlock = null;
            TetrominoMaker tm = new TetrominoMaker();
            Random rand = new Random();
            addBlock(tm.getPoly(rand.nextInt(tm.getNumberOfTypes())));
        }

        Action action;
        while (!actionQueue.isEmpty()) {
            action = actionQueue.remove();
            switch (action) {
                case MOVE_DOWN:
                    fallingBlock.moveDown();
                    break;
                case MOVE_LEFT:
                    fallingBlock.moveLeft();
                    break;
                case MOVE_RIGHT:
                    fallingBlock.moveRight();
                    break;
            }
        }
    }

    private boolean willCollide(Poly poly, SquarePos posOnScreen) {
        for (SquarePos pos : poly.getBlocks()) {
            int relxPos = pos.x + posOnScreen.x;
            int relyPos = pos.y + posOnScreen.y + 1;
            if (relyPos > getHeight() - 1
                    || board[relyPos][relxPos] != null) {
                return true;
            }
        }
        return false;
    }

    private void stickFallingBlock() {
        for (SquarePos pos : fallingBlock.poly.getBlocks()) {
            board[fallingBlock.position.y + pos.y][fallingBlock.position.x + pos.x]
                    = fallingBlock.poly.color;
        }
    }

    public void addAction(Action action) {
        actionQueue.add(action);
    }
}
