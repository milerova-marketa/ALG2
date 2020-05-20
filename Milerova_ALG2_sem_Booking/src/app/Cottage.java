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
public class Cottage extends Property {

    private static final double MAX_PRICE = 4000;

    /**
     * Konstruktor
     */
    public Cottage() {
    }

    @Override
    public void setProperty(String name, String destination, double pricePerNightPerPerson, List<Room> rooms, List<Date> dates) {
        if(pricePerNightPerPerson > MAX_PRICE){
            throw new IllegalArgumentException("Překročena maximální cena za noc u: " + name);
        }
        super.setProperty(name, destination, pricePerNightPerPerson, rooms, dates); //To change body of generated methods, choose Tools | Templates.
    }

}
