package se.liu.ida.geoza435.TDDC69.lab2;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Gosha
 * Date: 2012-09-04
 * Time: 23:07
 * Board logic and interface
 */
public class BoardController {
    private Board board;

    public BoardController(Board board) {
        this.board = board;
    }

    public void randomize() {
        SquareColor[] colors = SquareColor.values();
        Random rand = new Random();

        for (int row = 0; row < board.getHeight(); row++) {
            for (int col = 0; col < board.getWidth(); col++) {
                if (rand.nextInt(5) == 0) {
                    board.setSquare(row, col, colors[rand.nextInt(colors.length)]);
                }
            }
        }
    }

    public void step() {
        if (board.fallingBlock == null) {
            return;
        }

        if (CollisionDetector.collision(board, board.fallingBlock, Action.MOVE_DOWN)) {
            board.stickFallingBlock();
            board.fallingBlock = null;
            TetrominoMaker tm = new TetrominoMaker();
            Random rand = new Random();
            board.addBlock(tm.getPoly(rand.nextInt(tm.getNumberOfTypes())));
        } else {
            board.fallingBlock.moveDown();
        }
    }

    public void doAction(Action action) {

        switch (action) {
            case MOVE_DOWN:
                board.moveFallingBlock(Board.Move.DOWN);
                break;
            case MOVE_LEFT:
                if (board.fallingBlock != null && !CollisionDetector.collision(
                        board, board.fallingBlock, Action.MOVE_LEFT)) {
                    board.moveFallingBlock(Board.Move.LEFT);
                }
                break;
            case MOVE_RIGHT:
                if (board.fallingBlock != null && !CollisionDetector.collision(
                        board, board.fallingBlock, Action.MOVE_RIGHT)) {
                    board.moveFallingBlock(Board.Move.RIGHT);
                }
                break;
        }
    }
}

