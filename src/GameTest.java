public class GameTest
{

    public static void main(String[] args)
    {
        System.out.println("Welcome to Poker Casino!");
        System.out.println("");
        System.out.println("  RULES: ");
        System.out.println("	- Player starts with a hand of 5, you can select which cards to redraw and try to get one of the following.");
        System.out.println("	- Royal flush (100x wager): Ace-king-queen-jack-10 all of the same suit (hearts, clubs, spades, or diamonds).");
        System.out.println("	- Straight flush(50x wager): Five consecutive cards of the same suit; for example, 2-3-4-5-6, all of clubs.");
        System.out.println("	- Four of a kind(25x wager): Four cards of the same rank; for example, ace of hearts, ace of spades, ace of clubs, ace of diamonds.");
        System.out.println("	- Full house(15x wager): Three cards of one rank, two cards of another rank; for example, 3 of diamonds, 3 of hearts, 3 of spades, 6 of hearts, 6 of spades.");
        System.out.println("	- Flush(10x wager): Five cards of the same suit; for example, ace, 10, 7, 4, 3, all of diamonds.");
        System.out.println("	- Straight(5x wager): Five consecutive cards of mixed suits; for example, 2 of diamonds, 3 of hearts, 4 of diamonds, 5 of clubs, 6 of spades.");
        System.out.println("	- Three of a kind(3x wager): Three cards of the same rank; for example, 6 of hearts, 6 of clubs, 6 of diamonds.");
        System.out.println("	- Two pair(2x wager): Two cards of one rank, two cards of another rank; for example, ace of spades, ace of hearts, 7 of clubs, 7 of diamonds.");
        System.out.println("	- Pair of jacks or better(1x wager): Two jacks, queens, kings, or aces.");

        System.out.println("");




        // make game
        Game game = new Game();

        // play game
        game.play();

    }

}