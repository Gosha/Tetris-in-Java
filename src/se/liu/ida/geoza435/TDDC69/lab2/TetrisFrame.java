package se.liu.ida.geoza435.TDDC69.lab2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Gosha
 * Date: 2012-09-04
 * Time: 17:06
 * Shows a GUI of a Tetris Board
 */
public class TetrisFrame extends JFrame {
    private JTextArea textArea;
    final private TetrominoMaker tm = new TetrominoMaker();
    final private TextViewer tw = new TextViewer();
    Random rand = new Random();
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
        super("Tetris!");
        textArea = new JTextArea(board.getHeight() + 2, board.getWidth() + 2);
        textArea.setText(tw.convertToText(board));
        textArea.setFont(new Font("Lucida Console", Font.PLAIN, 19));
        this.setLayout(new BorderLayout());
        this.add(textArea);

        createMenuBar();

        this.pack();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                askQuit();
            }
        });

        this.setVisible(true);
        randomController = new RandomController(board);

        board.clear();
        board.addBlock(tm.getPoly(rand.nextInt(tm.getNumberOfTypes())));


        final javax.swing.Action controlRandomly = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                randomController.controlSome();
                update(board);
            }
        };

        final javax.swing.Action doOneStep = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                randomController.step();
                update(board);
            }
        };

        final Timer stepTimer = new Timer(10, doOneStep);
        stepTimer.setCoalesce(true);
        stepTimer.start();

        final Timer randomTimer = new Timer(10, controlRandomly);
        randomTimer.setCoalesce(true);
        randomTimer.start();

    }

    public void update(Board board) {
        textArea.setText(tw.convertToText(board));
    }
}
