package Entities.Player;

import java.util.List;

import Entities.Round;
import Entities.Card.Card;
import Entities.Card.CardUtility;
import Entities.Card.Suite;
import Utilities.LoggerIO;

public class RealPlayer implements Player {
    private String name;
    private Suite trump;
    private List<Card> handCards;
    private LoggerIO logger;
    private CardUtility cardUtility;

    public RealPlayer(String name, Suite trump) {
        this.name = name;
        this.trump = trump;
        logger = new LoggerIO();
        cardUtility = new CardUtility(this.trump);
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
        if (currentRound.playedCards.isEmpty()) logger.println(String.format("Player %s's turn (choose any card to play):", name));
        else logger.println(String.format("Player %s's turn (choose card to play with suite %s):", name, currentRound.playedCards.get(0).suite));
        showHandCards();

        int input = logger.getInt();

        if (input > handCards.size()) {
            logger.println("Card does not exist, choose a card within range.");
            return playTurn(currentRound);
        }
        Card playedCard = handCards.get(input-1);
        if (!currentRound.playedCards.isEmpty() && playedCard.suite != currentRound.playedCards.get(0).suite && cardUtility.hasSuite(handCards, currentRound.playedCards.get(0).suite)) {
            logger.println(String.format("You have a card from the suite[%s], please choose within suite.", currentRound.playedCards.get(0).suite));
            return playTurn(currentRound);
        }
        handCards.remove(playedCard);
        return playedCard;
    }

    public int remainingCardCount() {
        return handCards.size();
    }

    public void roundEnd(Round round) {}

    @Override
    public String toString() {
        return name;
    }
}