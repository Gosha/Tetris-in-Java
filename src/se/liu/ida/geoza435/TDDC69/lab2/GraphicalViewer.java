package se.liu.ida.geoza435.TDDC69.lab2;

import javax.swing.*;
import java.awt.*;
//import java.util.EnumMap;

/**
 * Created with IntelliJ IDEA.
 * User: Gosha
 * Date: 2012-09-08
 * Time: 16:27
 * Shows a graphic view of a {@code Board}.
 */
public class GraphicalViewer extends JComponent {
    Board board;
    final static int SQUARE_SIZE = 20;
    //EnumMap<SquareColor, Color> squareColors;

    public Color getSquareColor(SquareColor sColor) {
        if (sColor == null) {
            return new Color(20, 20, 20);
        }
        switch (sColor) {
            case CYAN:
                return new Color(76, 111, 124);
            case RED:
                return new Color(124, 82, 83);
            case BLUE:
                return new Color(109, 93, 109);
            case GREEN:
                return new Color(76, 109, 92);
            case YELLOW:
                return new Color(127, 125, 76);
            case PURPLE:
                return new Color(123, 76, 104);
            case ORANGE:
                return new Color(126, 106, 82);
            default:
                return new Color(255, 255, 255);
        }
    }

    public GraphicalViewer(Board board) {
        this.board = board;

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(SQUARE_SIZE * board.getWidth(), SQUARE_SIZE * (board.getHeight() - 2));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.fill(new Rectangle(getPreferredSize()));
        drawBackground(g2, board);
        for (int i = 2; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (board.getSquare(j, i) != null)
                    paintBlock(g2,
                            new Point(j * SQUARE_SIZE, (i - 2) * SQUARE_SIZE),
                            getSquareColor(board.getSquare(j, i)));
            }
        }
        if (board.fallingBlock != null) {
            for (SquarePos pos : board.fallingBlock.getBlocks()) {
                int xPos = pos.x * SQUARE_SIZE + SQUARE_SIZE * board.fallingBlock.position.x;
                int yPos = pos.y * SQUARE_SIZE + SQUARE_SIZE * (board.fallingBlock.position.y - 2);
                paintBlock(g2,
                        new Point(xPos, yPos),
                        getSquareColor(board.fallingBlock.poly.color));
            }
        }
    }

    private void drawBackground(Graphics2D g2, Board board) {


        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setPaint(new GradientPaint(
                new Point(0, 0),
                getSquareColor(null),
                new Point(
                        board.getWidth() * SQUARE_SIZE,
                        0),
                getSquareColor(null).darker()
        ));

        g2.fill(new Rectangle(getPreferredSize()));

        for (int i = 0; i < board.getHeight() - 2; i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                g2.setColor(getSquareColor(null).brighter());
                g2.draw(new Rectangle(j * SQUARE_SIZE, i * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE));
            }
        }
    }

    private void paintBlock(Graphics2D g2, Point position, Color color) {
        g2.setColor(color);
        if (!color.equals(getSquareColor(null))) {
            g2.setPaint(new GradientPaint(
                    new Point(position.x, position.y),
                    color,
                    new Point(position.x, SQUARE_SIZE + position.y),
                    color.darker()

            ));
            g2.fill(new Rectangle(position.x, position.y, SQUARE_SIZE, SQUARE_SIZE));
            g2.setColor(color.darker().darker());
            g2.draw(new Rectangle(position.x, position.y, SQUARE_SIZE, SQUARE_SIZE));
        }

    }
}
