package se.liu.ida.geoza435.TDDC69.lab2;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Gosha
 * Date: 2012-09-06
 * Time: 04:23
 * Controls the tetrominos randomly
 */
public class RandomController extends BoardController {
    Random rand;
    public RandomController(Board board) {
        super(board);
        rand = new Random();
    }

    public void controlSome () {
        switch (rand.nextInt(8)) {
            case 0:
                doAction(Action.MOVE_RIGHT);
            case 1:
                doAction(Action.MOVE_RIGHT);
            case 2:
                doAction(Action.MOVE_RIGHT);
            case 3:
                doAction(Action.MOVE_RIGHT);
                break;
            case 4:
                doAction(Action.MOVE_LEFT);
            case 5:
                doAction(Action.MOVE_LEFT);
            case 6:
                doAction(Action.MOVE_LEFT);
            case 7:
                doAction(Action.MOVE_LEFT);
                break;
        }
    }
}
