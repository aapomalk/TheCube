/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmGenerator;

/**
 *
 * @author Aapo
 */
public class TurnGenerator {
    public static NotationTurn generate(String command, int cubenumber) {
        NotationTurn generated = null;
        if (command.matches("^-?[0-9]+ [RLFBUDrlfbudMESmes]2?$")) {
            String[] splitted = command.split(" ");
            generated = new NotationTurn(cubenumber, Integer.parseInt(splitted[0]), Notation.valueOf(splitted[1]));
        } else if (command.matches("^[RLFBUDrlfbudMESmes]2?$")) {
            generated = new NotationTurn(cubenumber, Notation.valueOf(command));
        } else {
            throw new Error("Wrong syntax.");
        }
        return generated;
    }
}
