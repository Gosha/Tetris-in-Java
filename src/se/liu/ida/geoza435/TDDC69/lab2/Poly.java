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

    enum Direction {LEFT, RIGHT}

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

    public void rotate(Direction dir) {
        int boundingBox = getBoundingBoxSize();
        for (SquarePos pos : blocks) {
            SquarePos tmpPos = new SquarePos(pos.x, pos.y);
            pos.y = tmpPos.x;
            pos.x = tmpPos.y * -1 - 1 + boundingBox % 2;
        }
    }

    private int getBoundingBoxSize() {
        int maxY = blocks.get(0).y;
        int minY = blocks.get(0).y;
        int maxX = blocks.get(0).x;
        int minX = blocks.get(0).x;
        for (int i = 1; i < blocks.size(); i++) {
            maxX = Math.max(blocks.get(i).x, maxX);
            maxY = Math.max(blocks.get(i).y, maxY);
            minX = Math.min(blocks.get(i).x, minX);
            minY = Math.min(blocks.get(i).y, minY);
        }
        int width = Math.abs(minX - maxX) + 1;
        int height = Math.abs(minY - maxY) + 1;
        return (width > height) ? width : height;
    }
}
