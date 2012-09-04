package se.liu.ida.geoza435.TDDC69.lab2;

/**
 * Created with IntelliJ IDEA.
 * User: Gosha
 * Date: 2012-09-01
 * Time: 16:24
 * Handles the board
 */
public class Board {
    private final SquareColor[][] board;
    private final int width;
    private final int height;

    FallingBlock fallingBlock;

    public Board() {
        this(10, 20);
    }

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new SquareColor[height][width];
        clear();
    }

    public SquareColor getSquare(int col, int row) {
        return board[row][col];
    }

    public void clear() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                board[row][col] = null;
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setSquare(int row, int col, SquareColor color) {
        board[row][col] = color;
    }

    public void addBlock(Poly poly) {
        /*if (fallingBlock == null) {
            fallingBlock = new FallingBlock(new SquarePos(getWidth() / 2, 0), poly);
        }*/
        fallingBlock = null;
        fallingBlock = new FallingBlock(new SquarePos(getWidth() / 2, 1), poly);
    }

    public void stickFallingBlock() {
        for (SquarePos pos : fallingBlock.poly.getBlocks()) {
            board[fallingBlock.position.y + pos.y][fallingBlock.position.x + pos.x]
                    = fallingBlock.poly.color;
        }
    }

}
