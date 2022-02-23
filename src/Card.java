public class Card implements Comparable<Card> {
    // faceValue: 1 for A, 11 for J, 12 for Q, 13 for K
    private final int faceValue;
    private final Suit suit;

    public Card(int c, Suit s) {
        faceValue = c;
        suit = s;
    }

    public int value() {
        return faceValue;
    }

    public Suit suit() {
        return suit;
    }

    public boolean isAce() { // check if current card is A
        return faceValue == 1;
    }

    public boolean isFace() { // check if current card is 10-score card
        return faceValue >= 10 && faceValue <= 13;
    }

    @Override
    public String toString() {
        return "Card {" + "faceValue = " + faceValue + ", suit = " + suit + "}";
    }

    @Override
    public int compareTo(Card c) {
        return this.faceValue - c.faceValue;
    }
}
