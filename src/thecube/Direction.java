/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thecube;

/**
 *
 * @author Aapo
 */
public enum Direction {
    U(1),F(2),R(3),B(5),L(4),D(6);
    
    private final int direction;
    Direction(int direction) {
        this.direction = direction;
    }
    
    int getDirection() {
        return this.direction;
    }
}
