/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filehandling;

import app.Runner;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Marketa.Milerova
 */
public class BinaryWriter extends Writer {

    @Override
    public void saveResults(String resultFilePath, List<Runner> runners) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(resultFilePath))) {
            dos.writeUTF("Nove vysledky");
            int n = 1;
            for (Runner runner : runners) {
                dos.writeInt(n);
                //dos.writeChar('.');
                dos.writeUTF(runner.getFirstName());
                int nOfChars = runner.getLastName().length();
                dos.writeInt(nOfChars);
                for (int i = 0; i < nOfChars; i++) {
                    dos.writeChar(runner.getLastName().charAt(i));
                }
                dos.writeLong(runner.runningTime().toNanoOfDay());
                n++;
            }
        }
    }

    public void createStart(String startFilePath) throws FileNotFoundException, IOException {
        File binaryFile = new File(startFilePath);
        File textFile = new File(startFilePath.replace(".dat", ".txt"));
        try (Scanner out = new Scanner(textFile)) {
            try (DataOutputStream in = new DataOutputStream(new FileOutputStream(binaryFile))) {
                while (out.hasNext()) {
                    in.writeInt(out.nextInt());
                    in.writeUTF(out.next());
                    in.writeUTF(out.next());
                    String startTime = out.next();
                    LocalTime start = LocalTime.parse(startTime, Runner.dtfStart);
                    in.writeLong(start.toNanoOfDay());
                }
            }
        }

    }

    public void createFinish(String finishFilePath) throws FileNotFoundException, IOException {
        File binaryFile = new File(finishFilePath);
        File textFile = new File(finishFilePath.replace(".dat", ".txt"));
        try (Scanner out = new Scanner(textFile)) {
            try (DataOutputStream in = new DataOutputStream(new FileOutputStream(binaryFile))) {
                while (out.hasNext()) {
                    in.writeInt(out.nextInt());
                    String finishTime = out.next();
                    LocalTime finish = LocalTime.parse(finishTime, Runner.dtfFinish);
                    in.writeLong(finish.toNanoOfDay());
                }
            }
        }
    }

}
