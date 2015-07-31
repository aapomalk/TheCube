/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thecube;

import java.util.Random;

/**
 *
 * @author Aapo
 */
public class Shuffler {
    public static void shuffle(Cube c) {
        Random r = new Random();
        int cubenumber = c.getCubenumber();
        for (int i = 0; i < 7*cubenumber + r.nextInt(cubenumber); i++) {
            c.turn(randomAxis(r), randomNumber(cubenumber, r));
        }
    }
    
    private static Axis randomAxis(Random r) {
        switch (r.nextInt(3)) {
            case 0: return Axis.X;
            case 1: return Axis.Y;
            case 2: return Axis.Z;
            default: return null;
        }
    }
    
    private static int randomNumber(int cubenumber, Random r) {
        return r.nextInt(cubenumber);
    }
}
