package Entities.Card;

import java.util.ArrayList;
import java.util.List;

public class CardUtility {
    private Suite trump;
    private HigherCardComparator higherCardComparator;

    public CardUtility(Suite trump) {
        this.trump = trump;
        higherCardComparator = new HigherCardComparator(trump);
    }

    public Card max(Card card1, Card card2) {
        if (higherCardComparator.compare(card1, card2) > 0) return card2;
        return card1;
    }

    public Card min(Card card1, Card card2) {
        if (higherCardComparator.compare(card1, card2) < 0) return card2;
        return card1;
    }

    public boolean hasSuite(List<Card> cards, Suite suite) {
        for (Card card: cards) {
            if (card.suite == suite) return true;
        }
        return false;
    }

    public Card getMaxFaceCard(List<Card> cards) {
        Card maxCard = cards.get(0);
        for (Card card: cards) {
            if (card.cardFace.getPriority() > maxCard.cardFace.getPriority()) {
                maxCard = card;
            }
        }
        return maxCard;
    }

    public Card getMinFaceCardNonTrump(List<Card> cards) {
        Card minCard = cards.get(0);
        for (Card card: cards) {
            if (card.cardFace.getPriority() < minCard.cardFace.getPriority() && card.suite != trump) {
                minCard = card;
            }
        }
        return minCard;
    }

    public Card getMinCard(List<Card> cards) {
        Card minCard = cards.get(0);
        for (Card card: cards) {
            if (higherCardComparator.compare(minCard, card) < 0) {
                minCard = card;
            }
        }
        return minCard;
    }

    public Card getMaxCard(List<Card> cards) {
        Card maxCard = cards.get(0);
        for (Card card: cards) {
            if (higherCardComparator.compare(maxCard, card) > 0) {
                maxCard = card;
            }
        }
        return maxCard;
    }

    public Card getMaxCard(List<Card> cards, Suite suite) {
        Card maxCard = Card.getNullCard();
        for (Card card: cards) {
            if (card.suite == suite && higherCardComparator.compare(maxCard, card) > 0) {
                maxCard = card;
            }
        }
        return maxCard;
    }

    public Card getMinCard(List<Card> cards, Suite suite) {
        Card minCard = Card.getNullCard();
        for (Card card: cards) {
            if (card.suite == suite) {
                if (minCard.suite == Suite.Default || higherCardComparator.compare(minCard, card) < 0) {
                    minCard = card;
                }
            }
        }
        return minCard;
    }

    public List<Card> getHigherSameSuiteCards(List<Card> playedCards, Card currentCard) {
        List<Card> result = new ArrayList<>(); 
        for (Card card: playedCards) {
            if (higherCardComparator.compare(card, currentCard) < 0) result.add(card);
        }
        return result;
    }

    public boolean isHighestInGame(List<Card> playedCards, Card currentCard) {
        List<Card> higherCards = getHigherSameSuiteCards(playedCards, currentCard);
        for (CardFace face: CardFace.values()) if (face.getPriority() > currentCard.cardFace.getPriority()) {
            boolean foundCard = false;
            for (Card card: higherCards) if (card.cardFace == face) foundCard = true;
            if (foundCard == false && higherCards.size() > 0) return false;
        }
        return true;
    }
}
