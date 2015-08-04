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
public class AlgorithmPiper {

    public static void pipe(GeneralAlgorithms algs, String command, Cube c) {
        if (!command.contains("|")) {
            try {
                GeneralAlgorithmsGenerator.use(algs, command, c);
            } catch (Exception e) {
                System.out.println(e);
                throw new Error("didn't work");
            }
        } else {
            String[] parts = command.split("\\|");
            int length = parts.length;
            if (length == 0) {
                throw new Error("try again?");
            }
            int start;
            int stop;
            int direction;
            boolean reverse;
            if (parts[length - 1].contains("reverse") && !parts[length - 1].contains("(")) {
                reverse = true;
                start = length - 2;
                stop = 0;
                direction = -1;
            } else {
                reverse = false;
                start = 0;
                stop = length - 1;
                direction = 1;
            }
            for (int i = start; i != stop + direction; i = i + direction) {
                if (parts[length - 1].contains("mirror") && !parts[length - 1].contains("(")) {
                    if (i == length - 1) {
                        continue;
                    }
                    manageMirrorReverse(algs, parts[i], c, true, reverse);
                } else {
                    manageMirrorReverse(algs, parts[i], c, false, reverse);
                }
            }
        }
    }

    private static void manageMirrorReverse(GeneralAlgorithms algs,
            String command, Cube c, boolean mirror, boolean reverse) {
        String piped;
        if (!command.contains("(")) {
            GeneralAlgorithmsGenerator.generate(algs, "piped() " + command);
            piped = "piped()";
        } else {
            piped = command;
        }
        if (mirror) {
            piped = replaceOrPut(piped, "mirror");
        }
        if (reverse) {
            piped = replaceOrPut(piped, "reverse");
        }
        AlgorithmPiper.pipe(algs, piped, c);
    }

    private static String replaceOrPut(String one, String two) {
        if (one.contains(two)) {
            one = one.replace(two, "");
        } else {
            one = one + " " + two;
        }
        return one;
    }
}
