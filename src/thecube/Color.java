/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thecube;

/**
 *
 * @author Aapo
 */
public class Color {

    private static final char esc = (char) 27;
    private static final String[] c_code = new String[]{esc + "[00;34m",
        esc + "[00;32m", esc + "[00;37m",
        esc + "[00;33m", esc + "[00;35m",
        esc + "[00;31m", esc + "[00;00m"};
    private static final String[] texts = new String[]{"blue  ",
        "green ", "white ",
        "yellow", "violet",
        "red   "};
    private static final String[] colors = new String[]{c_code[0] + texts[0] + c_code[6],
        c_code[1] + texts[1] + c_code[6], c_code[2] + texts[2] + c_code[6],
        c_code[3] + texts[3] + c_code[6], c_code[4] + texts[4] + c_code[6],
        c_code[5] + texts[5] + c_code[6]};

    public static String getColor(Direction[] d) {
        return getColor(d, colors);
    }

    public static String getColorText(Direction[] d) {
        return getColor(d, texts);
    }
    
    public static Direction[] getDirection(String text, Direction d) {
        return Color.getDirection(new String[] {text}, new Direction[] {d});
    }

    public static Direction[] getDirection(String[] text, Direction[] d) {
        for (Direction d1 : Direction.values()) {
            for (Direction d2 : Direction.values()) {
                if (d1 == d2 || d1.getDirection() + d2.getDirection() == 7) {
                    continue;
                }
                Direction[] D = new Direction[]{d1, d2};
                boolean itIsHit = true;
                for (int i = 0; i < text.length; i++) {
                    Direction[] tempD = Color.antiTurn(d[i], D);
                    itIsHit = itIsHit && (Color.getColorText(tempD).contains(text[i])
                            || Color.getColorDirectionText(tempD).contains(text[i]));
                    
                    //System.out.println(tempD[0] + " " + tempD[1]);
                }
                if (itIsHit && text.length > 0) {
                    //System.out.println();
                    return D;
                }
            }
        }
        return new Direction[] {Direction.F, Direction.U};
    }

    private static Direction[] antiTurn(Direction d, Direction[] D) {
        switch (d) {
            case F:
                return D;
            case R:
                return Axis.X.turnDirection(D, 3);
            case B:
                return Axis.X.turnDirection(D, 2);
            case L:
                return Axis.X.turnDirection(D);
            case U:
                return Axis.Y.turnDirection(D, 3);
            case D:
            default:
                return Axis.Y.turnDirection(D);
        }
    }

    private static String getColor(Direction[] d, String[] names) {
        String stringToReturn;
        switch (d[0]) {
            case F:
                stringToReturn = names[0];
                break;
            case B:
                stringToReturn = names[1];
                break;
            default:
                switch (d[1]) {
                    case F:
                        stringToReturn = names[2];
                        break;
                    case B:
                        stringToReturn = names[3];
                        break;
                    default:
                        if (d[0] == Direction.L && d[1] == Direction.U
                                || d[0] == Direction.R && d[1] == Direction.D
                                || d[0] == Direction.U && d[1] == Direction.R
                                || d[0] == Direction.D && d[1] == Direction.L) {
                            stringToReturn = names[4];
                        } else if (d[0] == Direction.L && d[1] == Direction.D
                                || d[0] == Direction.R && d[1] == Direction.U
                                || d[0] == Direction.U && d[1] == Direction.L
                                || d[0] == Direction.D && d[1] == Direction.R) {
                            stringToReturn = names[5];
                        } else {
                            stringToReturn = "error ";
                        }
                }
        }
        return stringToReturn;
    }

    public static String[] getColors() {
        return colors;
    }

    public static String[] getColorTexts() {
        return texts;
    }

    public static String getColorDirection(Direction[] d) {
        return getColorDirection(d, Color.getColor(d));
    }

    private static String getColorDirection(Direction[] d, String color) {
        switch (d[1]) {
            case F:
            case B:
                return d[0].toString() + color;
        }
        return d[1].toString() + color;
    }

    public static String getColorDirectionText(Direction[] d) {
        return getColorDirection(d, Color.getColorText(d));
    }
}
