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
public class GeneralAlgorithmsGenerator {
    public static void generate(GeneralAlgorithms algs, String command) {
        String[] parts = command.split("\\(");
        String name = parts[0];
        
        String[] parts2 = parts[1].split("\\) ");
        String[] variables = parts2[0].split(",");
        
        String algorithm = parts2[1];
        
        algs.put(name, algorithm, variables);
    }
    
    public static void use(GeneralAlgorithms algs, String command, Cube c) {
        Algorithm alg;
        if (!command.contains("(")) {
            alg = AlgorithmsGenerator.generate(command, c.getCubenumber());
            alg.useOn(c);
            return;
        }
        
        String[] parts = command.split("\\(");
        String name = parts[0];
        
        String[] parts2 = parts[1].split("\\)");
        String[] numbers = parts2[0].split(",");
        
        alg = algs.get(c.getCubenumber(), name, numbers);
        
        if (parts2.length < 2) {
            alg.useOn(c);
        } else {
            String command2 = parts2[1];
            if (command2.contains("reverse") && command2.contains("mirror")) {
                alg.useMirrorReverse(c);
            } else if (command2.contains("reverse")) {
                alg.useReverse(c);
            } else if (command2.contains("mirror")) {
                alg.useMirror(c);
            }
        }
    }
}
