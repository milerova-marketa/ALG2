/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import app.Runner;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalTime;

/**
 *
 * @author Marketa.Milerova
 */
public class ReadResult {

    public static void ReadResult(String resultFilePath) throws FileNotFoundException, IOException {
        try (DataInputStream dis = new DataInputStream((new FileInputStream(resultFilePath)))) {
            boolean isEnd = false;
            int n;
            System.out.println(dis.readUTF());
            while (!isEnd) {
                try {
                    System.out.print(dis.readInt() + ". ");
                    String firstName = dis.readUTF();
                    n = dis.readInt();
                    String lastName = "";
                    for (int i = 0; i < n; i++) {
                        lastName += dis.readChar();
                    }
                    LocalTime runningTime = LocalTime.ofNanoOfDay(dis.readLong());
                    System.out.println(firstName + " " + lastName + " " + runningTime.format(Runner.dtfFinish));
                } catch (EOFException e) {
                    isEnd = true;
                }
            }
        }
    }
}
