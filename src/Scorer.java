
import java.util.ArrayList;
import java.util.Collections;

public interface Scorer {

    static int scoreHand(ArrayList<Card> hand)
    {
        ArrayList<Card> handToScore = hand;
        boolean isStraight = isStraight(handToScore);
        boolean isFlush = isFlush(handToScore);
        int score = 0;


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
}
