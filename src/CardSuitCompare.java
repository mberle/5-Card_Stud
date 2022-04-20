import java.util.Comparator;

public class CardSuitCompare implements Comparator<Card> {
    public int compare(Card c1, Card c2)
    {
        int c1Suit;
        int c2Suit;
        switch (c1.getSuit())
        {
            case Clubs:
                c1Suit = 1;
                break;
            case Spades:
                c1Suit = 2;
            case Hearts:
                c1Suit = 3;
            case Diamonds:
                c1Suit = 4;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + c1.getSuit());
        }
        switch (c2.getSuit())
        {
            case Clubs:
                c2Suit = 1;
                break;
            case Spades:
                c2Suit = 2;
            case Hearts:
                c2Suit = 3;
            case Diamonds:
                c2Suit = 4;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + c2.getSuit());
        }
            return c1Suit - c2Suit;
    }
}
