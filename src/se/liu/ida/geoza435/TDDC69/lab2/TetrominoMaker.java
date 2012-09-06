package se.liu.ida.geoza435.TDDC69.lab2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gosha
 * Date: 2012-09-01
 * Time: 18:11
 * Makes a list of tetrominos and handles them out
 */
public class TetrominoMaker {
    private List<Poly> polyList; //final?

    public TetrominoMaker() {
        polyList = new ArrayList<Poly>();
        // I
        Poly poly = new Poly(SquareColor.CYAN);
        poly.addSquare(new SquarePos(-2, -1));
        poly.addSquare(new SquarePos(-1, -1));
        poly.addSquare(new SquarePos(0, -1));
        poly.addSquare(new SquarePos(1, -1));
        polyList.add(poly);

        // O
        poly = new Poly(SquareColor.YELLOW);
        poly.addSquare(new SquarePos(-1, -1));
        poly.addSquare(new SquarePos(0, -1));
        poly.addSquare(new SquarePos(-1, 0));
        poly.addSquare(new SquarePos(0, 0));
        polyList.add(poly);

        // T
        poly = new Poly(SquareColor.PURPLE);
        poly.addSquare(new SquarePos(0, -1));
        poly.addSquare(new SquarePos(-1, 0));
        poly.addSquare(new SquarePos(0, 0));
        poly.addSquare(new SquarePos(1, 0));
        polyList.add(poly);

        // S
        poly = new Poly(SquareColor.GREEN);
        poly.addSquare(new SquarePos(-1, -1));
        poly.addSquare(new SquarePos(0, -1));
        poly.addSquare(new SquarePos(0, 0));
        poly.addSquare(new SquarePos(1, 0));
        polyList.add(poly);

        // Z
        poly = new Poly(SquareColor.RED);
        poly.addSquare(new SquarePos(-1, 0));
        poly.addSquare(new SquarePos(0, 0));
        poly.addSquare(new SquarePos(0, -1));
        poly.addSquare(new SquarePos(1, -1));
        polyList.add(poly);

        // J
        poly = new Poly(SquareColor.BLUE);
        poly.addSquare(new SquarePos(0, -1));
        poly.addSquare(new SquarePos(0, 0));
        poly.addSquare(new SquarePos(0, 1));
        poly.addSquare(new SquarePos(-1, 1));
        polyList.add(poly);

        // L
        poly = new Poly(SquareColor.ORANGE);
        poly.addSquare(new SquarePos(0, -1));
        poly.addSquare(new SquarePos(0, 0));
        poly.addSquare(new SquarePos(0, 1));
        poly.addSquare(new SquarePos(1, 1));
        polyList.add(poly);
    }

    public int getNumberOfTypes() {
        return polyList.size();
    }

    public Poly getPoly(int type) {
        return polyList.get(type);
    }
}
