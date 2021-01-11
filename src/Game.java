import java.util.Arrays;
import java.util.Scanner;

public class Game
{
    private final int HAND_SIZE = 5;
    private int again = 1;

    Scanner scan = new Scanner(System.in);
    Deck deck = new Deck();
    Player player = new Player();
    Card[] hand;

    double chips = 25;
    double bet = -1;

    public void play()
    {


        while (again == 1)
        {
            while (chips > 0) {

                do {

                    System.out.println("      You have " + chips + " chips, how much do you want to bet?");
                    bet = scan.nextDouble();

                    if (bet > chips) {
                        System.out.println("You can't bet more than 25");
                    } else if (bet < 0) {
                        System.out.println("Invalid Amount");
                    }
                } while (bet < 1 || bet > chips);


                deck.fillDeck();
                deck.shuffle();
                hand = player.draw(deck);
                Arrays.sort(hand);

                this.checkHand();
                hand = this.redraw();

                this.checkHand();

                Arrays.sort(hand);

                this.evaluate();

                this.again();
            }
        }
        System.out.println("Thanks for playing! =)");
    }

    public void makeHand()
    {
        hand[0].rank = 1;
        hand[1].rank = 2;
        hand[2].rank = 3;
        hand[3].rank = 4;
        hand[4].rank = 5;

        hand[0].suit = 1;
        hand[1].suit = 1;
        hand[2].suit = 1;
        hand[3].suit = 1;
        hand[4].suit = 1;
    }

    public void checkHand()
    {
        for (int handCounter = 0; handCounter < HAND_SIZE; handCounter++)
        {
            this.display(hand[handCounter]);
        }
    }

    public Card[] redraw()
    {
        for (int counter = 0; counter < 5; counter++)
        {
            System.out.print("Redraw card " + (counter + 1) + "?" +
                    " (1 for yes, 0 for no)");
            int answer = scan.nextInt();
            if (answer == 1)
            {
                hand[counter] = player.redraw(counter, deck);
            }
        }
        deck.refreshDeckPosition();
        return hand;
    }


    public void evaluate()
    {
        if (this.royalFlush() == 1)
        {
            System.out.println("You have a royal flush!");
            chips = chips - bet;
            chips = chips + (bet * 100);
            System.out.println("You currently have " + chips + "chips.");
        }
        else if (this.straightFlush() == 1)
        {
            System.out.println("You have a straight flush!");
            chips = chips - bet;
            chips = chips + (bet * 50);
            System.out.println("You currently have " + chips + "chips.");
        }
        else if (this.fourOfaKind() == 1)
        {
            System.out.println("You have four of a kind!");
            chips = chips - bet;
            chips = chips + (bet * 25);
            System.out.println("You currently have " + chips + "chips.");
        }
        else if (this.fullHouse() == 1)
        {
            System.out.println("You have a full house!");
            chips = chips - bet;
            chips = chips + (bet * 15);
            System.out.println("You currently have " + chips + "chips.");
        }
        else if (this.flush() == 1)
        {
            System.out.println("You have a flush!");
            chips = chips - bet;
            chips = chips + (bet * 10);
            System.out.println("You currently have " + chips + "chips.");
        }
        else if (this.straight() == 1)
        {
            System.out.println("You have a straight!");
            chips = chips - bet;
            chips = chips + (bet * 5);
            System.out.println("You currently have " + chips + "chips.");
        }
        else if (this.triple() == 1)
        {
            System.out.println("You have a triple!");
            chips = chips - bet;
            chips = chips + (bet * 3);
            System.out.println("You currently have " + chips + "chips.");
        }
        else if (this.twoPairs() == 1)
        {
            System.out.println("You have two pairs!");
            chips = chips - bet;
            chips = chips + (bet * 2);
            System.out.println("You currently have " + chips + "chips.");
        }
        else if (this.pair() == 1)
        {
            System.out.println("You have a pair!");
            chips = chips - bet;
            chips = chips + (bet * 2);
            System.out.println("You currently have " + chips + "chips.");

        }
        else
        {
            int highCard = this.highCard();
            System.out.println("You lost this round...");
            chips = chips - bet;
            System.out.println("You currently have " + chips + "chips.");
        }
    }

