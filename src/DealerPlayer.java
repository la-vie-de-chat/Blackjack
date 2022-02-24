public class DealerPlayer extends Player {
    private static final int dealerMustHit = 17;
    private Player player;

    public DealerPlayer(Player player) {
        this.player = player;
    }

    public Action action(Deck deck) {
        if (score() < dealerMustHit) {
            System.out.println("Dealer must hit under 17");
            printStatus();
            hit(deck.dealCard());
            return Action.Hit;
        } else { // dealer player's score >= 17
            if (score() > this.player.score()) {
                stand();
                return Action.Stand;
            } else {
                // if the probability of next card will hit < hit ratio --> return Action.Hit
                // otherwise --> return Action.Stand
                return super.action(deck);
            }
        }
    }
}
