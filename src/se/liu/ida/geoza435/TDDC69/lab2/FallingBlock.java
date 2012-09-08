package se.liu.ida.geoza435.TDDC69.lab2;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gosha
 * Date: 2012-09-03
 * Time: 21:55
 * Holds the position (and rotation) of a falling block
 */
public class FallingBlock {
    enum Direction {LEFT, RIGHT}

    SquarePos position;
    Poly poly;
    int currentRotation;

    public FallingBlock(SquarePos position, Poly poly) {
        this.currentRotation = 0;
        this.position = position;
        this.poly = poly;
    }

    public void moveDown() {
        position.y += 1;
    }

    public void moveLeft() {
        position.x -= 1;
    }

    public void moveRight() {
        position.x += 1;
    }

    public void rotate(Direction dir) {
        switch (dir) {
            case RIGHT:
                this.currentRotation = (currentRotation + 1) % 4;
                break;
            case LEFT:
                this.currentRotation = (currentRotation - 1) % 4;
                break;
        }
    }

    public List<SquarePos> getNextRotation(Direction dir) {
        switch (dir) {
            case RIGHT:
                return getBlocksInRotation((currentRotation + 1) % 4);
            case LEFT:
                return getBlocksInRotation((currentRotation + 1) % 4);
        }
        return getBlocks();
    }

    public List<SquarePos> getBlocks() {
        return poly.getBlocks(currentRotation);
    }

    public List<SquarePos> getBlocksInRotation(int rotation) {
        return poly.getBlocks(rotation);
    }
}
