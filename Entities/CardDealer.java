package Entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import Entities.Card.Card;
import Entities.Card.CardFace;
import Entities.Card.Suite;
import Entities.Player.Player;

public class CardDealer {
    public static List<Card> getDeckOfCards() {
        List<Card> deck = new ArrayList<>();
        for (Suite suite: Suite.values()) {
            if (suite == Suite.Default) continue;
            for (CardFace face: CardFace.values()) {
                if (face == CardFace.Default) continue;
                deck.add(new Card(face, suite));
            }
        }
        return deck;
    }

    public static void distributeCards(List<Player> players) {
        List<Card> deck = getDeckOfCards();
        Collections.shuffle(deck);
        int playerCount = players.size();
        int cardCount = deck.size()/playerCount;
        if (cardCount == 0) {
            throw new IllegalArgumentException("Number of players are more than number of cards.");
        }
        for (int i=0; i<playerCount; ++i) {
            List<Card> handCards = new ArrayList<>();
            for (int j=0; j<cardCount; ++j) {
                handCards.add(deck.get(i + j*playerCount));
            }
            players.get(i).setHandCards(handCards);
        }
    }

    public static Suite getTrump() {
        Random random = new Random();
        int randomIndex = 1 + random.nextInt(Suite.values().length-1);
        return Suite.values()[randomIndex];
    }
}
