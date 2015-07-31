/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmGenerator;

/**
 *
 * @author Aapo
 */
public class AlgorithmsGenerator {
    public static Algorithm generate(String commands, int cubenumber) {
        String[] splitted = commands.split(", ");
        Algorithm alg = new Algorithm(splitted.length);
        for (int i = 0; i < splitted.length; i++) {
            String command = splitted[i];
            if (command.matches("^-?[0-9]+ [RLFBUDrlfbudMESmes]2?$") || command.matches("^[RLFBUDrlfbudMESmes]2?$")) {
                try {
                    alg.setIndex(TurnGenerator.generate(command, cubenumber),i);
                } catch (Exception e) {
                    System.out.println(e);
                    throw new Error("Not working");
                }
            } else if (command.matches("^[1-9][0-9]*[RLFBUDrlfbudMESmes]2?$")) {
                try {
                    int number; String ending;
                    if (command.matches("^[1-9][0-9]*[RLFBUDrlfbudMESmes]2$")) { 
                        number = Integer.parseInt(command.substring(0, command.length()-2));
                        ending = command.substring(command.length()-2, command.length());
                    } else {
                        number = Integer.parseInt(command.substring(0, command.length()-1));
                        ending = command.substring(command.length()-1, command.length());
                    }
                    System.out.println(ending);
                    System.out.println(number);
                    String newCommand = ending;
                    for (int j = 1; j < number; j++) {
                        if (ending.matches("^[RLFBUDrlfbud]2?$")) {
                            newCommand = newCommand + ", " + j + " " + ending;
                        } else {
                            int number2 = (-1)*(number/2)+j-1;
                            if (cubenumber % 2 == 0) {
                                if (number2 >= 0) {
                                    number2 = number2 + 2;
                                }
                            } else {
                                if (number2 >= 0) {
                                    number2 = number2 + 1;
                                }
                            }
                            newCommand = newCommand + ", " + number2 + " " + ending;
                        }
                    }
                    System.out.println(newCommand);
                    alg.setIndex(AlgorithmsGenerator.generate(newCommand, cubenumber), i);
                } catch (Error e) {
                    throw e;
                }
            } else {
                throw new Error("Syntax not supported.");
            }
        }
        return alg;
    }
}
