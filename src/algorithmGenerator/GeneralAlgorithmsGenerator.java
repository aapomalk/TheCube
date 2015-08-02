/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmGenerator;

/**
 *
 * @author Aapo
 */
public class GeneralAlgorithmsGenerator {
    public static void generate(GeneralAlgorithms algs, String command) {
        String[] parts = command.split("\\(");
        String name = parts[0];
        
        String[] parts2 = parts[1].split("\\) ");
        String[] variables = parts2[0].split(",");
        
        String algorithm = parts2[1];
        
        algs.put(name, algorithm, variables);
    }
    
    public static Algorithm get(GeneralAlgorithms algs, String command, int cubenumber) {
        String[] parts = command.split("\\(");
        String name = parts[0];
        
        String[] parts2 = parts[1].split("\\)");
        String[] numbers = parts2[0].split(",");
        
        return algs.get(cubenumber, name, numbers);
    }
}
