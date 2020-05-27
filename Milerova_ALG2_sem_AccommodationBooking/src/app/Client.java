/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.Objects;

/**
 * Třída představující klienta
 *
 * @author Marketa.Milerova
 */
public class Client implements Comparable<Client> {

    private String firstName;
    private String lastName;
    private String nationality;
    private int age;

    /**
     * Konstruktor
     *
     * @param firstName křestní jméno
     * @param lastName příjmení
     * @param nationality národnost
     * @param age věk
     */
    public Client(String firstName, String lastName, String nationality, int age) {
        if (!firstName.matches("[a-zA-ZÀ-ž]+[[ ]*[a-zA-ZÀ-ž]*]*")
                || !lastName.matches("[a-zA-ZÀ-ž]+[[ ]*[a-zA-ZÀ-ž]*]*")
                || !nationality.matches("[a-zÀ-ž]+")
                || !nationality.endsWith("á")) {
            throw new IllegalArgumentException("Neplatné jméno či národnost");
        }
        if (age < 18) {
            throw new IllegalArgumentException("Osoba " + firstName + " " + lastName + " není plnoletá");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.age = age;
    }

    /**
     * Metoda vracející křestní jméno klienta
     *
     * @return křestní jméno
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Metoda vracející příjmení klienta
     *
     * @return příjmení klienta
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Metoda vracející národnost klienta
     *
     * @return národnost
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Metoda vracející věk
     *
     * @return věk
     */
    public int getAge() {
        return age;
    }

    /**
     * Metoda, která vrací zformátované detaily o klientovi
     *
     * @return zformátované detaily
     */
    public String getDetail() {
        return String.format("%-20s %-20s %-20s %-3d", firstName, lastName, nationality, age);
    }

    @Override
    public String toString() {
        return String.format("%-20s %-20s", firstName, lastName);
    }

    @Override
    public int compareTo(Client o) {
        return this.age - o.age;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.firstName);
        hash = 67 * hash + Objects.hashCode(this.lastName);
        hash = 67 * hash + Objects.hashCode(this.nationality);
        hash = 67 * hash + this.age;
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
        final Client other = (Client) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        return Objects.equals(this.lastName, other.lastName);
    }
}
