package se.liu.ida.geoza435.TDDC69.lab2;

import javax.swing.*;
import java.awt.event.ActionEvent;
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

        final TextViewer tw = new TextViewer();
        final TetrominoMaker tm = new TetrominoMaker();
        final Board board = new Board(10, 20);
        final BoardController boardController = new BoardController(board);

        final TetrisFrame frame = new TetrisFrame(board);

        //board.addObserver(tw);

        board.clear();
        board.addBlock(tm.getPoly(rand.nextInt(tm.getNumberOfTypes())));
//        board.addBlock(tm.getPoly(0));
        //System.out.println(tw.convertToText(board));

        final javax.swing.Action doOneStep = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardController.step();
                frame.update(board);
            }
        };

        final Timer clockTimer = new Timer(100, doOneStep);
        clockTimer.setCoalesce(true);

        clockTimer.start();

        while (true) {
            //board.addAction(Action.MOVE_DOWN);
            switch (rand.nextInt(8)) {
                case 0:
                    boardController.doAction(Action.MOVE_RIGHT);
                case 1:
                    boardController.doAction(Action.MOVE_RIGHT);
                case 2:
                    boardController.doAction(Action.MOVE_RIGHT);
                case 3:
                    boardController.doAction(Action.MOVE_RIGHT);
                    break;
                case 4:
                    boardController.doAction(Action.MOVE_LEFT);
                case 5:
                    boardController.doAction(Action.MOVE_LEFT);
                case 6:
                    boardController.doAction(Action.MOVE_LEFT);
                case 7:
                    boardController.doAction(Action.MOVE_LEFT);
                    break;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //boardController.step();
            frame.update(board);
            //System.out.println(tw.convertToText(board));
        }

    }
}
