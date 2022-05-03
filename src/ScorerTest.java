import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class ScorerTest {
    public ScorerTest()
    {

    }

    @Before
    public void setUp()
    {

    }

    @After
    public void tearDown()
    {

    }

    @Test
    public void isStraightTest()
    {
        ArrayList<Card> hand = new ArrayList();
        hand.add(new Card( Suit.Clubs, 6, true, "six"));
        hand.add(new Card( Suit.Clubs, 2, true, "two"));
        hand.add(new Card( Suit.Clubs, 3, true, "three"));
        hand.add(new Card( Suit.Clubs, 4, true, "four"));
        hand.add(new Card( Suit.Clubs, 5, true, "five"));

        assert(Scorer.isStraight(hand));
    }

    @Test
    public void isFlushTest()
    {
        ArrayList<Card> hand = new ArrayList();
        hand.add(new Card( Suit.Clubs, 6, true, "six"));
        hand.add(new Card( Suit.Clubs, 2, true, "two"));
        hand.add(new Card( Suit.Clubs, 3, true, "three"));
        hand.add(new Card( Suit.Clubs, 4, true, "four"));
        hand.add(new Card( Suit.Clubs, 5, true, "five"));

        assert(Scorer.isFlush(hand));
    }

    @Test
    public void isThreeOfAKindTest()
    {
        ArrayList<Card> hand = new ArrayList();
        hand.add(new Card( Suit.Clubs, 6, true, "six"));
        hand.add(new Card( Suit.Clubs, 2, true, "two"));
        hand.add(new Card( Suit.Hearts, 2, true, "two"));
        hand.add(new Card( Suit.Diamonds, 2, true, "two"));
        hand.add(new Card( Suit.Clubs, 5, true, "five"));

        assert(Scorer.isThreeOfAKind(hand));
    }

    @Test
    public void isFourOfAKindTest()
    {
        ArrayList<Card> hand = new ArrayList();
        hand.add(new Card( Suit.Clubs, 6, true, "six"));
        hand.add(new Card( Suit.Clubs, 2, true, "two"));
        hand.add(new Card( Suit.Spades, 2, true, "two"));
        hand.add(new Card( Suit.Diamonds, 2, true, "two"));
        hand.add(new Card( Suit.Hearts, 2, true, "two"));

        assert(Scorer.isFourOfAKind(hand));
    }

    @Test
    public void isFullHouseTest()
    {
        ArrayList<Card> hand = new ArrayList();
        hand.add(new Card( Suit.Clubs, 6, true, "six"));
        hand.add(new Card( Suit.Clubs, 2, true, "two"));
        hand.add(new Card( Suit.Hearts, 2, true, "two"));
        hand.add(new Card( Suit.Diamonds, 2, true, "two"));
        hand.add(new Card( Suit.Spades, 6, true, "six"));

        assert(Scorer.isFullHouse(hand));
    }

    @Test
    public void isTwoPairTest()
    {
        ArrayList<Card> hand = new ArrayList();
        hand.add(new Card( Suit.Clubs, 6, true, "six"));
        hand.add(new Card( Suit.Clubs, 2, true, "two"));
        hand.add(new Card( Suit.Spades, 2, true, "two"));
        hand.add(new Card( Suit.Diamonds, 3, true, "three"));
        hand.add(new Card( Suit.Hearts, 3, true, "three"));

        assert(Scorer.isTwoPair(hand));
    }

    @Test
    public void isOnePairTest()
    {
        ArrayList<Card> hand = new ArrayList();
        hand.add(new Card( Suit.Clubs, 6, true, "six"));
        hand.add(new Card( Suit.Clubs, 2, true, "two"));
        hand.add(new Card( Suit.Spades, 2, true, "two"));
        hand.add(new Card( Suit.Diamonds, 3, true, "three"));
        hand.add(new Card( Suit.Hearts, 4, true, "four"));

        assert(Scorer.isOnePair(hand));
    }
}
