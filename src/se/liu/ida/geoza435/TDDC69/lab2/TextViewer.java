package se.liu.ida.geoza435.TDDC69.lab2;


import java.util.Observable;
import java.util.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: Gosha
 * Date: 2012-09-01
 * Time: 16:28
 * A class used for viewing the board in text format
 */
public class TextViewer implements Observer {

    private String colorToCharacter(SquareColor color) {
        if (color == null) {
            return " ";
        }
        switch (color) {
            case CYAN:
                return ("◘");
            case RED:
                return ("◙");
            case BLUE:
                return ("█");
            case GREEN:
                return ("▓");
            case YELLOW:
                return ("■");
            case PURPLE:
                return ("▒");
            case ORANGE:
                return ("░");
            default:
                return ("?");
        }
    }


    public String convertToText(Board board) {
        StringBuilder outStr = new StringBuilder();
        outStr.append("╔");
        for (int i = 0; i < board.getWidth(); i++) {
            outStr.append("═");
        }
        outStr.append("╗\n");

        for (int row = 0; row < board.getHeight(); row++) {
            outStr.append("╟");
            for (int col = 0; col < board.getWidth(); col++) {
                outStr.append(colorToCharacter(board.getSquare(row, col)));
            }
            outStr.append("╢\n");
        }
        outStr.append("╚");
        for (int i = 0; i < board.getWidth(); i++) {
            outStr.append("═");
        }
        outStr.append("╝");

        if (board.fallingBlock != null) {
            for (SquarePos pos : board.fallingBlock.getBlocks()) {
                int xPosOnScreen = board.fallingBlock.position.x + pos.x + 1;
                int yPosOnScreen = board.fallingBlock.position.y + pos.y;
                int location = xPosOnScreen + (3 + board.getWidth()) * (yPosOnScreen + 1);
                outStr.replace(
                        location, location + 1,
                        colorToCharacter(board.fallingBlock.poly.color)
                );
            }
        }

        return outStr.toString();
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(convertToText((Board) o));
    }
}
