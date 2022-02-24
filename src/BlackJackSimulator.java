public class BlackJackSimulator {
    private Deck deck;
    private Player player;
    private Player dealer;

    public BlackJackSimulator() {
        deck = new Deck();
        deck.shuffle();
        player = new Player();
        dealer = new DealerPlayer(player);
    }

    private void printStatus() {
        System.out.print("Player");
        player.printStatus();
        System.out.print("Dealer");
        dealer.printStatus();
    }

    public void simulate() {
        player.hit(deck.dealCard()); // player always goes first
        dealer.hit(deck.dealCard());
        player.hit(deck.dealCard());
        dealer.hit(deck.dealCard());
        determineWinner();
    }

    private void determineWinner() {
        System.out.println("---- Initialization ----");
        System.out.println("---- Check Black Jack ----");
        if (player.isBlackJack()) {
            if (dealer.isBlackJack()) {
                System.out.print("**Draw");
            } else {
                player.printStatus();
                System.out.println("**Player Black Jack Wins");
            }
            return;
        } 
        if (dealer.isBlackJack()) {
            dealer.printStatus();
            System.out.println("**Dealer Black Jack Wins");
            return;
        }

        System.out.println("---- Player Round ----");
        while (player.action(this.deck) == Action.Hit) {
            if (player.isBusted()) {
                System.out.println("**Dealer Wins");
                printStatus();
                return;
            }
        }

        System.out.println("---- Dealer Round ----");
        while (dealer.action(this.deck) == Action.Hit) {
            if (dealer.isBusted()) {
                System.out.println("**Player Wins");
                printStatus();
                return;
            }
        }
        // finally, both players are not black jack or busted, compare their scores
        if (player.score() > dealer.score()) {
            System.out.println("**Player Wins");
        } else if (player.score() < dealer.score()) {
            System.out.println("**Dealer Wins");
        } else {
            System.out.println("**Draw");
        }
    }

    // a simple test
    public static void main(String[] args) {
        BlackJackSimulator sim = new BlackJackSimulator();
        sim.simulate();
    }
}
