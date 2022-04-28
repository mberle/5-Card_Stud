
import java.util.ArrayList;
import java.util.Collections;

public interface Scorer {

    static int scoreHand(ArrayList<Card> hand)
    {
        int score = 0;
        if(hand.size() == 5) {
            ArrayList<Card> handToScore = hand;
            boolean isStraight = isStraight(handToScore);
            boolean isFlush = isFlush(handToScore);
        }
        return score;
    }

    static boolean isStraight(ArrayList<Card> handToScore)
    {
        boolean isStraight = true;
        CardValueCompare valueComp = new CardValueCompare();
        Collections.sort(handToScore,valueComp);
        for(int i = 0; i < 4; i++)
        {
            if(handToScore.get(i).getValue() - handToScore.get(i+1).getValue() != -1)
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
        int occOne = 0;
        int occTwo = 0;
        for(Card card : handToScore)
        {
            if(card.getValue() == handToScore.get(0).getValue())
            {
                occOne++;
            }
            else if(card.getValue() == handToScore.get(4).getValue())
            {
                occTwo++;
            }
        }
        if(occOne == 4 || occTwo == 4)
        {
            isFourKind = true;
        }
        return isFourKind;
    }

    static boolean isFullHouse(ArrayList<Card> handToScore)
    {
        boolean isFullHouse = false;
        CardValueCompare valueComp = new CardValueCompare();
        Collections.sort(handToScore,valueComp);
        int occOne = 0;
        int occTwo = 0;
        for(Card card : handToScore)
        {
            if(card.getValue() == handToScore.get(0).getValue())
            {
                occOne++;
            }
            else if(card.getValue() == handToScore.get(4).getValue())
            {
                occTwo++;
            }
        }
        if((occOne == 3 && occTwo == 2) || (occOne == 2 && occTwo == 3))
        {
            isFullHouse = true;
        }
        return isFullHouse;
    }

    static boolean isThreeOfAKind(ArrayList<Card> handToScore)
    {
        boolean isThreeKind = false;
        CardValueCompare valueComp = new CardValueCompare();
        Collections.sort(handToScore,valueComp);
        if(handToScore.get(2).getValue() == handToScore.get(0).getValue())
        {
            isThreeKind = true;
        }
        else if(handToScore.get(2).getValue() == handToScore.get(4).getValue())
        {
            isThreeKind = true;
        }
        else if(handToScore.get(1).getValue() == handToScore.get(3).getValue())
        {
            isThreeKind = true;
        }
        return isThreeKind;
    }

    static boolean isTwoPair(ArrayList<Card> handToScore) {
        boolean isTwoPair = false;
        CardValueCompare valueComp = new CardValueCompare();
        Collections.sort(handToScore, valueComp);
        if (handToScore.get(0).getValue() == handToScore.get(1).getValue()) {
            if (handToScore.get(2).getValue() == handToScore.get(3).getValue() || handToScore.get(3).getValue() == handToScore.get(4).getValue()) {
                isTwoPair = true;
            }
        } else {
            if (handToScore.get(1).getValue() == handToScore.get(2).getValue() && handToScore.get(3).getValue() == handToScore.get(4).getValue()) {
                isTwoPair = true;
            }
        }
        return isTwoPair;
    }

}
