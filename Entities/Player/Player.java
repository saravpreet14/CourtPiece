package Entities.Player;

import java.util.List;

import Entities.Round;
import Entities.Card.Card;

public interface Player {
    public void setHandCards(List<Card> cards);

    public void showHandCards();

    public Card playTurn(Round currentRound);

    public void roundEnd(Round round);

    public int remainingCardCount();
}
