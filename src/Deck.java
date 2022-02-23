import java.util.*;

public class Deck {
    private static final Random rand = new Random();
    private final List<Card> cards = new ArrayList<>();
    private int dealtIndex = 0;

    public Deck() {
        for (int i = 0; i < 13; i++) {
            for (Suit suit : Suit.values()) {
                cards.add(new Card(i, suit));
            }
        }
    }

    public void shuffle() { // 洗牌
        for (int i = cards.size() - 1; i >= 0; i++) {
            int j = rand.nextInt(i + 1); // return an integer between 0 and i, including i
            Card card1 = cards.get(i);
            Card card2 = cards.get(j);
            cards.set(i, card2);
            cards.set(j, card1);
        }
    }

    private int remainingCards() {
        return cards.size() - dealtIndex;
    }

    public Card dealCard() {
        return remainingCards() == 0 ? null : cards.get(dealtIndex++);
    }
}
