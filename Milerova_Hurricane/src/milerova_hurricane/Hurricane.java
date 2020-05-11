/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milerova_hurricane;

/**
 *
 * @author Marketa.Milerova
 */
public class Hurricane implements Comparable<Hurricane> {

    private int year;
    private String month;
    private int pressure;
    private int velocity;
    private String name;
    private static final double CONVERSION = 1.852;

    public Hurricane(int year, String month, int pressure, int velocity, String name) {
        if (year < 0 || pressure < 0 || velocity < 0) {
            throw new IllegalArgumentException("Nonpositive value inserted");
        }
        this.year = year;
        this.month = month;
        this.pressure = pressure;
        this.velocity = velocity;
        this.name = name;
    }

    public double getVelocityInKmH() {
        return velocity * CONVERSION;
    }

    public int getCategory() {
        double velInKmH = getVelocityInKmH();
        if (velInKmH >= 252) {
            return 5;
        } else if (velInKmH >= 209) {
            return 4;
        } else if (velInKmH >= 178) {
            return 3;
        } else if (velInKmH >= 154) {
            return 2;
        } else if (velInKmH >= 119) {
            return 1;
        }
        return 0;
    }

    @Override
    public int compareTo(Hurricane o) {
        return this.velocity - o.velocity;
    }

    @Override
    public String toString() {
        return String.format("%-4d %-10s %-10d %-20d %-15s", year, month, pressure, velocity, name);
    }

    public int getYear() {
        return year;
    }

    public String getName() {
        return name;
    }

}
