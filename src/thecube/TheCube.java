/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thecube;

import algorithmGenerator.*;
import fileManagers.ReadFromFile;
import fileManagers.StringToCube;
import fileManagers.WriteToFile;
import java.util.Scanner;

/**
 *
 * @author Aapo
 */
public class TheCube {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Cube cube = new Cube(2);
        //System.out.println(cube);
        /*cube.turn(Axis.Z, 1);
         //Shuffler.shuffle(cube);
         Checker.check(cube);
         System.out.println(cube);*/
        solver();
        //TurnGenerator.generate("5 r2", 2);
        /*System.out.println(Arrays.deepToString(
         Color.getDirection(new String[] {"yellow","violet"}, 
         new Direction[] {Direction.R, Direction.D})));*/
        /*Cube cube = StringToCube.stringToCube(getScanner());
         System.out.println(cube);
         Checker.check(cube);*/
    }

    private static void solver() {
        GeneralAlgorithms algs = new GeneralAlgorithms();
        algs.put("test", "x m, y m, U, y M, u, x M, U, y m, u, y M", new String[] {"x", "y"});
        
        Scanner scanner = new Scanner(System.in);
        int cubenumber = 0;
        boolean playground = false;
        System.out.print("Load save? (yes/anything) ");
        String answer = scanner.nextLine();
        Cube c = null;
        if (answer.equals("yes")) {
            c = load(scanner);
            System.out.println(c);
        } else {
            while (cubenumber < 2) {
                System.out.print("Choose cubenumber: ");
                String temp = scanner.nextLine();
                try {
                    cubenumber = Integer.parseInt(temp);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            c = new Cube(cubenumber);
            //System.out.println(Checker.check(c));
            System.out.print("Playgound? (yes/anything) ");
            String play = scanner.nextLine();
            if (play.equals("yes")) {
                playground = true;
            } else {
                Shuffler.shuffle(c);
            }
            //c.changeOnlyColors();
            //System.out.println(Checker.check(c));
            System.out.println(c);
            welcome();
            printInstructions(playground);
        }
        
        //algs.get(c.getCubenumber(), "test", new String[] {"-1", "2"}).useOn(c);
        //System.out.println(c);

        while (!Checker.check(c) || playground) {
            System.out.print("Turn cube: ");
            String line = scanner.nextLine();
            if (line.equals("exit")) {
                break;
            } else if (line.equals("GeneralAlgorithm")) {
                System.out.println("Syntax:\nname(var1,var2) var1 R, var2 L");
                GeneralAlgorithmsGenerator.generate(algs, scanner.nextLine());
                continue;
            } else if (line.equals("list")) {
                System.out.print(algs);
                continue;
            } else if (line.equals("time")) {
                System.out.println("Cubes lifetime: " + printLifetime(c.getLifetime()));
                continue;
            } else if (line.equals("supercube")) {
                c.changeSuperCubeMode();
                System.out.println(c);
                continue;
            } else if (line.equals("colormode")) {
                c.changeCubeColorsMode();
                System.out.println(c);
                continue;
            } else if (line.equals("save") && !playground) {
                save(c, scanner);
                continue;
            } else if (line.equals("load") && !playground) {
                c = load(scanner);
                System.out.println("Loaded cube:");
                System.out.println(c);
                continue;
            }
            //Algorithm alg;
            try {
                //alg = AlgorithmsGenerator.generate(line, c.getCubenumber());
                GeneralAlgorithmsGenerator.use(algs, line, c);
            } catch (Error e) {
                System.out.println(e);
                printInstructions(playground);
                continue;
            }
            //alg.useOn(c);
            
            System.out.println(c);
        }
        System.out.println("Cubes lifetime: " + printLifetime(c.getLifetime()));

        System.out.print("Do you want to restart? (yes/anything) ");
        String restart = scanner.nextLine();
        if (restart.equals("yes")) {
            TheCube.main(new String[]{});
        } else {
            System.out.println("Good work! See you!");
        }
    }

    private static String printLifetime(long lifetime) {
        String toReturn = "";
        double hours = Math.floor(lifetime / (1000 * 60 * 60));
        double minutes = Math.floor((lifetime - hours * 1000 * 60 * 60) / (1000 * 60));
        double seconds = ((lifetime - hours * 1000 * 60 * 60 - minutes * 1000 * 60) / (1000));
        if (hours > 0) {
            toReturn = "\nHours: " + hours;
        }
        if (minutes > 0) {
            toReturn = toReturn + "\nMinutes: " + minutes;
        }
        toReturn = toReturn + "\nSeconds: " + seconds;
        return toReturn;
    }

    private static Cube load(Scanner sc) {
        try {
            System.out.print("Filename: ");
        String filename = sc.nextLine();
        Scanner filescan = ReadFromFile.read(filename);
        return StringToCube.stringToCube(filescan);
        } catch (Exception e) {
            System.out.println(e);
            return load(sc);
        }
    }

    private static void save(Cube cube, Scanner sc) {
        System.out.print("Filename: ");
        String filename = sc.nextLine();
        WriteToFile.write(cube.toNoColorString() + cube.getLifetime(), filename);
    }

    private static void welcome() {
        System.out.println("Welcome to solver!");
        System.out.println("Above you can see a rubik's cube shuffled");
        System.out.println("");
    }

    private static void printInstructions(boolean playground) {
        System.out.println("Turn the cube like this:");
        System.out.println("F");
        System.out.println("It means turn front clockwise, f would be unclockwise");
        System.out.println("Other commands are:");
        System.out.println("R, L, F, B, U, D, M, E, S");
        System.out.println("F2 turns front 180 degrees");
        System.out.println("");
        System.out.println("2 R and 2R are also valid moves, 0 R is the same as R");
        System.out.println("2R actually means combination of moves: 0 R and 1 R");
        System.out.println("");
        System.out.println("you can make an algorithm like this:");
        System.out.println("r, R, 2 L, 3M2");
        System.out.println("");
        System.out.println("Write exit if you want to exit");
        System.out.println("Write supercube to change from or to supercube");
        System.out.println("Write colormode to change textmode");
        System.out.println("");
        System.out.println("Write GeneralAlgorithm if you want to create general algorithm");
        System.out.println("Write list if you want a list of general algorithms");
        System.out.println("");
        System.out.println("You can use general algorithms with the next syntax:");
        System.out.println("nameOfAlgorithm(1,2) mirror reverse");
        System.out.println("mirror and reverse are both additional parameters");
        System.out.println("Note that mirror might change the sign of number you are using");
        if (playground) {
            return;
        }
        System.out.println("");
        System.out.println("Write load to load and save to save cube");
    }

    private static Scanner getScanner() {
        return new Scanner("Side U\n"
                + "[blue  , yellow, red   ]\n"
                + "[green , green , violet]\n"
                + "[red   , blue  , green ]\n"
                + "Side F\n"
                + "[blue  , white , yellow]\n"
                + "[yellow, red   , red   ]\n"
                + "[blue  , violet, white ]\n"
                + "Side R\n"
                + "[red   , green , white ]\n"
                + "[white , white , green ]\n"
                + "[green , white , green ]\n"
                + "Side B\n"
                + "[green , violet, white ]\n"
                + "[red   , violet, yellow]\n"
                + "[yellow, green , violet]\n"
                + "Side L\n"
                + "[red   , yellow, yellow]\n"
                + "[blue  , yellow, red   ]\n"
                + "[blue  , red   , yellow]\n"
                + "Side D\n"
                + "[violet, blue  , violet]\n"
                + "[blue  , blue  , violet]\n"
                + "[white , white , violet]");
    }
}
