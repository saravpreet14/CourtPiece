package Entities.Card;

import java.util.Comparator;

public class HigherCardComparator implements Comparator<Card> {
    Suite trump;

    public HigherCardComparator(Suite trump) {
        this.trump = trump;
    }

    // Returns positive is card2 > card1
    // card1 suite > card2 suite
    public int compare(Card card1, Card card2) {
        if (card1.suite == card2.suite) {
            return card2.cardFace.getPriority() - card1.cardFace.getPriority();
        }
        else if (card2.suite == trump) return 1;
        return -1;
    }
}
