
public class Dealer extends Player
{
    private Deck deck;

    /**
     * Constructor for objects of class Dealer, setting the name to "Dealer" and the stash of money to bet,
     * and initializes a new deck of cards
     */
    public Dealer(int stash,boolean graphic)
    {
        super("Dealer", stash);
        deck = new Deck();
    }

    /**
     * Takes a card from the deck and returns it
     * Used in conjunction with the Player's receiveCard method
     * Example: player.receiveCard(dealer.deal());
     * @return A card from the deck
     */
    public Card deal()
    {
        if (deck.cardsLeftInDeck() != 0)
        {
            return deck.deal();
        }
        return null;
    }

        @Override
        public void receiveCard(Card card, boolean visibility)
        {
            if (visibility)
            {
                hand.add(card);
            }
            else
            {
                card.hide();
                hand.add(card);
            }
            if(hand.size() == 1)
            {
                hand.get(0).hide();
            }
        }

    /**
     * Get a full, shuffled deck back to the dealer
     */
    public void resetDeck()
    {
        deck = new Deck();
    }
}
