/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thecube;

/**
 *
 * @author Aapo
 */
import java.util.Arrays;

public class Cube {

    private Direction[][][][] littlecubes;
    private int cubenumber;
    private boolean noSuper;
    private boolean noColors;
    private long lifetime;
    private long startTime;

    public Cube(int number) {
        this.littlecubes = new Direction[number][number][number][2];
        this.cubenumber = number;
        this.build();
        this.noSuper = true;
        this.noColors = false;
        this.lifetime = 0;
        this.startTime = System.currentTimeMillis();
    }

    public Cube(Direction[][][][] selfBuild, Long lifetime) {
        if (selfBuild.length != selfBuild[0].length
                || selfBuild[0][0].length != selfBuild[0].length
                || selfBuild[0][0][0].length != 2) {
            throw new Error("cube not valid, wrong size");
        }
        this.littlecubes = selfBuild;
        this.cubenumber = selfBuild.length;
        this.noSuper = true;
        this.noColors = false;
        this.lifetime = lifetime;
        this.startTime = System.currentTimeMillis();
    }
    
    public Cube(Direction[][][][] selfBuild) {
        this(selfBuild, (long) 0);
    }

    public void changeSuperCubeMode() {
        this.noSuper = !this.noSuper;
    }
    
    public void changeCubeColorsMode() {
        this.noColors = !this.noColors;
    }

    public int getCubenumber() {
        return this.cubenumber;
    }
    
    private boolean isInnerCube(int i, int j, int k) {
        int number = 0;
        if (i == 0 || i == cubenumber -1 ) {
            number = 1;
        }
        if (j == 0 || j == cubenumber -1 ) {
            number = number + 1;
        }
        if (k == 0 || k == cubenumber -1 ) {
            number = number + 1;
        }
        if (number >= 1) {
            return false;
        }
        return true;
    }

    private void build() {
        for (int i = 0; i < cubenumber; i++) {
            for (int j = 0; j < cubenumber; j++) {
                for (int k = 0; k < cubenumber; k++) {
                    this.littlecubes[i][j][k][0] = Direction.F;
                    this.littlecubes[i][j][k][1] = Direction.U;
                }
            }
        }
    }

    public void turn(Axis axis, int number) {
        Direction[][][][] tempcubes = copyCubes();
        for (int i = 0; i < cubenumber; i++) {
            for (int j = 0; j < cubenumber; j++) {
                if (isInnerCube(i,j,number)) {
                    continue;
                }
                int[] turned = turnIndex(i, j);
                for (int k = 0; k < 2; k++) {
                    int iTemp;
                    int iTemp2;
                    int jTemp;
                    int jTemp2;
                    int kTemp;
                    int kTemp2;
                    switch (axis) {
                        case X:
                            iTemp = turned[1];
                            jTemp = turned[0];
                            kTemp = number;
                            iTemp2 = j;
                            jTemp2 = i;
                            kTemp2 = number;
                            break;
                        case Y:
                            iTemp = turned[0];
                            jTemp = number;
                            kTemp = turned[1];
                            iTemp2 = i;
                            jTemp2 = number;
                            kTemp2 = j;
                            break;
                        case Z:
                            iTemp = number;
                            jTemp = turned[1];
                            kTemp = turned[0];
                            iTemp2 = number;
                            jTemp2 = j;
                            kTemp2 = i;
                            break;
                        default:
                            iTemp = -1;
                            jTemp = -1;
                            kTemp = -1;
                            iTemp2 = -1;
                            jTemp2 = -1;
                            kTemp2 = -1;
                    }
                    //System.out.println(print(littlecubes) + "\n" + print(tempcubes) + "\n");
                    tempcubes[iTemp2][jTemp2][kTemp2][k] =
                            axis.turnDirection(littlecubes[iTemp][jTemp][kTemp][k]);
                }
            }
        }
        this.littlecubes = tempcubes;
    }

    public void turn(Axis ax, int number, int howMany) {
        for (int i = 0; i < howMany; i++) {
            this.turn(ax, number);
        }
    }

    public void turn(Turn turn) {
        this.turn(turn.getAxis(), turn.getNumber(), turn.getHowMany());
    }

    private int[] turnIndex(int i, int j) {
        int i2 = j;
        int j2 = -i + c();
        return new int[]{i2, j2};
    }

