import java.util.Comparator;

public class CardValueCompare implements Comparator<Card> {
    public int compare(Card c1, Card c2)
    {
            if( c1.getValue() >= c2.getValue()) {
                return c1.getValue() - c2.getValue();
            }
            else {
                return c2.getValue() - c1.getValue();
            }
    }
}
