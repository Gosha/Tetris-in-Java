package se.liu.ida.geoza435.TDDC69.lab2;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Gosha
 * Date: 2012-09-01
 * Time: 17:25
 * Test the board
 */
public class BoardTest {

    public static void main(String[] args) {

        Random rand = new Random();

        TextViewer tw = new TextViewer();
        TetrominoMaker tm = new TetrominoMaker();
        Board board = new Board(15, 15);

        TetrisFrame frame = new TetrisFrame(board);

        board.clear();
        board.addBlock(tm.getPoly(rand.nextInt(tm.getNumberOfTypes())));
//        board.addBlock(tm.getPoly(0));
        System.out.println(tw.convertToText(board));

        while (true) {
            //board.addAction(Action.MOVE_DOWN);
            switch (rand.nextInt(8)) {
                case 0:
                    board.addAction(Action.MOVE_RIGHT);
                    break;
                case 1:
                    board.addAction(Action.MOVE_RIGHT);
                case 2:
                    board.addAction(Action.MOVE_RIGHT);
                case 3:
                    board.addAction(Action.MOVE_RIGHT);
                    break;
                case 4:
                    board.addAction(Action.MOVE_LEFT);
                    break;
                case 5:
                    board.addAction(Action.MOVE_LEFT);
                case 6:
                    board.addAction(Action.MOVE_LEFT);
                case 7:
                    board.addAction(Action.MOVE_LEFT);
                    break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            board.step();
            frame.update(board);
            System.out.println(tw.convertToText(board));
        }

    }
}
