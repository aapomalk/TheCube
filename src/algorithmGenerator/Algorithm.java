/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmGenerator;

import thecube.Cube;

/**
 *
 * @author Aapo
 */
public class Algorithm implements Algorithms {
    private Algorithms[] algorithm;
    //private Algorithms[] reverse;
    
    public Algorithm(int howMany) {
        this.algorithm = new Algorithms[howMany];
        //this.reverse = new Algorithms[howMany];
    }
    
    public Algorithms getIndex(int index) {
        return this.algorithm[index];
    }
    
    /*public Algorithms getReverseIndex(int index) {
        return this.reverse[index];
    }*/
    
    public void setIndex(Algorithms alg, int index) {
        this.algorithm[index] = alg;
        //Turn reverseTurn = new Turn(alg.getAxis(), alg.getNumber(), alg.getHowMany() + 2);
        //this.reverse[this.getLength() - index - 1] = reverseTurn;
    }
    
    public int getLength() {
        return this.algorithm.length;
    }
    
    @Override
    public void useOn(Cube c) {
        for (int i = 0; i < this.getLength(); i++) {
            this.getIndex(i).useOn(c);
        }
    }
    
    @Override
    public void useReverse(Cube c) {
        for (int i = this.getLength(); i >= 0; i--) {
            this.getIndex(i).useReverse(c);
        }
    }

    @Override
    public void useMirror(Cube c) {
        for (int i = 0; i < this.getLength(); i++) {
            this.getIndex(i).useMirror(c);
        }
    }

    @Override
    public void useMirrorReverse(Cube c) {
        for (int i = this.getLength(); i >= 0; i--) {
            this.getIndex(i).useMirrorReverse(c);
        }
    }
}
