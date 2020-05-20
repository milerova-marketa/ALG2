/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 * Třída chyby, která se zobrazí, pokud již objekt v daném seznamu existuje
 * @author Marketa.Milerova
 */
public class ObjectAlreadyInListException extends Exception{

    /**
     * Konstruktor
     * @param message chybová zpráva
     */
    public ObjectAlreadyInListException(String message){
        super(message);
    }
}
