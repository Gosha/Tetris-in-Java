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
    private JTextArea textArea;
    private TextViewer tw;

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

    public TetrisFrame(Board board) throws HeadlessException {
        super("Tetris!");
        tw = new TextViewer();
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
    }

    public void update(Board board) {
        textArea.setText(tw.convertToText(board));
    }
}
