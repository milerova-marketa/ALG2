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

    public Company() {
    }

    @Override
    public String getList(String text) {
        if (!canWe(text)) {
            return "Seznam je prázdný\n";
        }
        switch (text) {
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
    public String getDetail(int index, String text) {
        switch (text) {
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
                return String.format("%-10s %-20s %-20s %-10s %12s%n",
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
    public String sort(int option, String text) {
        try {
            switch (text) {
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
                    log = DataHandler.updateList(clients, properties, reservations,".txt");
                    break;
                case 2:
                    FileHandler.createBinaries();
                    log = DataHandler.updateList(clients, properties, reservations,".dat");
                    break;
                case 3:
                    log = DataHandler.updateList(clients, properties, reservations,".xlsx");
                    break;
                default:
                    return "Nevalidní volba typu souboru";
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | IOException e) {
            return e.getMessage();
        }
        if (log.isEmpty()) {
            return "Data načtena";
        }
        return "Kolize:\n" + log;
    }

    @Override
    public boolean canWe(String string) {
        switch (string) {
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
        String string;
        String nameFile;
        try {
            switch (listChoice) {
                case 1:
                    clients = Mycomparator.sortClients(sortChoice, clients, true);
                    string = Formater.printClient(clients);
                    break;
                case 2:
                    properties = Mycomparator.sortProperties(sortChoice, properties, true);
                    string = Formater.printProperty(properties);
                    break;
                case 3:
                    reservations = Mycomparator.sortReservations(sortChoice, reservations, true);
                    string = Formater.printReservation(reservations);
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
    public String addClient(String info) {
        try {
            Client c = Parser.parseClient(info);
            if (!DataHandler.checkDuplicate(clients, c)) {
                clients.add(c);
            } else {
                throw new ObjectAlreadyInListException("Klient" + c.getFirstName() + " " + c.getLastName() + "již existuje");
            }
        } catch (ObjectAlreadyInListException e) {
            return e.getMessage();
        }
        return "Klient přidán";
    }

    @Override
    public String addProperty(String string) {
        try {
            Property p = Parser.parseProperty(string);
            if (!DataHandler.checkDuplicate(properties, p)) {
                properties.add(p);
            } else {
                throw new ObjectAlreadyInListException("Klient" + p.getName() + "již existuje");
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException("Nepodařilo se naparsovat objekt - " + e.getMessage());
        } catch (ObjectAlreadyInListException ex) {
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
    public String removeReservation(int index) {
        if (index < 1 || index > reservations.size()) {
            return "Reservace nenalezena";
        } else {
            reservations.get(index - 1).restoreDate();
            reservations.remove(index - 1);
            return "Reservace odebrána";
        }
    }

}
