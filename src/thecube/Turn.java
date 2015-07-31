/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thecube;

/**
 *
 * @author Aapo
 */
public class Turn {
    private Axis axis;
    private int number;
    private int howMany;
    
    public Turn(Axis axis, int number, int howMany) {
        this.axis = axis;
        this.number = number;
        this.howMany = howMany % 4;
    }
    
    public Turn(Axis axis, int number) {
        this(axis, number, 1);
    }
    
    public Axis getAxis() {
        return this.axis;
    }
    
    public int getNumber() {
        return this.number;
    }
    
    public int getHowMany() {
        return this.howMany;
    }
    
    public int getReverseHowMany() {
        return (this.howMany + 2) % 4;
    }
    
    public Turn getReverse() {
        return new Turn(this.axis, this.number, this.getReverseHowMany());
    }
    
    public Turn getMirror(int cubenumber) {
        switch (this.axis) {
            case X: 
            case Z: return this.getReverse();
            case Y: return new Turn(this.axis, (cubenumber - 1 - this.number), this.howMany);
            default: return null;
        }
    }
}
