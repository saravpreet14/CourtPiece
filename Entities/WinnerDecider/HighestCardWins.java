package Entities.WinnerDecider;

import Entities.Round;
import Entities.Card.HigherCardComparator;
import Entities.Card.Suite;
import Entities.Player.Player;

public class HighestCardWins implements WinnerDecider {
    Suite trump;
    HigherCardComparator higherCardComparator;

    public HighestCardWins(Suite trump) {
        this.trump = trump;
        higherCardComparator = new HigherCardComparator(trump);
    }

    public Player getWinner(Round round) {
        int winnerIndex = 0;
        for (int i=1; i<round.players.size(); ++i) {
            if (higherCardComparator.compare(round.playedCards.get(winnerIndex), round.playedCards.get(i)) > 0) {
                winnerIndex = i;
            }
        }
        return round.players.get(winnerIndex);
    }
}
