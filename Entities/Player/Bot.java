package Entities.Player;

import java.util.ArrayList;
import java.util.List;

import Entities.Round;
import Entities.Card.Card;
import Entities.Card.CardUtility;
import Entities.Card.Suite;
import Utilities.LoggerIO;

public class Bot implements Player {
    private String name;
    private Suite trump;
    private List<Card> handCards;
    private List<Card> playedCards;
    private LoggerIO logger;
    private CardUtility cardUtility;

    public Bot(String name, Suite trump) {
        this.name = name;
        logger = new LoggerIO();
        playedCards = new ArrayList<>();
        cardUtility = new CardUtility(trump);
    }

    public void setHandCards(List<Card> cards) {
        this.handCards = cards;
    }

    public void showHandCards() {
        for (int i=0; i<handCards.size(); i++) {
            logger.println(i+1 + ". " + handCards.get(i));
        }
    }

    public Card playTurn(Round currentRound) {
        Card playedCard = playTurnHelper(currentRound);
        handCards.remove(playedCard);
        return playedCard;
    }

    public Card playTurnHelper(Round currentRound) {
        if (currentRound.playedCards.isEmpty()) {
            return cardUtility.getMaxFaceCard(handCards);
        }
        Suite currentSuite = currentRound.playedCards.get(0).suite;
        if (cardUtility.hasSuite(handCards, currentSuite)) {
            if (currentSuite != trump && cardUtility.hasSuite(currentRound.playedCards, trump)) {
                return cardUtility.getMinCard(handCards, currentSuite);
            }
            Card maxCard = cardUtility.getMaxCard(handCards, currentSuite);
            Card maxPlayedCard = cardUtility.getMaxCard(currentRound.playedCards, currentSuite);
            if (cardUtility.max(maxCard, maxPlayedCard) == maxCard && cardUtility.isHighestInGame(playedCards, maxCard)) return maxCard;
            return cardUtility.getMinCard(handCards, currentSuite);
        }
        else {
            if (currentSuite == trump || !cardUtility.hasSuite(handCards, trump)) {
                return cardUtility.getMinFaceCardNonTrump(handCards);
            }
            Card maxCard = cardUtility.getMaxCard(handCards, trump);
            Card maxPlayedCard = cardUtility.getMaxCard(currentRound.playedCards, currentSuite);
            if (cardUtility.max(maxCard, maxPlayedCard) == maxCard && cardUtility.isHighestInGame(playedCards, maxCard)) return maxCard;
            return cardUtility.getMinFaceCardNonTrump(handCards);
        }
    }

    public void roundEnd(Round round) {
        playedCards.addAll(round.playedCards);
    }

    public int remainingCardCount() {
        return handCards.size();
    }

    @Override
    public String toString() {
        return name;
    }
}
