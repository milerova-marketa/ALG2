/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filehandling;

import app.Runner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author Marketa.Milerova
 */
public class TextWriter extends Writer {

    @Override
    public void saveResults(String resultFilePath, List<Runner> runners) throws IOException {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(resultFilePath, false)))) {
            pw.println("Nove vysledky");
            int n = 1;
            for (Runner runner : runners) {
                pw.println(n + ". " + runner.toString());
                n++;
            }
        }
    }

}
