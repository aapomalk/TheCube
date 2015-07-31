/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fileManagers;

import java.io.FileWriter;

/**
 *
 * @author Aapo
 */
public class WriteToFile {

    public static void write(String text, String filename) {
        try {
            FileWriter kirjoittaja = new FileWriter(filename);
            kirjoittaja.write(text);
            kirjoittaja.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
