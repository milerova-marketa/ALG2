/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.List;

/**
 * Třída typu objektu
 *
 * @author Marketa.Milerova
 */
public class HotelRoom extends Property {

    private static final int N_ROOMS_MAX = 1;

    /**
     * Konstruktor
     */
    public HotelRoom() {
    }

    @Override
    public void setProperty(String name, String destination, double pricePerNightPerPerson, List<Room> rooms, List<Date> dates) {
        if (rooms.size() > N_ROOMS_MAX) {
            throw new IllegalArgumentException("Hotelový pokoj " + name + " nemá více pokojů.");
        }
        super.setProperty(name, destination, pricePerNightPerPerson, rooms, dates);
    }

    @Override
    public String getDetail() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-10s %-20s %-20s %-10d %10.2fKč%n", getClass().getSimpleName(), name, destination, getCapacity(), pricePerNightPerPerson));
        sb.append("    -Dostupné termíny:\n");
        for (int i = 0; i < dates.size(); i++) {
            sb.append("        ").append(i + 1).append(". ").append(dates.get(i)).append("\n");
        }
        sb.append("    -Dispozice pokoje: ").append(rooms.get(0)).append("\n");
        return sb.toString();
    }

}
