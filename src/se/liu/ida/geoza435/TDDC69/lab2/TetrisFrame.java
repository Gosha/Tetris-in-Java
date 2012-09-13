package se.liu.ida.geoza435.TDDC69.lab2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Gosha
 * Date: 2012-09-04
 * Time: 17:06
 * Shows a GUI of a Tetris Board
 */
public class TetrisFrame extends JFrame {
    private GraphicalViewer graphicalTetris;
    private RandomController randomController;

    private void askQuit() {
        int answer = JOptionPane.showConfirmDialog
                (this, "Do you really want to quit?", "Quit?",
                        JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void createMenuBar() {
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Tja");
        JMenuItem quitButton = new JMenuItem("Hej då");
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                askQuit();
            }
        });
        menu.add(quitButton);
        bar.add(menu);
        this.setJMenuBar(bar);
    }

    public TetrisFrame(final Board board) throws HeadlessException {
        //Frame initialization
        super("Tetris!");
        this.setLayout(new FlowLayout());
        createMenuBar();

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                askQuit();
            }
        });

        //Tetris initialization
        graphicalTetris = new GraphicalViewer(board);
        board.addObserver(graphicalTetris);

        randomController = new RandomController(board);

        final javax.swing.Action controlRandomly = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                randomController.controlSome();
            }
        };

        final javax.swing.Action doOneStep = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                randomController.step();
            }
        };

        final Timer stepTimer = new Timer(1000, doOneStep);
        stepTimer.setCoalesce(true);
        stepTimer.setInitialDelay(0);
        stepTimer.start();

        final Timer randomTimer = new Timer(100, controlRandomly);
        randomTimer.setCoalesce(true);
        //randomTimer.start();

        addKeyListener(new KeyboardController(board));

        //Fire it up!
        this.add(graphicalTetris);
        this.pack();
        this.setVisible(true);
    }
}
