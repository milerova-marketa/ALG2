/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Třída představující rezervaci
 *
 * @author Marketa.Milerova
 */
public class Reservation implements Comparable<Reservation> {

    private Client client;
    private Property property;
    private int nPeople;
    private int nDays;
    private double price;
    private Date reservedDate;
    private LocalDate dateOfReservation;

    public Reservation(Client client, Property property, int nPeople, LocalDate dateOfReservation, Date reservedDate) {
        if (property.getCapacity() < nPeople) {
            throw new IllegalArgumentException("Překročena kapacita objektu u:" + property.name);
        }
        this.client = client;
        this.property = property;
        this.nPeople = nPeople;
        this.reservedDate = reservedDate;
        this.nDays = reservedDate.getnDays();
        this.dateOfReservation = dateOfReservation;
        this.price = nDays * this.nPeople * property.getPricePerNightPerPerson();
    }

    @Override
    public String toString() {
        return String.format("%-20s %-20s %-10s", client.getLastName(),
                property.getName(),
                dateOfReservation.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }

    public Client getClient() {
        return client;
    }

    public Property getProperty() {
        return property;
    }

    public LocalDate getDateOfReservation() {
        return dateOfReservation;
    }

    public int getnPeople() {
        return nPeople;
    }

    public int getnDays() {
        return nDays;
    }

    public double getPrice() {
        return price;
    }

    public Date getReservedDate() {
        return reservedDate;
    }
    

    public String getDetail() {
        return String.format("%-20s %-20s %-20s %-10d %-10d %-25s %-15s %-20s",
                client.getFirstName(), client.getLastName(),
                property.getName(), nPeople, nDays, reservedDate,
                dateOfReservation.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                String.format("%.2f Kč", price));
    }

    @Override
    public int compareTo(Reservation o) {
        return this.dateOfReservation.compareTo(o.dateOfReservation);
    }

    /**
     * Metoda přidá zpět termín do seznamu termínů
     */
    public void restoreDate() {
        property.addDate(reservedDate);
    }

}
