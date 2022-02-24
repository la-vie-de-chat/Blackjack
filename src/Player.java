import java.util.*;

public class Player {
    private static final Random rand = new Random();
    private static final double hitRatio = 0.5;
    protected final List<Card> cards = new ArrayList<>();

    public Action action(Deck deck) {
        if (rand.nextDouble() < hitRatio) {
            hit(deck.dealCard());
            return Action.Hit;
        } else {
            stand();
            return Action.Stand;
        }
    }

    public int score() {
        Collections.sort(cards, Collections.reverseOrder());
        int score = 0;
        int i = 0;
        while (i < cards.size()) {
            Card cur = cards.get(i);
            if (cur.isFace()) {
                score += 10;
            } else if (!cur.isAce()) {
                score += cur.value();
            } else {
                break; // if card is Ace
            }
            i++;
        }
        int numAces = cards.size() - i; // count the number of Aces the player has
        if (numAces == 0) {
            return score <= 21 ? score : 0;
        }
        int max = numAces + 10;
        int min = numAces;
        if (score + min > 21) {
            return 0; // busted
        } else if (score + max > 21) {
            return score + min;
        } else {
            return score + max;
        }
    }

    public boolean isBusted() { // score > 21
        return score() == 0;
    }

    public void hit(Card card) {
        cards.add(card);
    }

    public void stand() {}

    public boolean isBlackJack() { // an A with any J/Q/K is blackjack
        if (cards.size() != 2) {
            return false;
        }
        Card first = cards.get(0);
        Card second = cards.get(1);
        return (first.isAce() && second.isFace()) || (second.isAce() && first.isFace());
    }

    public void printStatus() {
        System.out.println("score: " + score());
        for (Card card : cards) {
            System.out.println(card.toString());
        }
    }
}
