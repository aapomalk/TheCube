/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fileManagers;

import java.util.Arrays;
import thecube.Color;
import thecube.Direction;

/**
 *
 * @author Aapo
 */
public class StringCube {

    private int i;
    private int j;
    private int k;
    private int count;
    private String[] texts;
    private Direction[] directions;

    public StringCube(int cubenumber, int i, int j, int k) {
        this.i = i;
        this.j = j;
        this.k = k;
        int number = 0;
        if (i == 0 || i == cubenumber - 1) {
            number = number + 1;
        }
        if (j == 0 || j == cubenumber - 1) {
            number = number + 1;
        }
        if (k == 0 || k == cubenumber - 1) {
            number = number + 1;
        }
        this.texts = new String[number];
        this.directions = new Direction[number];
        this.count = 0;
    }
    
    public void add(String text, Direction d) {
        if (count == texts.length) {
            throw new Error("\nAlready full\ncount was " + count + " and number " + texts.length +
                    "\ni: " + i + ", j: " + j + ", k: " + k);
        }
        this.texts[count] = text;
        this.directions[count] = d;
        count = count + 1;
    }
    
    public Direction[] getDirection() {
        if (count != texts.length) {
            throw new Error("Not full yet");
        }
        return Color.getDirection(texts, directions);
    }
    
    @Override
    public String toString() {
        return i + " " + j + " " + k + " " + Arrays.deepToString(texts) + " " + Arrays.deepToString(directions);
    }
}
