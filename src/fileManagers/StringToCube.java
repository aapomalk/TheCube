/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fileManagers;

import java.util.Scanner;
import thecube.Cube;
import thecube.Direction;

/**
 *
 * @author Aapo
 */
public class StringToCube {

    public static Cube stringToCube(Scanner scanner) {
        // use Color.getDirection() and Cube toString analoge
        Direction[][][][] cube = null;
        StringCube[][][] strings = null;

        Direction d = null;
        int row = -1;
        int col;
        int cubenumber = 0;
        int sides = 0;

        long lifetime = 0;
        while (scanner.hasNextLine() && sides <= 6) {
            if (cubenumber != 0 && sides == 6 && row == cubenumber) {
                break;
            }
            String line = scanner.nextLine();
            if (line.contains("Side ")) {
                d = Direction.valueOf("" + line.charAt(5));
                row = 0;
                sides = sides + 1;
                continue;
            }
            if (d == null) {
                continue;
            }
            String[] cols = line.split(", ");
            if (cubenumber == 0) {
                cubenumber = cols.length;
            } else if (cubenumber != cols.length) {
                throw new Error("Rows don't match");
            }
            if (strings == null) {
                strings = buildStringCubes(cubenumber);
                cube = new Direction[cubenumber][cubenumber][cubenumber][2];
            }
            for (col = 0; col < cubenumber; col++) {
                int[] indexes = getIndexes(d, row, col, cubenumber);
                strings[indexes[0]][indexes[1]][indexes[2]].add(clean(cols[col]), d);
                //System.out.println(strings[indexes[0]][indexes[1]][indexes[2]]);
            }
            row = row + 1;
        }
        if (scanner.hasNextLine()) {
            lifetime = Long.parseLong(scanner.nextLine());
        }
        //System.out.println(lifetime);
        return turnTextToCube(strings, cube, cubenumber, lifetime);
    }

    private static Cube turnTextToCube(StringCube[][][] sc, Direction[][][][] cube,
            int cubenumber, long lifetime) {
        for (int i = 0; i < cubenumber; i++) {
            for (int j = 0; j < cubenumber; j++) {
                for (int k = 0; k < cubenumber; k++) {
                    cube[i][j][k] = sc[i][j][k].getDirection();
                }
            }
        }
        return new Cube(cube, lifetime);
    }

    private static String clean(String text) {
        text = text.replace("[", "");
        text = text.replace("]", "");
        text = text.replace(" ", "");
        return text;
    }

    private static StringCube[][][] buildStringCubes(int cubenumber) {
        StringCube[][][] sc = new StringCube[cubenumber][cubenumber][cubenumber];
        for (int i = 0; i < cubenumber; i++) {
            for (int j = 0; j < cubenumber; j++) {
                for (int k = 0; k < cubenumber; k++) {
                    sc[i][j][k] = new StringCube(cubenumber, i, j, k);
                }
            }
        }
        return sc;
    }

    private static int[] getIndexes(Direction d, int row, int col, int cubenumber) {
        int[] indexes = new int[3];
        switch (d) {
            case U:
                indexes[0] = cubenumber - 1 - row; //i back
                indexes[1] = col;
                ; //j right
                indexes[2] = 0; //k down
                break;
            case D:
                indexes[0] = row;
                indexes[1] = col;
                indexes[2] = cubenumber - 1;
                break;
            case F:
                indexes[0] = 0;
                indexes[1] = col;
                indexes[2] = row;
                break;
            case B:
                indexes[0] = cubenumber - 1;
                indexes[1] = cubenumber - 1 - col;
                indexes[2] = row;
                break;
            case L:
                indexes[0] = cubenumber - 1 - col;
                indexes[1] = 0;
                indexes[2] = row;
                break;
            default:
                indexes[0] = col;
                indexes[1] = cubenumber - 1;
                indexes[2] = row;
        }
        return indexes;
    }
}
