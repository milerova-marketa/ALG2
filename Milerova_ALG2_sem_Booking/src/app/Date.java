/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Třída představující termín
 *
 * @author Marketa.Milerova
 */
public class Date {

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private LocalDate start;
    private LocalDate end;

    /**
     * Konstruktor
     * @param start začátek termínu
     * @param end konec termínu
     */
    public Date(LocalDate start, LocalDate end) {
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Neplatný termín "
                    + start.format(DTF) + "-" + end.format(DTF)
                    + " - počátek je po konci");
        }
        if (start.isBefore(LocalDate.now()) || end.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Neplatný termín "
                    + start.format(DTF) + "-" + end.format(DTF)
                    + " - je třeba zadat budoucí termín");
        }
        this.start = start;
        this.end = end;
    }

    /**
     * Metoda vrací počátek termínu
     * @return počátek termínu
     */
    public LocalDate getStart() {
        return start;
    }

    /**
     * Metoda vrací konec termínu
     * @return konec termínu
     */
    public LocalDate getEnd() {
        return end;
    }

    /**
     * Metoda vrací počet dní termínu
     *
     * @return počet dní
     */
    public int getnDays() {
        return start.until(end).getDays();
    }

    @Override
    public String toString() {
        return start.format(DTF) + "-" + end.format(DTF);
    }
}
