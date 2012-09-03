package se.liu.ida.geoza435.TDDC69.lab2;


import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gosha
 * Date: 2012-09-01
 * Time: 17:50
 * holds information about a polymino
 */
public class Poly {
    private List<SquarePos> blocks;

    public SquareColor color;

    public Poly() {
        blocks = new ArrayList<SquarePos>();
    }

    public Poly(SquareColor color) {
        this();
        this.color = color;
    }

    public void addSquare(SquarePos block) {
        blocks.add(block);
    }

    public List<SquarePos> getBlocks() {
        return blocks;
    }
}
