import java.io.File;
import java.awt.image.*;
import javax.imageio.ImageIO;
/**
 * Write a description of class GraphicalCard here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Card implements Comparable<Card>
{
    private Suit suit;
    private int value;
    private boolean visible;
    private String name = new String();
    private static BufferedImage cardBack;
    private BufferedImage cardFront;


    public Card(Suit suit, int value, boolean visible, String name)
    {
        this.suit = suit;
        if (value >= 1 && value <= 11)
        {
            this.value = value;
        }
        this.visible = visible;
        this.name = name;
        populateCardBack();
        populateCardFront();
    }

    /**
     * Get the card's suit
     * @return The card's suit
     */
    public Suit getSuit()
    {
        return suit;
    }

    /**
     * Get the card's point value
     * @return A number 2 - 11 of the card's point value
     */
    public int getValue()
    {
        return value;
    }

    /**
     * Get the card's name
     * @return The name of the card ("2 - 10, Jack, Queen, King, or Ace")
     */
    public String getName()
    {
        return name;
    }

    /**
     * Report if the card is face-up or face-down
     * @return true if the card is face-up, false if face-down
     */
    public boolean isVisible()
    {
        return visible;
    }

    /**
     * Set the card as visible, so the front of the card is shown
     */
    public void show()
    {
        visible = true;
    }

    /**
     * Set the card as hidden, so the back of the card is shown
     */
    public void hide()
    {
        visible = false;
    }

    /*
     * Display the front or back of the card, based on the visible field's value
     */
    @Override
    public String toString()
    {
        String cardDescription = new String();

        if (visible)
        {
            cardDescription = name + " of " + suit.name();
        }
        else
        {
            cardDescription = "Hidden Card";
        }

        return cardDescription;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        else if (!(obj instanceof Card))
        {
            return false;
        }
        // It's a card, transform it into one
        Card that = (Card) obj;
        // Compare suit, value, and name
        if (this.getValue() == that.getValue() &&
                this.getSuit() == that.getSuit() &&
                this.getName().equals(that.getName()))
        {
            return true;
        }
        return false;
    }

    public String getMemoryAddress()
    {
        return super.toString();
    }

    private void populateCardBack()
    {
        try
        {
            cardBack = ImageIO.read(new File(".\\png\\back.png"));
        }
        catch (Exception e)
        {
            System.out.println("Error: Card Back not processed");
        }
    }

    private void populateCardFront()
    {
        String suitName = "clubs";
        if(suit == Suit.Hearts) suitName = "hearts";
        else if(suit == Suit.Diamonds) suitName = "diamonds";
        else if(suit == Suit.Spades) suitName = "spades";
        try
        {
            cardFront = ImageIO.read(new File(".\\png\\" + name + "_of_" + suitName + ".png"));
        }
        catch (Exception e)
        {
            System.out.println("Error: Card Front not processed");
        }

    }

    public BufferedImage getGraphic()
    {
        if(visible)
            return cardFront;
        else
            return cardBack;
    }


    @Override
    public int compareTo(Card o) {
        return 0;
    }
}
