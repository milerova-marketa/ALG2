/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milerova_hurricane;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Marketa.Milerova
 */
public class HurricaneHandler implements HurricaneHandlerInterface {

    private final List<Hurricane> hurricanes = loadData();

    public HurricaneHandler() {

    }

    @Override
    public String getList(int start, int end) {
        int year;
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-4s %-10s %-10s %-20s %-15s%n",
                "Rok", "Měsíc", "Tlak [Mb]", "Rychlost [uzel/h]", "Jméno"));
        for (Hurricane hurricane : hurricanes) {
            year = hurricane.getYear();
            if (year >= start && year <= end) {
                sb.append(hurricane).append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public String getList(String name) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-10s %-10s %-15s%n", "Jméno", "Kategorie", "Rychlost [km/h]"));
        for (Hurricane hurricane : hurricanes) {
            if (hurricane.getName().equalsIgnoreCase(name)) {
                sb.append(String.format("%-10s %-10d %-12.2f",
                        hurricane.getName(), hurricane.getCategory(), hurricane.getVelocityInKmH()));
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public String getList() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-4s %-10s %-10s %-20s %-15s%n",
                "Rok", "Měsíc", "Tlak [Mb]", "Rychlost [uzel/h]", "Jméno"));
        Collections.sort(hurricanes);
        for (Hurricane hurricane : hurricanes) {
            sb.append(hurricane).append("\n");
        }
        return sb.toString();
    }

    private List<Hurricane> loadData() {
        List<Hurricane> hurricanesInit = new ArrayList();
        File file = new File("hurricanedata.txt");
        try {
            Scanner inFile = new Scanner(file);
            int year;
            String month, name;
            int pressure, velocity;
            Hurricane hurricane;
            while (inFile.hasNext()) {
                try {
                    year = inFile.nextInt();
                    month = inFile.next();
                    pressure = inFile.nextInt();
                    velocity = inFile.nextInt();
                    name = inFile.next();
                    hurricane = new Hurricane(year, month, pressure, velocity, name);
                    hurricanesInit.add(hurricane);
                } catch (Exception e) {
                    throw new RuntimeException("Bad file");
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found");
        }
        return hurricanesInit;
    }
}
