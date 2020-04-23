package elevengame;

/**
 *
 * @author Marketa.Milerova
 */
public class Board implements BoardInterface {

    private Card[] cards;
    private Deck deck;

    private Board(Card[] cards, Deck deck) {
        this.cards = cards;
        this.deck = deck;
    }

    public String getName() {
        return "Hra jedenactka";
    }

    public int getDeckSize() {
        return deck.getnCards();
    }

    public int nCards() {
        return cards.length;
    }

    public String getCardDescriptionAt(int i) {
        if(cards[i]==null){
            return "               ";
        }
        return cards[i].toString();
    }

    public boolean playAndReplace(String[] selection) {
        int nPoints = 0;
        int sel[] = new int[selection.length];
        for (int i = 0; i < selection.length; i++) {
            sel[i] = Integer.parseInt(selection[i]) - 1;
        }
        if (checkSelection(sel)) {
            for (int i : sel) {
                nPoints += cards[i].getnPoints();
            }
        } else {
            return false;
        }
        if ((nPoints == 11 && sel.length == 2) || (nPoints == 0 && sel.length == 3)) {
            for (int i : sel) {
                cards[i] = deck.dealCard();
            }
            return true;
        }
        return false;
    }

    public boolean isAnotherPlayPossible() {
        int nJQK = 0;
        int nEmpty = 0;
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == null) {
                nEmpty++;
            } else {
                if (cards[i].getnPoints() == 0) {
                    nJQK++;
                }
                for (int j = i + 1; j < cards.length; j++) {
                    if (i < cards.length - 1 && cards[j] != null && cards[i].getnPoints() + cards[j].getnPoints() == 11) {
                        return true;
                    }
                }
            }
        }
        return nJQK > 2;
    }

    public boolean hasWon() {
        return deck.getnCards() == 0;
    }

    private boolean checkSelection(int sel[]) {
        for (int i : sel) {
            if (i < 0 || i > 8) {
                return false;
            }
        }
        return true;
    }

    public static Board newBoard() {
        Deck deck = Deck.fullDeck();
        deck.shuffle();
        Card[] cards = new Card[9];
        for (int i = 0; i < cards.length; i++) {
            cards[i] = deck.dealCard();
        }
        return new Board(cards, deck);
    }
}
