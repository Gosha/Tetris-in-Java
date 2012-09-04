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
        Board board = new Board(10, 20);
        BoardController boardController = new BoardController(board);

        TetrisFrame frame = new TetrisFrame(board);

        board.clear();
        board.addBlock(tm.getPoly(rand.nextInt(tm.getNumberOfTypes())));
//        board.addBlock(tm.getPoly(0));
        System.out.println(tw.convertToText(board));

        while (true) {
            //board.addAction(Action.MOVE_DOWN);
            switch (rand.nextInt(8)) {
                case 0:
                    boardController.addAction(Action.MOVE_RIGHT);
                    break;
                case 1:
                    boardController.addAction(Action.MOVE_RIGHT);
                case 2:
                    boardController.addAction(Action.MOVE_RIGHT);
                case 3:
                    boardController.addAction(Action.MOVE_RIGHT);
                    break;
                case 4:
                    boardController.addAction(Action.MOVE_LEFT);
                    break;
                case 5:
                    boardController.addAction(Action.MOVE_LEFT);
                case 6:
                    boardController.addAction(Action.MOVE_LEFT);
                case 7:
                    boardController.addAction(Action.MOVE_LEFT);
                    break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boardController.step();
            frame.update(board);
            //System.out.println(tw.convertToText(board));
        }

    }
}
