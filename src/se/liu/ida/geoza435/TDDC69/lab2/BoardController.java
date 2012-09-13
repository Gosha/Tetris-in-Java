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
    protected Board board;
    Random rand = new Random();
    TetrominoMaker tm;

    public BoardController(Board board) {
        this.board = board;
        tm = new TetrominoMaker();
    }

    //Used in early development.
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
        if (board.getState() == State.GAME_OVER) {
            board.clear();
            board.setState(State.PLAYING);
        }

        if (board.fallingBlock == null) {
            board.addFallingBlock(tm.getPoly(rand.nextInt(tm.getNumberOfTypes())));
        }

        if (CollisionDetector.collision(board, board.fallingBlock, Action.MOVE_DOWN)) {
            board.stickFallingBlock();
            if (board.fallingBlock.position.y == 1) {
                board.setState(State.GAME_OVER);
            }
            board.fallingBlock = null;
            board.removeFullRows();

        } else {
            board.moveFallingBlock(Board.Move.DOWN);
        }
    }

    public void doAction(Action action) {
        if (board.fallingBlock == null) {
            return;
        }
        switch (action) {
            case MOVE_DOWN:
                if (board.fallingBlock != null && !CollisionDetector.collision(
                        board, board.fallingBlock, Action.MOVE_DOWN)) {
                    board.moveFallingBlock(Board.Move.DOWN);
                }
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
            case ROTATE_RIGHT:
                if (board.fallingBlock != null && !CollisionDetector.collision(
                        board, board.fallingBlock, Action.ROTATE_RIGHT)) {
                    board.moveFallingBlock(Board.Move.ROTATE_RIGHT);
                }
                break;
            case ROTATE_LEFT:
                if (board.fallingBlock != null && !CollisionDetector.collision(
                        board, board.fallingBlock, Action.ROTATE_LEFT)) {
                    board.moveFallingBlock(Board.Move.ROTATE_LEFT);
                }
                break;
            case DROP:
                while (board.fallingBlock != null && !CollisionDetector.collision(
                        board, board.fallingBlock, Action.MOVE_DOWN)) {
                    board.moveFallingBlock(Board.Move.DOWN);

                }
                break;
            case HARD_DROP:
                while (board.fallingBlock != null && !CollisionDetector.collision(
                        board, board.fallingBlock, Action.MOVE_DOWN)) {
                    board.moveFallingBlock(Board.Move.DOWN);
                }
                step();
                break;
        }
    }
}

