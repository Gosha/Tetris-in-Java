package se.liu.ida.geoza435.TDDC69.lab2;

/**
 * Created with IntelliJ IDEA.
 * User: Gosha
 * Date: 2012-09-04
 * Time: 02:01
 * Looks for different collisions between the falling piece and the existing pieces and the walls.
 */
public class CollisionDetector {
    private static enum Side {LEFT, RIGHT}

    private static boolean checkSide(Board board, FallingBlock fallingBlock, Side side) {
        switch (side) {
            case LEFT:
                return checkRelative(board, fallingBlock, -1, 0);
            case RIGHT:
                return checkRelative(board, fallingBlock, 1, 0);
        }
        return false;
    }

    private static boolean checkDown(Board board, FallingBlock fallingBlock) {
        return checkRelative(board, fallingBlock, 0, 1);
    }

    private static boolean checkRelative(Board board, FallingBlock fallingBlock, int x, int y) {
        for (SquarePos pos : fallingBlock.poly.getBlocks()) {
            int relxPos = pos.x + fallingBlock.position.x + x;
            int relyPos = pos.y + fallingBlock.position.y + y;
            if (relyPos > board.getHeight() - 1
                    || relxPos > board.getWidth() - 1
                    || relxPos < 0
                    || board.getSquare(relxPos, relyPos) != null) {
                return true;
            }
        }
        return false;
    }

    public static boolean collision(Board board, FallingBlock fallingBlock, Action action) {
        switch (action) {
            case MOVE_LEFT:
                return checkSide(board, fallingBlock, Side.LEFT);
            case MOVE_RIGHT:
                return checkSide(board, fallingBlock, Side.RIGHT);
            case MOVE_DOWN:
                return checkDown(board, fallingBlock);
            // And cases for rotate
        }
        return false;
    }
}
