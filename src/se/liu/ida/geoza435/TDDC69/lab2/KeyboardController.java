package se.liu.ida.geoza435.TDDC69.lab2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created with IntelliJ IDEA.
 * User: Gosha
 * Date: 2012-09-08
 * Time: 21:47
 * Handles keyboard events
 */
public class KeyboardController extends BoardController implements KeyListener {
    TetrisFrame frame;

    public KeyboardController(Board board, TetrisFrame frame) {
        super(board);
        this.frame = frame;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                doAction(Action.MOVE_LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                doAction(Action.MOVE_RIGHT);
                break;
            case KeyEvent.VK_DOWN:
                doAction(Action.MOVE_DOWN);
                break;
            case KeyEvent.VK_UP:
                doAction(Action.ROTATE_RIGHT);
                break;
        }
        frame.update(board);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
