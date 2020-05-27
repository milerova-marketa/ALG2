/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import utils.Mycomparator;
import utils.DataHandler;
import utils.FileHandler;
import utils.Formater;
import utils.ObjectAlreadyInListException;
import utils.Parser;

/**
 * Třída, která zprostředkovává komunikaci mezi uživatelem a seznamy dat
 *
 * @author Marketa.Milerova
 */
public class Company implements CompanyInterface {

    private List<Client> clients = new ArrayList();
    private List<Reservation> reservations = new ArrayList();
    private List<Property> properties = new ArrayList();

    /**
     * Konstruktor
     */
    public Company() {
    }

    @Override
    public String getList(String name) {
        if (!canWe(name)) {
            return "Seznam je prázdný\n";
        }
        switch (name) {
            case "client":
                return String.format("    %-20s %-20s%n",
                        "Křestní jméno", "Příjmení")
                        + Formater.print(clients);
            case "property":
                return String.format("    %-20s %-20s %12s%n",
                        "Název", "Destinace", "Cena za noc")
                        + Formater.print(properties);
            case "reservation":
                return String.format("    %-20s %-20s %-10s%n",
                        "Příjmení klienta", "Název objektu", "Datum rezervace")
                        + Formater.print(reservations);
            default:
                return "Seznam nenalezen\n";
        }
    }

    @Override
    public String getDetail(int index, String name) {
        switch (name) {
            case "client":
                if (index < 1 || index > clients.size()) {
                    return "Zadáno špatné číslo u klienta\n";
                }
                return String.format("%-20s %-20s %-20s %-3s%n",
                        "Křestní jméno", "Příjmení", "Národnost", "Věk")
                        + clients.get(index - 1).getDetail();
            case "property":
                if (index < 1 || index > properties.size()) {
                    return "Zadáno špatné číslo u objektu\n";
                }
                return String.format("%-10s %-20s %-20s %-10s %13s%n",
                        "Typ", "Název", "Destinace", "Kapacita", "Cena za noc")
                        + properties.get(index - 1).getDetail();

            case "reservation":
                if (index < 1 || index > reservations.size()) {
                    return "Zadáno špatné číslo u rezervace\n";
                }
                return String.format("%-20s %-20s %-20s %-10s %-10s %-25s %-15s %-20s%n",
                        "Křestní jméno", "Přijmení", "Název objektu", "Počet lidí",
                        "Počet dní", "Termín", "Datum rezervace", "Celková cena")
                        + reservations.get(index - 1).getDetail();
        }
        return "Neplatný seznam\n";
    }

    @Override
    public String sort(int option, String name) {
        try {
            switch (name) {
                case "client":
                    clients = Mycomparator.sortClients(option, clients);
                    break;
                case "property":
                    properties = Mycomparator.sortProperties(option, properties);
                    break;
                case "reservation":
                    reservations = Mycomparator.sortReservations(option, reservations);
                    break;
                default:
                    return "Seznam neexistuje";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "";
    }

    @Override
    public String loadData(int option) {
        String log = "";
        try {
            switch (option) {
                case 1:
                    log = DataHandler.updateList(clients, properties, reservations, ".txt");
                    break;
                case 2:
                    FileHandler.createBinaries();
                    log = DataHandler.updateList(clients, properties, reservations, ".dat");
                    break;
                case 3:
                    log = DataHandler.updateList(clients, properties, reservations, ".xlsx");
                    break;
                default:
                    return "Nevalidní volba typu souboru";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        if (log.isEmpty()) {
            return "Data načtena";
        }
        return "Kolize:\n" + log;
    }

    @Override
    public boolean canWe(String name) {
        switch (name) {
            case "property":
                return (properties != null && !properties.isEmpty());
            case "client":
                return (clients != null && !clients.isEmpty());
            case "reservation":
                return (reservations != null && !reservations.isEmpty());
            default:
                return false;
        }
    }

    @Override
    public String saveData(int listChoice, int sortChoice, int typeChoice) {
        String string = "";
        String nameFile;
        try {
            DataHandler.updateList(clients, properties, reservations, "");
            switch (listChoice) {
                case 1:
                    clients = Mycomparator.sortClients(sortChoice, clients, true);
                    if (typeChoice != 3) {
                        string = Formater.printClient(clients);
                    }
                    break;
                case 2:
                    properties = Mycomparator.sortProperties(sortChoice, properties, true);
                    if (typeChoice != 3) {
                        string = Formater.printProperty(properties);
                    }
                    break;
                case 3:
                    reservations = Mycomparator.sortReservations(sortChoice, reservations, true);
                    if (typeChoice != 3) {
                        string = Formater.printReservation(reservations);
                    }
                    break;
                default:
                    return "Chyba výběru seznamu";
            }
            if (typeChoice == 1) {
                return string;
            } else {
                nameFile = utils.FileHandler.createFile(listChoice, typeChoice, string);
                return "Soubor " + nameFile + " vytvořen";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public String addClient(String firstName, String lastName, String nationality, String age) {
        String info = Formater.format(firstName, lastName, nationality, age);
        try {
            Client c = Parser.parseClient(info);
            if (!DataHandler.checkDuplicate(clients, c)) {
                clients.add(c);
            } else {
                throw new ObjectAlreadyInListException("Klient " + c.getFirstName() + " " + c.getLastName() + " již existuje");
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Klient přidán";
    }

    @Override
    public String addProperty(String type, String name, String destination, String pricePerNight, String[] rooms, String[] dates) {
        String info = Formater.format(type, name, destination, pricePerNight, rooms, dates);
        try {
            Property p = Parser.parseProperty(info);
            if (!DataHandler.checkDuplicate(properties, p)) {
                properties.add(p);
            } else {
                throw new ObjectAlreadyInListException("Objekt " + p.getName() + " již existuje");
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException("Nepodařilo se naparsovat objekt - " + e.getMessage());
        } catch (Exception ex) {
            return ex.getMessage();
        }
        return "Objekt přidán";
    }

    @Override
    public String addReservation(int[] indexes) {
        Reservation r;
        Property p;
        Client c;
        int nPeople = indexes[3], i;
        Date date;
        try {
            p = properties.get(indexes[1] - 1);
            c = clients.get(indexes[0] - 1);
            date = p.getDate(indexes[2] - 1);
            i = p.findDate(date);
            r = new Reservation(c, p, nPeople, LocalDate.now(), date);
            p.removeDate(i);
            reservations.add(r);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Rezervace přidána";
    }

    @Override
    public String removeReservation(int index
    ) {
        if (index < 1 || index > reservations.size()) {
            return "Rezervace nenalezena";
        } else {
            reservations.get(index - 1).restoreDate();
            reservations.remove(index - 1);
            return "Rezervace odebrána";
        }
    }

}
