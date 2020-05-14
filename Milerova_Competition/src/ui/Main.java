package ui;

import app.Competition;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Marketa.Milerova
 */
public class Main {

    public static Scanner sc = new Scanner(System.in);
    // du udělat metody na načítání a vytvoření binárních souborů ze start a finish

    public static void main(String[] args) {
        Competition c = new Competition();
        try {
            while (true) {
                try {
                    System.out.println("Zadej názvy vstupních souborů");
                    String startFile = sc.next();
                    String finishFile = sc.next();
                    c.load(startFile, finishFile);
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());

                }
            }
            System.out.println(c.getResults());
            System.out.println("Zadej název výstupního souboru");
            String resultFile = sc.next();
            c.saveResults(resultFile);
        } catch (IOException ex) {
            System.out.println("Chyba pri cteni a zapisu");
        } catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
    }
}