    public int royalFlush()
    {
        if (hand[0].rank == 1 && hand[1].rank == 10 && hand[2].rank == 11 &&
                hand[3].rank == 12 && hand[4].rank == 13)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    public int straightFlush()
    {
        for (int counter = 1; counter < 5; counter++)
        {
            if (hand[0].suit != hand[counter].suit)
            {
                return 0;
            }
        }
        for (int counter2 = 1; counter2 < 5; counter2++)
        {
            if (hand[counter2 - 1].rank != (hand[counter2].rank - 1))
            {
                return 0;
            }

        }
        return 1;

    }

    public int fourOfaKind()
    {
        if (hand[0].rank != hand[3].rank && hand[1].rank != hand[4].rank)
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }

    public int fullHouse()
    {
        int comparison = 0;
        for (int counter = 1; counter < 5; counter++)
        {
            if (hand[counter - 1].rank == hand[counter].rank)
            {
                comparison++;
            }
        }
        if (comparison == 3)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    public int flush()
    {
        for (int counter = 1; counter < 5; counter++)
        {
            if (hand[0].suit != hand[counter].suit)
            {
                return 0;
            }
        }
        return 1;
    }

    public int straight()
    {
        for (int counter2 = 1; counter2 < 5; counter2++)
        {
            if (hand[counter2 - 1].rank != (hand[counter2].rank - 1))
            {
                return 0;
            }

        }
        return 1;
    }

    public int triple()
    {
        if (hand[0].rank == hand[2].rank || hand[2].rank == hand[4].rank)
        {
            return 1;
        }
        return 0;
    }

    public int twoPairs()
    {
        int check = 0;
        for(int counter = 1; counter < 5; counter++)
        {
            if (hand[counter - 1].rank == hand[counter].rank)
            {
                check++;
            }
        }
        if (check == 2)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    public int pair()
    {
        int check = 0;
        for(int counter = 1; counter < 5; counter++)
        {
            if (hand[counter - 1].rank == hand[counter].rank)
            {
                check++;
            }
        }
        if (check == 1)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    public int highCard()
    {
        int highCard = 0;
        for (int counter = 0; counter < 5; counter++)
        {
            if (hand[counter].rank > highCard)
            {
                highCard = hand[counter].rank;
            }
        }
        return highCard;
    }

    public void again()
    {
        System.out.print("Play again? (1 for yes, 0 for no)");
        again = scan.nextInt();
    }

    public void display(Card card)
    {
        if (card.rank == 1)
        {
            System.out.print("Ace of ");
        }
        if (card.rank == 2)
        {
            System.out.print("Two of ");
        }
        if (card.rank == 3)
        {
            System.out.print("Three of ");
        }
        if (card.rank == 4)
        {
            System.out.print("Four of ");
        }
        if (card.rank == 5)
        {
            System.out.print("Five of ");
        }
        if (card.rank == 6)
        {
            System.out.print("Six of ");
        }
        if (card.rank == 7)
        {
            System.out.print("Seven of ");
        }
        if (card.rank == 8)
        {
            System.out.print("Eight of ");
        }
        if (card.rank == 9)
        {
            System.out.print("Nine of ");
        }
        if (card.rank == 10)
        {
            System.out.print("Ten of ");
        }
        if (card.rank == 11)
        {
            System.out.print("Jack of ");
        }
        if (card.rank == 12)
        {
            System.out.print("Queen of ");
        }
        if (card.rank == 13)
        {
            System.out.print("King of ");
        }
        if (card.suit == 1)
        {
            System.out.print("Spades");
            System.out.println();
        }
        if (card.suit == 2)
        {
            System.out.print("Hearts");
            System.out.println();
        }
        if (card.suit == 3)
        {
            System.out.print("Diamonds");
            System.out.println();
        }
        if (card.suit == 4)
        {
            System.out.print("Clubs");
            System.out.println();
        }

    }
}