/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import app.Client;
import app.Date;
import app.Property;
import app.Reservation;
import app.Room;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída parsující text na objekty
 *
 * @author Marketa.Milerova
 */
public class Parser {

    /**
     * Klasický formát pro data
     */
    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    /**
     * Metoda naparsuje text a vytvoří z něj Client
     *
     * @param string info o klientovi
     * @return klient
     */
    public static Client parseClient(String string) {
        String[] infoSeparated = string.split(" +");
        Client c = new Client(Formater.firstLetterToUpperCase(infoSeparated[0]),
                Formater.firstLetterToUpperCase(infoSeparated[1]),
                infoSeparated[2], Integer.parseInt(infoSeparated[3]));
        return c;
    }

    /**
     * Metoda naparsuje text a vytvoří z něj Property
     *
     * @param string info o objektu
     * @return objekt
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static Property parseProperty(String string) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<Room> rooms = new ArrayList();
        List<Date> dates = new ArrayList();
        String[] info = string.split(";");
        String[] roomsInfo = info[4].split(",");
        String[] datesInfo = info[5].split(" ");
        String[] oneRoomInfo;
        int nSingles, nDoubles;
        for (String roomsInfo1 : roomsInfo) {
            oneRoomInfo = roomsInfo1.split(" ");
            nSingles = Integer.parseInt(oneRoomInfo[0]);
            nDoubles = Integer.parseInt(oneRoomInfo[1]);
            rooms.add(new Room(nSingles, nDoubles));
        }
        int[] n = new int[6];
        for (int i = 0; i < datesInfo.length; i += 2) {
            LocalDate start = LocalDate.parse(datesInfo[i], DTF);
            LocalDate end = LocalDate.parse(datesInfo[i + 1], DTF);
            dates.add(new Date(start, end));
        }
        Class c = Class.forName("app." + Formater.firstLetterToUpperCase(info[0]));
        Property p = (Property) c.newInstance();
        p.setProperty(Formater.firstLetterToUpperCase(info[1]),
                Formater.firstLetterToUpperCase(info[2]),
                Double.parseDouble(info[3]), rooms, dates);
        return p;
    }

    /**
     * Metoda naparsuje text a vytvoří z něj Reservation
     *
     * @param string info o rezervaci
     * @return rezervace
     */
    public static Reservation parseReservation(String string) {
        String[] info, clientInfo, dateInfo;
        int[] index;
        Reservation r;
        Property p;
        Client c;
        LocalDate start, end, dateReserved;
        int nPeople, i;
        Date date;
        info = string.split(";");
        if (info.length != 5) {
            throw new IllegalArgumentException("Špatně zadaná data v reservation");
        }
        clientInfo = info[0].split(" +");
        nPeople = Integer.parseInt(info[2].replace(".0", ""));
        dateInfo = info[3].split(" +");
        dateReserved = LocalDate.parse(info[4], DTF);
        index = DataHandler.checkValidity(Formater.firstLetterToUpperCase(clientInfo[0]),
                Formater.firstLetterToUpperCase(clientInfo[1]),
                Formater.firstLetterToUpperCase(info[1]));
        p = DataHandler.properties.get(index[1]);
        c = DataHandler.clients.get(index[0]);
        start = LocalDate.parse(dateInfo[0], DTF);
        end = LocalDate.parse(dateInfo[1], DTF);
        date = new Date(start, end);
        i = p.findDate(date);
        if (i != -1) {
            r = new Reservation(c, p, nPeople, dateReserved, date);
            p.removeDate(i);
            return r;
        } else {
            throw new IllegalArgumentException("Termín " + date.toString() + " neexistuje");
        }
    }

}
