package Entities.WinnerDecider;

import Entities.Round;
import Entities.Player.Player;

public interface WinnerDecider {
    public Player getWinner(Round round);
}
