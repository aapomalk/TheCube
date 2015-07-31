/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmGenerator;

import thecube.Cube;
import thecube.Turn;

/**
 *
 * @author Aapo
 */
public class NotationTurn implements Algorithms {
    private Turn turn;
    private int cubenumber;
    
    public NotationTurn(int cubenumber, int number, Notation n) {
        this.cubenumber = cubenumber;
        this.turn = new Turn(n.getAxis(), this.whichNumber(number, n), n.getHowMany());
    }
    
    public NotationTurn(int cubenumber, Notation n) {
        this(cubenumber, 0, n);
    }
    
    private int whichNumber(int number, Notation n) {
        int which = -1;
        switch (n) {
            case D: case d: case D2:
            case B: case b: case B2: 
            case R: case r: case R2: which = cubenumber - number - 1; break;
            case L: case l: case L2: 
            case U: case u: case U2: 
            case F: case f: case F2: which = number; break;
            case M: case m: case M2: 
            case E: case e: case E2: 
            case S: case s: case S2:
                if (cubenumber % 2 == 0 && number > 0) {
                    number = number - 1;
                }
                which = cubenumber / 2 + number;
                break;
        }
        if (which < 0 || which >= cubenumber) {
            throw new Error("Number out of limits.");
        }
        return which;
    }

    @Override
    public void useOn(Cube c) {
        c.turn(turn);
    }

    @Override
    public void useReverse(Cube c) {
        c.turn(turn.getReverse());
    }

    @Override
    public void useMirror(Cube c) {
        c.turn(turn.getMirror(cubenumber));
    }

    @Override
    public void useMirrorReverse(Cube c) {
        c.turn(turn.getMirror(cubenumber).getReverse());
    }
}
