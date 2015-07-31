/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thecube;

import java.util.HashMap;

/**
 *
 * @author Aapo
 */
public class Checker {
    public static boolean check(Cube c) {
        int cubenumber = c.getCubenumber();
        HashMap<String, Integer> colorCounter = new HashMap<String, Integer>();
        for (String col : Color.getColorTexts()) {
            colorCounter.put(col, 0);
        }
        boolean correct = true;
        for (Direction d : Direction.values()) {
            String[][] colors = c.getSideColored(d);
            String color = colors[0][0];
            for (int i = 0; i < cubenumber; i++) {
                for (int j = 0; j < cubenumber; j++) {
                    if (!color.equals(colors[i][j])) {
                        correct = false;
                    }
                    String col = colors[i][j];
                    //System.out.println(col.length());
                    if (col.length() == 22) {
                        col = col.substring(8,14);
                    } else if (col.length() == 23) {
                        col = col.substring(9,15);
                    } else if (col.length() == 7) {
                        col = col.substring(1);
                    }
                    int number = colorCounter.get(col);
                    colorCounter.put(col, (number + 1));
                }
            }
        }
        for (int values : colorCounter.values()) {
            if (values != cubenumber*cubenumber) {
                System.out.println("Error! Wrong number of colors..");
            }
        }
        return correct;
    }
}
