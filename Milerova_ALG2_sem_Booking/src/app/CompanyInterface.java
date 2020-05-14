/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

/**
 *
 * @author Marketa.Milerova
 */
public interface CompanyInterface {

    /**
     * Metoda vracející výčet prvků v seznamu
     *
     * @param name název seznamu
     * @return výčet prvků
     */
    public String getList(String name);

    /**
     * Metoda vracející detail prvku na daném indexu v seznamu
     *
     * @param index
     * @param name název seznamu
     * @return detail prvku
     */
    public String getDetail(int index, String name);

    /**
     * Metoda seřadí seznam dle parametru
     *
     * @param option parametr řazení
     * @param name jméno seznamu
     * @return sežazený seznam
     */
    public String sort(int option, String name);

    /**
     * Metoda načte všechna data ze souborů
     *
     * @param option typ souboru 1-txt 2-dat
     * @return informace o načtení
     */
    public String loadData(int option);

    /**
     * Metoda testující, zda seznam existuje a není prázdný
     *
     * @param name název seznamu
     * @return informca zda seznam existuje a není prázdný
     */
    public boolean canWe(String name);

    /**
     * Metoda která vytvoří detailní seznam a vypíše ho buď na obrazovku nebo do
     * souboru
     *
     * @param listChoice výběr seznamu
     * @param sortChoice výběr řazení
     * @param typeChoice výběr souboru/obrazovka
     * @return informace o vytvoření souboru či výčet položek v seznamu
     */
    public String saveData(int listChoice, int sortChoice, int typeChoice);

    /**
     * Metoda vytvoří nového klienta a přidá ho do seznamu
     *
     * @param info potřebné informace o klientovi
     * @return informace o přidání klienta
     */
    public String addClient(String info);

    /**
     * Metoda vytvoří nový objekt a přidá ho do seznamu
     *
     * @param info potřebné informace o objektu
     * @return informace o přidání objektu
     */
    public String addProperty(String info);

    /**
     * Metoda vytvoří novou rezervaci a přidá ji do seznamu
     *
     * @param indexes potřebné informace o rezervaci
     * @return informace o přidání rezervace
     */
    public String addReservation(int[] indexes);

    /**
     * Metoda odstraní rezervaci na daném indexu ze seznamu;
     *
     * @param index
     * @return infomace o odstranění
     */
    public String removeReservation(int index);

}
