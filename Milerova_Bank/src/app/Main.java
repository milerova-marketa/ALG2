/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import bank.Client;
import bank.Company;
import bank.Person;
import java.util.ArrayList;

/**
 *
 * @author Marketa.Milerova
 */
public class Main {

    public static void main(String[] args) {
        ArrayList<Client> clients = new ArrayList();
        clients.add(new Person("Pekar"));
        clients.add(new Person("Svecova"));
        clients.add(new Company("Skoda"));
        clients.get(0).openNewAccount(1000);
        clients.get(0).openNewAccount(500);
        clients.get(1).openNewAccount(1200);
        clients.get(2).openNewAccount(120);
        for (Client client : clients) {
            System.out.println("Jmeno: " + client.callName() + ", bilance vsech"
                    + " uctu: " + client.balanceOnAll() + " Kč.");
        }
    }

}
