/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmGenerator;

/**
 *
 * @author Aapo
 */
public class GeneralAlgorithm {
    private String[] variables;
    private String algorithm;
    
    public GeneralAlgorithm(String algorithm, String... variables) {
        this.variables = new String[variables.length];
        int i = 0;
        for (String variable : variables) {
            if (!algorithm.contains(variable)) {
                throw new Error("Algorithm must contain all variables");
            }
            this.variables[i] = variable;
            i = i + 1;
        }
        this.algorithm = algorithm;
    }
    
    public int getNumberOfVariables() {
        return this.variables.length;
    }
    
    public String getRawAlgorithm() {
        return this.algorithm;
    }
    
    public Algorithm getAlgorithm(int cubenumber, String... numbers) {
        String newAlgorithm = this.algorithm;
        int i = 0;
        for (String number : numbers) {
            newAlgorithm = newAlgorithm.replaceAll(variables[i], number);
            i = i + 1;
        }
        return AlgorithmsGenerator.generate(newAlgorithm, cubenumber);
    }
    
    @Override
    public String toString() {
        String toReturn = "(";
        for (int i = 0; i < this.variables.length; i++) {
            toReturn = toReturn + this.variables[i];
            if (i < this.variables.length - 1) {
                toReturn = toReturn + ", ";
            } else {
                toReturn = toReturn + ") ";
            }
        }
        return toReturn + this.algorithm;
    }
}
