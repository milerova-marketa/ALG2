package utils;

import app.Client;
import app.Property;
import app.Reservation;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * Třída pro třídění seznamů
 *
 * @author Marketa.Milerova
 */
public class Mycomparator {

    /**
     * Metoda vrací setříděný seznam klientů dle parametru: 1 - křestní jméno 2
     * - příjmení 3 - věk
     *
     * @param option parametr třídění
     * @param clients seznam, který je třeba setřídit
     * @return setříděný seznam dle parametru
     */
    public static List<Client> sortClients(int option, List<Client> clients) {
        switch (option) {
            case 1:
                clients.sort((Client o1, Client o2) -> {
                    Collator col = Collator.getInstance(new Locale("cs", "CZ"));
                    return col.compare(o1.getFirstName(), o2.getFirstName());
                });
                break;
            case 2:
                clients.sort((Client o1, Client o2) -> {
                    Collator col = Collator.getInstance(new Locale("cs", "CZ"));
                    return col.compare(o1.getLastName(), o2.getLastName());
                });
                break;
            default:
                throw new IllegalArgumentException("Neplatné třídění");
        }
        return clients;
    }

    /**
     * Metoda vrací setříděný seznam objektů dle parametru: 1 - název 2 -
     * destinace 3 - cena za noc
     *
     * @param option parametr třídení
     * @param properties seznam, který je potřeba setřídit
     * @return setříděný seznam dle parametru
     */
    public static List<Property> sortProperties(int option, List<Property> properties) {
        switch (option) {
            case 1:
                properties.sort((Property o1, Property o2) -> {
                    Collator col = Collator.getInstance(new Locale("cs", "CZ"));
                    return col.compare(o1.getName(), o2.getName());
                });
                break;
            case 2:
                properties.sort((Property o1, Property o2) -> {
                    Collator col = Collator.getInstance(new Locale("cs", "CZ"));
                    return col.compare(o1.getDestination(), o2.getDestination());
                });
                break;
            case 3:
                properties.sort(new Comparator<Property>() {
                    @Override
                    public int compare(Property o1, Property o2) {
                        return (int) (o1.getPricePerNightPerPerson() - o2.getPricePerNightPerPerson());
                    }
                });
                break;
            default:
                throw new IllegalArgumentException("Neplatné třídění");
        }
        return properties;
    }

    /**
     * Metoda vrací setříděný seznam rezervací dle parametru: 1 - příjmení
     * klienta 2 - název objektu 3 - datum rezervace
     *
     * @param option parametr třídění
     * @param reservations seznam, který je třeba setřídit
     * @return setříděný seznam dle parametru
     */
    public static List<Reservation> sortReservations(int option, List<Reservation> reservations) {
        switch (option) {
            case 1:
                reservations.sort((Reservation o1, Reservation o2) -> {
                    Collator col = Collator.getInstance(new Locale("cs", "CZ"));
                    return col.compare(o1.getClient().getLastName(), o2.getClient().getLastName());
                });
                break;
            case 2:
                reservations.sort((Reservation o1, Reservation o2) -> {
                    Collator col = Collator.getInstance(new Locale("cs", "CZ"));
                    return col.compare(o1.getProperty().getName(), o2.getProperty().getName());
                });
                break;
            case 3:
                Collections.sort(reservations);
                break;
            default:
                throw new IllegalArgumentException("Neplatné třídění");
        }
        return reservations;
    }

    /**
     * Metoda vrací setříděný seznam klientů dle parametru: 1 - křestní jméno 2
     * - příjmení 3 - národnost 4 - věk
     *
     * @param option parametr třídění
     * @param clients tříděný seznam
     * @param detail parametr představující, zda se jedná o detailní seznam ne
     * @return setříděný seznam
     */
    public static List<Client> sortClients(int option, List<Client> clients, boolean detail) {
        if (!detail) {
            return sortClients(option, clients);
        }
        switch (option) {
            case 1:
                return sortClients(option, clients);
            case 2:
                return sortClients(option, clients);
            case 3:
                clients.sort((Client o1, Client o2) -> {
                    Collator col = Collator.getInstance(new Locale("cs", "CZ"));
                    return col.compare(o1.getNationality(), o2.getNationality());
                });
                break;
            case 4:
                Collections.sort(clients);
                break;
            default:
                return sortClients(option, clients);
        }
        return clients;
    }

    /**
     * Metoda vrací setříděný seznam objektů dle parametru: 1 - název 2 -
     * destinace 3 - kapacita 4 - cena za noc
     *
     * @param option parametr třídění
     * @param properties tříděný seznam
     * @param detail paramter představující, zda se jedná o detailní seznam ne
     * @return setříděný seznam
     */
    public static List<Property> sortProperties(int option, List<Property> properties, boolean detail) {
        if (!detail) {
            return sortProperties(option, properties);
        }
        switch (option) {
            case 1:
                return sortProperties(option, properties);
            case 2:
                return sortProperties(option, properties);
            case 3:
                properties.sort((Property o1, Property o2) -> o1.getCapacity() - o2.getCapacity());
                break;
            case 4:
                return sortProperties(3, properties);
            default:
                return sortProperties(option, properties);
        }
        return properties;
    }

    /**
     * Metoda vrací setříděný seznam rezervací dle parametru: 1 - křestní jméno
     * klienta 2 - příjmení klienta 3 - název objektu 4 - počet lidí 5 - počet
     * dní 6 - datum rezervace 7 - celková cena
     *
     * @param option parametr třídění
     * @param reservations tříděný seznam
     * @param detail paramter představující, zda se jedná o detailní seznam
     * @return setříděný seznam
     */
    public static List<Reservation> sortReservations(int option, List<Reservation> reservations, boolean detail) {
        if (!detail) {
            return sortReservations(option, reservations);
        }
        switch (option) {
            case 1:
                reservations.sort((Reservation o1, Reservation o2) -> {
                    Collator col = Collator.getInstance(new Locale("cs", "CZ"));
                    return col.compare(o1.getClient().getFirstName(), o2.getClient().getFirstName());
                });
                break;
            case 2:
                return sortReservations(1, reservations);
            case 3:
                return sortReservations(2, reservations);
            case 4:
                reservations.sort((Reservation o1, Reservation o2) -> o1.getnPeople() - o2.getnPeople());
                break;
            case 5:
                reservations.sort((Reservation o1, Reservation o2) -> o1.getnDays() - o2.getnDays());
                break;
            case 6:
                return sortReservations(3, reservations);
            case 7:
                reservations.sort((Reservation o1, Reservation o2) -> (int) (o1.getPrice() - o2.getPrice()));
                break;
            default:
                return sortReservations(option, reservations);
        }
        return reservations;
    }
}
