public class Player
{
    public Card[] draw(Deck deck)
    {
        Card[] hand = deck.deal();
        return hand;
    }
    public Card redraw(int counter, Deck deck)
    {
        Card card = deck.redeal();
        return card;
    }

}