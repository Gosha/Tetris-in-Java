package se.liu.ida.geoza435.TDDC69.lab2;

import javax.swing.*;
import java.awt.*;

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

    public TetrisFrame(Board board) throws HeadlessException {
        super("Tetris!");
        tw = new TextViewer();
        textArea = new JTextArea(board.getHeight() + 2, board.getWidth() + 2);
        textArea.setText(tw.convertToText(board));
        textArea.setFont(new Font("Lucida Console", Font.PLAIN, 19));
        this.add(textArea);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void update(Board board) {
        textArea.setText(tw.convertToText(board));
    }
}