    private Direction[][][] getSide(Direction d) {
        Direction[][][] side = new Direction[cubenumber][cubenumber][2];
        for (int i = 0; i < cubenumber; i++) {
            for (int j = 0; j < cubenumber; j++) {
                for (int k = 0; k < 2; k++) {
                    Direction temp;
                    switch (d) {
                        case F:
                            temp = littlecubes[0][i][j][k];
                            break;
                        case R:
                            temp = Axis.X.turnDirection(littlecubes[i][c()][j][k], 3);
                            break;
                        case B:
                            temp = Axis.X.turnDirection(littlecubes[c()][i][j][k], 2);
                            break;
                        case L:
                            temp = Axis.X.turnDirection(littlecubes[i][0][j][k]);
                            break;
                        case U:
                            temp = Axis.Y.turnDirection(littlecubes[i][j][0][k], 3);
                            break;
                        case D:
                            temp = Axis.Y.turnDirection(littlecubes[i][j][c()][k]);
                            break;
                        default:
                            temp = null;
                    }
                    side[i][j][k] = temp;
                }
            }
        }
        return side;
    }

    public String[][] getSideColored(Direction d) {
        Direction[][][] side = getSide(d);
        String[][] sideColored = new String[cubenumber][cubenumber];
        for (int i = 0; i < cubenumber; i++) {
            for (int j = 0; j < cubenumber; j++) {
                switch (d) {
                    case L:
                    case B:
                        sideColored[j][cubenumber - 1 - i] = getColorOrDirection(side[i][j]);
                        break;
                    case U:
                        sideColored[cubenumber - 1 - i][j] = getColorOrDirection(side[i][j]);
                        break;
                    case D:
                        sideColored[i][j] = getColorOrDirection(side[i][j]);
                        break;
                    default:
                        sideColored[j][i] = getColorOrDirection(side[i][j]);
                }

            }
        }
        return sideColored;
    }

    private String getColorOrDirection(Direction[] side) {
        if (this.noSuper) {
            if (this.noColors) {
                return Color.getColorText(side);
            } else {
                return Color.getColor(side);
            }
        } else {
            if (this.noColors) {
                return Color.getColorDirectionText(side);
            } else {
                return Color.getColorDirection(side);
            }
        }
    }

    private Direction[][][][] copyCubes(Direction[][][][] cubeToCopy) {
        Direction[][][][] D4new = new Direction[cubenumber][cubenumber][cubenumber][2];
        for (int i = 0; i < cubenumber; i++) {
            for (int j = 0; j < cubenumber; j++) {
                for (int k = 0; k < cubenumber; k++) {
                    System.arraycopy(cubeToCopy[i][j][k], 0, D4new[i][j][k], 0, 2);
                }
            }
        }
        return D4new;
    }

    private Direction[][][][] copyCubes() {
        return copyCubes(this.littlecubes);
    }

    private int c() {
        return cubenumber - 1;
    }

    public String sideColoredToString(Direction d) {
        //Direction[][][] side = getSide(d);
        String[][] sideColored = getSideColored(d);
        String sideString = "";
        for (int i = 0; i < cubenumber; i++) {
            //Direction[][] row = side[i];
            sideString = sideString + Arrays.deepToString(sideColored[i]) + "\n";//sideString + Arrays.deepToString(row) + "\n";
        }
        return sideString;
    }

    @Override
    public String toString() {
        String string = "";
        for (Direction d : Direction.values()) {
            string = string + "Side " + d + "\n" + sideColoredToString(d);
        }
        return string;
    }
    
    public String toNoColorString() {
        boolean turnBack = false;
        if (!this.noColors) {
            turnBack = true;
            this.changeCubeColorsMode();
        }
        String toReturn = this.toString();
        if (turnBack) {
            this.changeCubeColorsMode();
        }
        return toReturn;
    }

    private String print(Direction[][][][] D4) {
        String s = "";
        for (Direction[][][] d : D4) {
            for (Direction[][] d2 : d) {
                for (Direction[] d3 : d2) {
                    for (Direction d4 : d3) {
                        s = s + d4.toString();
                    }
                }
            }
        }
        return s;
    }

    public String print() {
        return this.print(littlecubes);
    }
    
    public long getLifetime() {
        return System.currentTimeMillis() - this.startTime + this.lifetime;
    }
}
