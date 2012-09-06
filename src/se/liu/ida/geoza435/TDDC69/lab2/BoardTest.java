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
        final RandomController randomController = new RandomController(board);

        final TetrisFrame frame = new TetrisFrame(board);

        //board.addObserver(tw);

        board.clear();
        board.addBlock(tm.getPoly(rand.nextInt(tm.getNumberOfTypes())));

        final javax.swing.Action doOneStep = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                randomController.step();
                frame.update(board);
            }
        };

        final Timer clockTimer = new Timer(100, doOneStep);
        clockTimer.setCoalesce(true);

        clockTimer.start();

        while (true) {
            randomController.controlSome();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            frame.update(board);
        }

    }
}
