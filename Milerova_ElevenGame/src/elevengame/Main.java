package elevengame;

import java.util.Scanner;

/**
 *
 * @author Marketa.Milerova
 */
public class Main {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Board board = Board.newBoard();
        while (board.isAnotherPlayPossible()) {
            showBoard(board);
            System.out.println("Vyber karty:");
            String[] selection = sc.nextLine().split(" +");
            while (!board.playAndReplace(selection)) {
                System.out.println("Nevalidní tah");
                selection = sc.nextLine().split(" +");
            }
        }
        if (board.hasWon()) {
            showBoard(board);
            System.out.println("Gratuluji");
        } else {
            showBoard(board);
            System.out.println("Neextistuje další tah. Prohra.");
        }
    }

    private static void displayCards(BoardInterface board) {
        for (int i = 0; i < board.nCards(); i++) {
            System.out.printf("%1d. %10s   ", i + 1, board.getCardDescriptionAt(i));
            if ((i + 1) % 3 == 0) {
                System.out.println("");
            }
        }
    }

    private static void displayDeck(BoardInterface board) {
        System.out.println("V balicku je " + board.getDeckSize() + " karet.");
    }

    private static void showBoard(BoardInterface board) {
        System.out.println("*************** " + board.getName() + " ***************");
        displayCards(board);
        displayDeck(board);
    }

}
