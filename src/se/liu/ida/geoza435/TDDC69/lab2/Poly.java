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
    private List<ArrayList<SquarePos>> blocks;
    private int currentRotation = 0; // 0 - 3, 0 is initial
    public SquareColor color;

    enum Direction {LEFT, RIGHT}

    public Poly() {
        blocks = new ArrayList<ArrayList<SquarePos>>();
        for (int i = 0; i < 4; i++) {
            blocks.add(new ArrayList<SquarePos>());
        }
    }

    public Poly(SquareColor color) {
        this();
        this.color = color;
    }

    public void addSquare(SquarePos block) {
        blocks.get(0).add(block);
    }

    public List<SquarePos> getBlocks() {
        return blocks.get(currentRotation);
    }

    public void rotate(Direction dir) {
        switch (dir) {
            case RIGHT:
                currentRotation = (currentRotation + 1) % 4;
                break;
            case LEFT:
                currentRotation = (currentRotation - 1) % 4;
                break;
        }
    }

    public void createRotations() {
        int boundingBox = getBoundingBoxSize();
        for (int i = 1; i < 4; i++) {
            for (SquarePos pos : blocks.get(i - 1)) {
                // Magic!
                blocks.get(i).add(new SquarePos(pos.y * -1 - 1 + boundingBox % 2, pos.x));
            }
        }
    }

    private int getBoundingBoxSize() {
        List<SquarePos> currentBlocks = blocks.get(currentRotation);
        int maxY = currentBlocks.get(0).y;
        int minY = currentBlocks.get(0).y;
        int maxX = currentBlocks.get(0).x;
        int minX = currentBlocks.get(0).x;
        for (int i = 1; i < currentBlocks.size(); i++) {
            maxX = Math.max(currentBlocks.get(i).x, maxX);
            maxY = Math.max(currentBlocks.get(i).y, maxY);
            minX = Math.min(currentBlocks.get(i).x, minX);
            minY = Math.min(currentBlocks.get(i).y, minY);
        }
        int width = Math.abs(minX - maxX) + 1;
        int height = Math.abs(minY - maxY) + 1;
        return (width > height) ? width : height;
    }
}
