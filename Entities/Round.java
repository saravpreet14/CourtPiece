package Entities;

import java.util.ArrayList;
import java.util.List;

import Entities.Card.Card;
import Entities.Card.Suite;
import Entities.Player.Player;
import Entities.WinnerDecider.WinnerDecider;
import Utilities.LoggerIO;

public class Round {
    private WinnerDecider winnerDecider;

    public List<Player> players;
    public List<Card> playedCards;
    public Player winner;
    private LoggerIO logger;

    public Object playedCard;

    public Round(List<Player> players, WinnerDecider winnerDecider) {
        this.winnerDecider = winnerDecider;
        this.players = players;
        playedCards = new ArrayList<>();
        logger = new LoggerIO();
    }

    public void start() {
        Suite currentSuite = Suite.Default;
        for (Player player: players) {
            Card playedCard = player.playTurn(this);
            logger.println(String.format("%s played %s card.", player, playedCard));
            if (currentSuite == Suite.Default) currentSuite = playedCard.suite;
            playedCards.add(playedCard);
        }
        winner = winnerDecider.getWinner(this);
        players.forEach(player -> player.roundEnd(this));
    }
}
