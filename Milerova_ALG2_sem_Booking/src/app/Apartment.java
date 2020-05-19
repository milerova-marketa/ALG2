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
public class Apartment extends Property {

    private static final int N_ROOMS_MAX = 4;

    public Apartment() {
    }

    @Override
    public void setProperty(String name, String destination, double pricePerNightPerPerson, List<Room> rooms, List<Date> dates) {
        if (rooms.size() > N_ROOMS_MAX) {
            throw new IllegalArgumentException("Překročen maximální počet pokojů - " + name);
        }
        super.setProperty(name, destination, pricePerNightPerPerson, rooms, dates); //To change body of generated methods, choose Tools | Templates.
    }

}
