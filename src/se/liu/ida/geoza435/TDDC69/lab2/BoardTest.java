package se.liu.ida.geoza435.TDDC69.lab2;

/**
 * Created with IntelliJ IDEA.
 * User: Gosha
 * Date: 2012-09-01
 * Time: 17:25
 * Test the board
 */
public class BoardTest {

    public static void main(String[] args) {

        final Board board = new Board(10, 22);

        new TetrisFrame(board);

    }
}
