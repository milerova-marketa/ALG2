/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Marketa.Milerova
 */
public abstract class Property {

    /**
     * Název objektu
     */
    protected String name;

    /**
     * Destinace
     */
    protected String destination;

    /**
     * Volné termíny
     */
    protected List<Date> dates;

    /**
     * Seznam pokojů
     */
    protected List<Room> rooms;

    /**
     * Cena za noc na osobu
     */
    protected double pricePerNightPerPerson;

    /**
     * Metoda vrací název objektu
     *
     * @return název objektu
     */
    public String getName() {
        return name;
    }

    /**
     * Metoda vrací destinaci objektu
     *
     * @return destinace
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Metoda vrací cenu za noc na osobu
     *
     * @return cena za noc na osobu
     */
    public double getPricePerNightPerPerson() {
        return pricePerNightPerPerson;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-20s %10.2fKč", name, destination, pricePerNightPerPerson);
    }

    /**
     * Metoda, která vrací zformátovaný detail o objektu
     *
     * @return zformátovaný detail
     */
    public String getDetail() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-10s %-20s %-20s %-10d %10.2fKč", getClass().getSimpleName(), name, destination, getCapacity(), pricePerNightPerPerson));
        sb.append("\n").append("    -Dostupné termíny:\n");
        for (int i = 0; i < dates.size(); i++) {
            sb.append("         " + (i + 1) + ". " + dates.get(i)).append("\n");
        }
        sb.append("    -Pokoje:\n");
        for (int i = 0; i < rooms.size(); i++) {
            sb.append("         Pokoj č." + (i + 1) + ": ");
            sb.append(rooms.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Metoda která přidá termín do seznamu termínů objektu
     *
     * @param date termín, který chceme přidat
     */
    public void addDate(Date date) {
        for (Date date1 : dates) {
            if (date.getStart().isAfter(date1.getStart()) && date.getStart().isBefore(date1.getEnd())
                    || date.getEnd().isAfter(date1.getStart()) && date.getEnd().isBefore(date1.getEnd())) {
                throw new IllegalArgumentException("Kolize termínů - " + date + "(" + name + ")");
            }
        }
        dates.add(date);
    }

    /**
     * Metoda odebírající termín ze seznamu termínů
     *
     * @param date termín, který chceme odebrat
     */
    public void removeDate(Date date) {
        dates.remove(date);
    }

    /**
     * Metoda odebírající termín ze seznamu termínů
     *
     * @param i index na kterém se termín nachází v seznamu
     */
    public void removeDate(int i) {
        dates.remove(i);
    }

    /**
     * Metoda vracející maximální kapacitu objektu
     *
     * @return kapacita
     */
    public int getCapacity() {
        int n = 0;
        for (Room room : rooms) {
            n += room.getCapacity();
        }
        return n;
    }

    /**
     * Metoda inicializující data v objektu
     *
     * @param name název objektu
     * @param destination země, ve které je objekt nachází
     * @param pricePerNightPerPerson cena za noc pro jednu osobu
     * @param rooms seznam pokojů
     * @param dates seznam volných termínů
     */
    public void setProperty(String name, String destination, double pricePerNightPerPerson, List<Room> rooms, List<Date> dates) {
        if (pricePerNightPerPerson < 0) {
            throw new IllegalArgumentException("Nelze zadat zápornou cenu - " + name);
        }
        if (rooms.isEmpty() || dates.isEmpty()) {
            throw new IllegalArgumentException("Nebyly zadány pokoje či termíny - " + name);
        }
        this.name = name;
        this.destination = destination;
        this.dates = dates;
        this.rooms = rooms;
        this.pricePerNightPerPerson = pricePerNightPerPerson;
    }

    /**
     * Metoda vrací index termínu ze seznamu termínů
     *
     * @param date hledaný termín
     * @return index v seznamu
     */
    public int findDate(Date date) {
        for (int i = 0; i < dates.size(); i++) {
            if (dates.get(i).getStart().equals(date.getStart()) && dates.get(i).getEnd().equals(date.getEnd())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Metoda vrací termín na indexu ze seznamu termínů
     *
     * @param i index v seznamu
     * @return termín na indexu
     */
    public Date getDate(int i) {
        return dates.get(i);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.destination);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.pricePerNightPerPerson) ^ (Double.doubleToLongBits(this.pricePerNightPerPerson) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Property other = (Property) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
}
