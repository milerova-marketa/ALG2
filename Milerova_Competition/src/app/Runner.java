/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Marketa.Milerova
 */
public class Runner implements Comparable<Runner> {

    private int number;
    private String firstName;
    private String lastName;
    private LocalTime startTime;
    private LocalTime finishTime;
    public static DateTimeFormatter dtfStart = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static DateTimeFormatter dtfFinish = DateTimeFormatter.ofPattern("HH:mm:ss:SSS");

    public Runner(int number, String firstName, String lastName) {
        this.number = number;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setStartTime(String startTime) {
        this.startTime = LocalTime.parse(startTime, dtfStart);
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = LocalTime.parse(finishTime, dtfFinish);
    }

    public int getNumber() {
        return number;
    }

    public String getStartTimeString() {
        return startTime.format(dtfStart);
    }

    public String getFinishTimeString() {
        return finishTime.format(dtfFinish);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalTime runningTime() {
        long n = finishTime.toNanoOfDay() - startTime.toNanoOfDay();
        return LocalTime.ofNanoOfDay(n);
    }

    @Override
    public String toString() {
        return String.format("%-4d %-10s %-10s %-15s %-15s %-15s",
                number, firstName, lastName, getStartTimeString(),
                getFinishTimeString(), runningTime().format(dtfFinish));
    }

    @Override
    public int compareTo(Runner o) {
        return (this.runningTime().compareTo(o.runningTime()));
    }

}
