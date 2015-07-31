/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fileManagers;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Aapo
 */
public class ReadFromFile {
    
    public static Scanner read(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            //scanner.close();
            return scanner;
        } catch (Exception e) {
            System.out.println(e);
            //throw new Error("Didn't work");
            return null;
        }
    }
}
