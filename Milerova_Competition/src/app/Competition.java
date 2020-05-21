/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import filehandling.BinaryWriter;
import filehandling.Writer;
import filehandling.TextWriter;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.activation.UnsupportedDataTypeException;

/**
 *
 * @author Marketa.Milerova
 */
public class Competition {

    private ArrayList<Runner> runners = new ArrayList();

    public Competition() {
    }

    public String getResults() {
        Collections.sort(runners);
        StringBuilder sb = new StringBuilder();
        int n = 1;
        for (Runner runner : runners) {
            sb.append(String.format("%2d. %s%n", n, runner));
            n++;
        }
        return sb.toString();
    }

    public void load(String startFilepath, String finishFilepath) throws IOException {
        if (startFilepath.endsWith(".txt")) {
            loadStartText(startFilepath);
        } else if (startFilepath.endsWith(".dat")) {
            loadStartBinary(startFilepath);
        } else {
            throw new UnsupportedDataTypeException("start type not supported");
        }
        if (finishFilepath.endsWith(".txt")) {
            loadFinishText(finishFilepath);
        } else if (finishFilepath.endsWith(".dat")) {
            loadFinishBinary(finishFilepath);
        } else {
            throw new UnsupportedDataTypeException("finish type not supported");
        }
    }

    public void saveResults(String resultFilePath) throws IOException {
        Collections.sort(runners);
        Writer w = null;
        if (resultFilePath.endsWith(".txt")) {
            w = new TextWriter();
        } else if (resultFilePath.endsWith(".dat")) {
            w = new BinaryWriter();
        } else {
            throw new IllegalArgumentException("not suppported");
        }
        w.saveResults(resultFilePath, runners);
    }

    private Runner findRunner(int number) {
        for (Runner runner : runners) {
            if (runner.getNumber() == number) {
                return runner;
            }
        }
        throw new NoSuchElementException("Bezec s cislem " + number + " nenalezen.");
    }

    private void loadStartText(String startFilepath) throws FileNotFoundException, IOException {
        File startFile = new File(Writer.dataDir, startFilepath);
        try (Scanner inStart = new Scanner(startFile)) {
            while (inStart.hasNext()) {
                int number = inStart.nextInt();
                String firstName = inStart.next();
                String lastName = inStart.next();
                String startTime = inStart.next();
                try {
                    Runner r = new Runner(number, firstName, lastName);
                    r.setStartTime(startTime);
                    runners.add(r);
                } catch (NoSuchElementException e) {
                    System.err.println("runner not found");
                }
            }
        }
    }

    private void loadFinishText(String finishFilepath) throws FileNotFoundException, IOException {
        File finishFile = new File(Writer.dataDir, finishFilepath);
        try (BufferedReader inFinish = new BufferedReader(new FileReader(finishFile))) { //automatické uzavření souboru
            String line;
            while ((line = inFinish.readLine()) != null) {
                String[] parts = line.split("[ ]+");
                Runner r = findRunner(Integer.parseInt(parts[0]));
                r.setFinishTime(parts[1]);
            }
        }
    }

    private void loadStartBinary(String startFilepath) throws IOException {
        BinaryWriter bw = new BinaryWriter();
        startFilepath = Character.toUpperCase(startFilepath.charAt(0)) + startFilepath.substring(1);
        bw.createStart(startFilepath);
        File startFile = new File(Writer.dataDir, startFilepath);
        try (DataInputStream dis = new DataInputStream((new FileInputStream(startFile)))) {
            boolean isEnd = false;
            while (!isEnd) {
                try {
                    int number = dis.readInt();
                    String firstName = dis.readUTF();
                    String lastName = dis.readUTF();
                    long startTimeNano = dis.readLong();
                    String startTime = (LocalTime.ofNanoOfDay(startTimeNano)).format(Runner.dtfStart);
                    try {
                        Runner r = new Runner(number, firstName, lastName);
                        r.setStartTime(startTime);
                        runners.add(r);
                    } catch (NoSuchElementException e) {
                        System.err.println("runner not found");
                    }
                } catch (EOFException e) {
                    isEnd = true;
                }
            }
        }

    }

    private void loadFinishBinary(String finishFilepath) throws IOException {
        BinaryWriter bw = new BinaryWriter();
        finishFilepath = Character.toUpperCase(finishFilepath.charAt(0)) + finishFilepath.substring(1);
        bw.createFinish(finishFilepath);
        File finishFile = new File(Writer.dataDir, finishFilepath);
        try (DataInputStream dis = new DataInputStream((new FileInputStream(finishFile)))) {
            boolean isEnd = false;
            while (!isEnd) {
                try {
                    int number = dis.readInt();
                    long finishTimeNano = dis.readLong();
                    String finishTime = (LocalTime.ofNanoOfDay(finishTimeNano)).format(Runner.dtfFinish);
                    Runner r = findRunner(number);
                    r.setFinishTime(finishTime);
                } catch (EOFException e) {
                    isEnd = true;
                }
            }
        }
    }

}
