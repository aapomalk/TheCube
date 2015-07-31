/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmGenerator;

import thecube.Axis;

/**
 *
 * @author Aapo
 */
public enum Notation {
    R(1, Axis.Y), L(3, Axis.Y), F(3, Axis.Z), B(1, Axis.Z), U(3, Axis.X),
    r(3, Axis.Y), l(1, Axis.Y), f(1, Axis.Z), b(3, Axis.Z), u(1, Axis.X),
    R2(2, Axis.Y),L2(2, Axis.Y),F2(2, Axis.Z),B2(2, Axis.Z),U2(2, Axis.X),
    D(1, Axis.X), E(1, Axis.X), M(3, Axis.Y), S(3, Axis.Z),
    d(3, Axis.X), e(3, Axis.X), m(1, Axis.Y), s(1, Axis.Z),
    D2(2, Axis.X),E2(2, Axis.X),M2(2, Axis.Y),S2(2, Axis.Z);
    
    private final int howMany;
    private final Axis ax;
    Notation(int howMany, Axis ax) {
        this.howMany = howMany;
        this.ax = ax;
    }
    
    public int getHowMany() {
        return this.howMany;
    }
    
    public Axis getAxis() {
        return this.ax;
    }
}
