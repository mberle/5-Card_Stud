import java.util.ArrayList;

public class Player
{
    protected ArrayList<Card> hand;
    private String name;
    private int stash;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        // initialise instance variables
        hand = new ArrayList<Card>();
        name = "Player";
        setStash(500);
    }

    /**
     * Overloaded constructor for custom name and amount of money to bet with
     */
    public Player(String name, int stash)
    {
        hand = new ArrayList<Card>();
        this.name = name;
        setStash(stash);
    }

    /**
     * Get a card from the dealer
     * @param card A card from the deck of cards
     * @param visibility Set the card face-up (true) or face-down (false)
     */
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
    }

    /**
     * Set the amount of money the player has available to bet, must be >= 0
     */
    public void setStash(int stash)
    {
        if (stash >= 0)
        {
            this.stash = stash;
        }
    }

    /**
     * Get the amount of money the player has available to bet
     * @return The amount of money the player has to bet
     */
    public int getStash()
    {
        return stash;
    }

    /**
     * Clear the player's hand, for use after one round of Blackjack
     */
    public void clearHand()
    {
        hand.clear();
    }

    /**
     * Score the cards in the player's hand for Blackjack
     * If the score is more than 21, the method reduces the score by 10 for each Ace present
     * until the score is less than or equal to 21 or all cards are examined
     * @return The score of all cards in the player's hand
     */
    public int scoreHand()
    {
        return Scorer.scoreHand(hand);
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<Card> getHand()
    {
        return hand;
    }

    /**
     * Flips all cards face-up
     */
    public void showAllCards()
    {
        for (Card card : hand)
        {
            card.show();
        }
    }

    public void setName(String newName)
    {
        name = newName;
    }

    /*
     * Return a string representing the player and the amount of money they have available
     */
    @Override
    public String toString()
    {
        String player = name + " has $" + stash + "\n";
        player += "Current points: " + scoreHand() + "\n";
        for(Card c : hand)
        {
            player += c.toString() + "\n";
        }
        return player;
    }
}
