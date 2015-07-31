/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thecube;

/**
 *
 * @author Aapo
 */
public enum Axis {
    X(Direction.U,Direction.R,Direction.B,Direction.L,Direction.F,Direction.D), 
    Y(Direction.B,Direction.U,Direction.R,Direction.D,Direction.L,Direction.F), 
    Z(Direction.L,Direction.F,Direction.U,Direction.B,Direction.D,Direction.R);
    
    private final Direction turnUto;
    private final Direction turnFto;
    private final Direction turnRto;
    private final Direction turnBto;
    private final Direction turnLto;
    private final Direction turnDto;
    Axis(Direction i1, Direction i2, Direction i3, 
            Direction i4, Direction i5, Direction i6) {
        this.turnUto = i1;
        this.turnFto = i2;
        this.turnRto = i3;
        this.turnBto = i4;
        this.turnLto = i5;
        this.turnDto = i6;
    }
    
    Direction turnDirection(Direction direction) {
        switch (direction) {
            case U: return turnUto;
            case F: return turnFto;
            case R: return turnRto;
            case B: return turnBto;
            case L: return turnLto;
            case D: return turnDto;
        }
        return null;
    }
    
    Direction[] turnDirection(Direction[] d) {
        Direction[] d2 = new Direction[d.length];
        for (int i = 0; i < d.length; i++) {
            d2[i] = turnDirection(d[i]);
        }
        return d2;
    }
    
    Direction[] turnDirection(Direction[] d, int number) {
        for (int i = 0; i < number; i++) {
            d = turnDirection(d);
        }
        return d;
    }
    
    Direction turnDirection(Direction d, int number) {
        for (int i = 0; i < number; i++) {
            d = turnDirection(d);
        }
        return d;
    }
}
