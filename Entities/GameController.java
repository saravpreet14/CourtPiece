package Entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Entities.Card.Suite;
import Entities.Player.Bot;
import Entities.Player.Player;
import Entities.Player.RealPlayer;
import Entities.WinnerDecider.HighestCardWins;
import Entities.WinnerDecider.WinnerDecider;
import Utilities.LoggerIO;

public class GameController {
    Suite trump;
    List<Player> players;
    List<Round> rounds;
    WinnerDecider winnerDecider;
    LoggerIO logger;
    Map<Player, Integer> pointsCount;

    public GameController(int playerCount, int botCount) {
        players = new ArrayList<>();
        rounds = new ArrayList<>();
        pointsCount = new HashMap<>();
        logger = new LoggerIO();
        trump = CardDealer.getTrump();
        logger.println("Trump for the game is " + trump);
        for (int i=1; i<=playerCount; ++i) {
            players.add(new RealPlayer("Player" + i, trump));
        }
        for (int i=1; i<=botCount; ++i) {
            players.add(new Bot("Bot" + i, trump));
        }
        players.forEach(player -> pointsCount.put(player, 0));
        initializeGame();
    }

    void initializeGame() {
        Collections.shuffle(players);
        CardDealer.distributeCards(players);
        winnerDecider = new HighestCardWins(trump);
    }

    public void startGame() {
        int maxPoints = 0;
        while (players.get(0).remainingCardCount() > 0) {
            Round round = new Round(players, winnerDecider);
            round.start();
            logger.println("Winner for the round is " + round.winner);
            for (int i=0; i<players.size(); i++) if(players.get(i) == round.winner) {
                Collections.rotate(players, players.size() - i);
                break;
            }
            pointsCount.put(round.winner, pointsCount.get(round.winner)+1);
            maxPoints = Math.max(maxPoints, pointsCount.get(round.winner));
        }
        List<Player> winners = new ArrayList<>();
        for (Player player: players) {
            if (pointsCount.get(player) == maxPoints) {
                winners.add(player);
            }
        }
        if (winners.size() == 1) {
            logger.println("Winner for the game is " + winners.get(0));
        }
        else {
            logger.print("It's a tie between players " + winners);
        }
    }
}
