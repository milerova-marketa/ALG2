/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevengame;

/**
 *
 * @author Marketa.Milerova
 */
public interface BoardInterface {
   
    public boolean isAnotherPlayPossible();

    public String getName();

    public boolean playAndReplace(String[] selection);

    public boolean hasWon();

    public int nCards();

    public int getDeckSize();

    public String getCardDescriptionAt(int i);

}
