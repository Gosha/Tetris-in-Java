package se.liu.ida.geoza435.TDDC69.lab2;

/**
 * Created with IntelliJ IDEA.
 * User: Gosha
 * Date: 2012-09-03
 * Time: 21:55
 * Holds the position (and rotation) of a falling block
 */
public class FallingBlock {
    SquarePos position;
    Poly poly;

    public FallingBlock(SquarePos position, Poly poly) {
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
}
