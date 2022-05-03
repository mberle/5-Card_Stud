
import java.util.ArrayList;
import java.util.Collections;

public interface Scorer {

    static int scoreHand(ArrayList<Card> hand)
    {
        ArrayList<Card> handToScore = hand;
        boolean isStraight = isStraight(handToScore);
        boolean isFlush = isFlush(handToScore);
        CardValueCompare valueComp = new CardValueCompare();
        Collections.sort(handToScore,valueComp);
        if(isStraight && isFlush)
        {
            return 9000 + handToScore.get(0).getValue();
        }
        else if(isFourOfAKind(handToScore))
        {
            return 8000 + handToScore.get(2).getValue();
        }
        else if(isFullHouse(handToScore))
        {
            return 7000 + handToScore.get(2).getValue();
        }
        else if(isFlush)
        {
            return 6000 + handToScore.get(0).getValue();
        }
        else if(isStraight)
        {
            return 5000 + handToScore.get(0).getValue();
        }
        else if(isThreeOfAKind(handToScore))
        {
            return 4000 + handToScore.get(2).getValue();
        }
        else if(isTwoPair(handToScore))
        {
            return 3000 + handToScore.get(1).getValue() + handToScore.get(3).getValue();
        }
        else if(isOnePair(handToScore))
        {
            int pairScore = 0;
            for(int i = 0; i<4; i++)
            {
                if(handToScore.get(i).getValue() == handToScore.get(i+1).getValue())
                {
                    pairScore = handToScore.get(i).getValue();
                }
            }
            return 2000 + pairScore;
        }
        else {
            return 1000 + handToScore.get(0).getValue();
        }

    }

    static boolean isStraight(ArrayList<Card> handToScore)
    {
        boolean isStraight = true;
        CardValueCompare valueComp = new CardValueCompare();
        Collections.sort(handToScore,valueComp);
        for(int i = 4; i > 0; i--)
        {
            if(handToScore.get(i).getValue() - handToScore.get(i-1).getValue() != -1)
            {
                isStraight = false;
            }
        }
        return isStraight;
    }

    static boolean isFlush(ArrayList<Card> handToScore)
    {
        boolean isFlush = true;
        CardSuitCompare suitComp = new CardSuitCompare();
        Collections.sort(handToScore,suitComp);
        if(handToScore.get(0).getSuit() != handToScore.get(4).getSuit())
        {
            isFlush = false;
        }
        return isFlush;
    }

    static boolean isFourOfAKind(ArrayList<Card> handToScore)
    {
        boolean isFourKind = false;
        CardValueCompare valueComp = new CardValueCompare();
        Collections.sort(handToScore,valueComp);
        if(handToScore.get(0).getValue() == handToScore.get(3).getValue())
        {
            isFourKind = true;
        }
        if(handToScore.get(1).getValue() == handToScore.get(4).getValue())
        {
            isFourKind = true;
        }
        return isFourKind;
    }

    static boolean isFullHouse(ArrayList<Card> handToScore)
    {
        boolean isFullHouse = true;
        CardSuitCompare suitComp = new CardSuitCompare();
        Collections.sort(handToScore,suitComp);
        if(handToScore.get(0).getValue() == handToScore.get(2).getValue() && handToScore.get(3).getValue() == handToScore.get(4).getValue())
        {
            isFullHouse = true;
        }
        if(handToScore.get(2).getValue() == handToScore.get(4).getValue() && handToScore.get(0).getValue() == handToScore.get(1).getValue())
        {
            isFullHouse = true;
        }

        return isFullHouse;
    }
    static boolean isThreeOfAKind(ArrayList<Card> handToScore)
    {
        boolean isThreeKind = true;
        CardSuitCompare suitComp = new CardSuitCompare();
        Collections.sort(handToScore,suitComp);
        if(handToScore.get(0).getValue() == handToScore.get(2).getValue())
        {
            isThreeKind = true;
        }
        if(handToScore.get(1).getValue() == handToScore.get(3).getValue())
        {
            isThreeKind = true;
        }
        if(handToScore.get(2).getValue() == handToScore.get(4).getValue())
        {
            isThreeKind = true;
        }
        return isThreeKind;
    }

    static boolean isTwoPair(ArrayList<Card> handToScore)
    {
        boolean isTwoPair = false;
        CardSuitCompare suitComp = new CardSuitCompare();
        Collections.sort(handToScore,suitComp);
        if(handToScore.get(0).getValue() == handToScore.get(1).getValue() && handToScore.get(2).getValue() == handToScore.get(3).getValue())
        {
            isTwoPair = true;
        }
        if(handToScore.get(1).getValue() == handToScore.get(2).getValue() && handToScore.get(3).getValue() == handToScore.get(4).getValue())
        {
            isTwoPair = true;
        }
        if(handToScore.get(0).getValue() == handToScore.get(1).getValue() && handToScore.get(3).getValue() == handToScore.get(4).getValue())
        {
            isTwoPair = true;
        }
        return isTwoPair;
    }

    static boolean isOnePair(ArrayList<Card> handToScore)
    {
        boolean isOnePair = false;
        CardSuitCompare suitComp = new CardSuitCompare();
        Collections.sort(handToScore,suitComp);
        if(handToScore.get(0).getValue() == handToScore.get(1).getValue())
        {
            isOnePair = true;
        }
        if(handToScore.get(1).getValue() == handToScore.get(2).getValue())
        {
            isOnePair = true;
        }
        if(handToScore.get(2).getValue() == handToScore.get(3).getValue())
        {
            isOnePair = true;
        }
        if(handToScore.get(3).getValue() == handToScore.get(4).getValue())
        {
            isOnePair = true;
        }
        return isOnePair;
    }
}
