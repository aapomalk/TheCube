/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmGenerator;

import java.util.HashMap;

/**
 *
 * @author Aapo
 */
public class GeneralAlgorithms {
    private HashMap<String, GeneralAlgorithm> algs;
    
    public GeneralAlgorithms() {
        this.algs = new HashMap<String, GeneralAlgorithm>();
    }
    
    public void put(String name, String algorithm, String... variables) {
        this.algs.put(name, new GeneralAlgorithm(algorithm, variables));
    }
    
    public Algorithm get(int cubenumber, String name, String... numbers) {
        return this.algs.get(name).getAlgorithm(cubenumber, numbers);
    }
    
    public GeneralAlgorithm getGeneralAlgorithm(String name) {
        return this.algs.get(name);
    }
    
    @Override
    public String toString() {
        String toReturn = "";
        for (String key : algs.keySet()) {
            toReturn = toReturn + key + " " + algs.get(key) + "\n";
        }
        return toReturn;
    }
}
