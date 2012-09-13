package se.liu.ida.geoza435.TDDC69.lab2;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gosha
 * Date: 2012-09-04
 * Time: 02:01
 * Looks for different collisions between the falling piece and the existing pieces and the walls.
 */
public class CollisionDetector {
    private static enum Side {LEFT, RIGHT}

    public static boolean collision(Board board, FallingBlock fallingBlock, Action action) {
        switch (action) {
            case MOVE_LEFT:
                return checkSide(board, fallingBlock, Side.LEFT);
            case MOVE_RIGHT:
                return checkSide(board, fallingBlock, Side.RIGHT);
            case MOVE_DOWN:
                return checkDown(board, fallingBlock);
            case ROTATE_RIGHT:
                return checkRotation(board, fallingBlock, Side.RIGHT);
            case ROTATE_LEFT:
                return checkRotation(board, fallingBlock, Side.LEFT);
        }
        return false;
    }

    private static boolean checkSide(Board board, FallingBlock fallingBlock, Side side) {
        switch (side) {
            case LEFT:
                return checkRelative(board, fallingBlock.position, fallingBlock.getBlocks(), -1, 0);
            case RIGHT:
                return checkRelative(board, fallingBlock.position, fallingBlock.getBlocks(), 1, 0);
        }
        return false;
    }

    private static boolean checkDown(Board board, FallingBlock fallingBlock) {
        return checkRelative(board, fallingBlock.position, fallingBlock.getBlocks(), 0, 1);
    }

    private static boolean checkRelative(Board board, SquarePos position, List<SquarePos> blocks, int x, int y) {
        for (SquarePos pos : blocks) {
            int relxPos = pos.x + position.x + x;
            int relyPos = pos.y + position.y + y;
            if (relyPos > board.getHeight() - 1
                    || relxPos > board.getWidth() - 1
                    || relxPos < 0
                    || board.getSquare(relyPos, relxPos) != null) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkRotation(Board board, FallingBlock fallingBlock, Side side) {
        switch (side) {
            case RIGHT:
                return checkRelative(board, fallingBlock.position, fallingBlock.getNextRotation(FallingBlock.Direction.RIGHT), 0, 0);
            case LEFT:
                return checkRelative(board, fallingBlock.position, fallingBlock.getNextRotation(FallingBlock.Direction.LEFT), 0, 0);
        }
        return false;
    }
}
