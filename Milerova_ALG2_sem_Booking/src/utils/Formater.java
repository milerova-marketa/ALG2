/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import app.Client;
import app.Property;
import app.Reservation;
import java.util.List;

/**
 * Třída na formátování.
 *
 * @author Marketa.Milerova
 */
public class Formater {

    /**
     * Metoda vrací naformátovaný String určený pro výstup v UI
     *
     * @param list seznam, který chceme naformátovat
     * @return naformatovaný String pro výstup v UI
     */
    public static String print(List list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(String.format("%2d", i + 1)).append(". ").append(list.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Metoda formátuje informace tak, aby je program dokázal interpretovat.
     *
     * @param firstName křestní jméno
     * @param lastName příjmení
     * @param nationality národnost
     * @param age věk
     * @return naformátované informace
     */
    public static String format(String firstName, String lastName, String nationality, String age) {
        StringBuilder sb = new StringBuilder();
        sb.append(firstName).append(" ")
                .append(lastName).append(" ")
                .append(nationality).append(" ")
                .append(age);
        return sb.toString();
    }

    /**
     * Metoda formátuje informace tak, aby je program dokázal interpretovat.
     * Pole stringů rooms a dates je vyloženo na následujícím příkladu: - Pokud
     * rooms má podobu [3 2 0 1], znamená to, že objekt má dva pokoje. V prvním
     * jsou 3 jednolůžka a 2 dvoulůžka. V druhém je pouze jedno dvoulůžko. - V
     * dates se píšou data za sebe ve formátu dd.mm.yyyy, první je vždy
     * počáteční datum termínu, druhé je vždy koncové datum termínu.
     *
     * @param type typ objektu
     * @param name název objektu
     * @param destination země, ve které se obejkt nachází
     * @param pricePerNight cena za noc pro jednoho člověka
     * @param rooms pole stringů reprezentující kolik je v jednotlivých pokojích
     * jednolůžek a dvoulůžek
     * @param dates pole stringů reprezentující volné termíny
     * @return formátovaný string informací
     */
    public static String format(String type, String name, String destination, String pricePerNight, String[] rooms, String[] dates) {
        StringBuilder sb = new StringBuilder();
        type = firstLetterToUpperCase(type);
        sb.append(type).append(";");
        name = firstLetterToUpperCase(name);
        sb.append(name).append(";");
        destination = firstLetterToUpperCase(destination);
        sb.append(destination).append(";");
        sb.append(pricePerNight).append(";");
        for (int i = 0; i < rooms.length; i = i + 2) {
            sb.append(rooms[i]).append(" ").append(rooms[i + 1]).append(",");
        }
        sb.append(";");
        for (int i = 0; i < dates.length; i++) {
            sb.append(dates[i]).append(" ");
        }
        return sb.toString();
    }

    /**
     * Metoda mění první písmeno ve slově na velké.
     *
     * @param string vstupní slovo
     * @return slovo s velkým písmenem na začátku
     */
    public static String firstLetterToUpperCase(String string) {
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }

    /**
     * Metoda formátující seznam objektů pro soubor či detailní výpis do UI
     *
     * @param list seznam objektů
     * @return zformátovaný String
     */
    public static String printProperty(List<Property> list) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-10s %-20s %-20s %-10s %13s%n",
                "Typ", "Název", "Destinace", "Kapacita", "Cena za noc"));
        for (Property property : list) {
            sb.append(property.getDetail());
        }
        return sb.toString();
    }

    /**
     * Metoda formátující seznam klientů pro soubor či detailní výpis do UI
     *
     * @param list seznam klientů
     * @return zformátovaný String
     */
    public static String printClient(List<Client> list) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-20s %-20s %-20s %-3s%n",
                "Křestní jméno", "Příjmení", "Národnost", "Věk"));
        for (Client client : list) {
            sb.append(client.getDetail()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Metoda formátující seznam rezervací pro soubor či detailní výpis do UI
     *
     * @param list seznam rezervací
     * @return zformátovaný String
     */
    public static String printReservation(List<Reservation> list) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-20s %-20s %-20s %-10s %-10s %-25s %-15s %-20s%n",
                "Křestní jméno", "Přijmení", "Název objektu", "Počet lidí",
                "Počet dní", "Termín", "Datum rezervace", "Celková cena"));
        for (Reservation reservation : list) {
            sb.append(reservation.getDetail()).append("\n");
        }
        return sb.toString();
    }
}
