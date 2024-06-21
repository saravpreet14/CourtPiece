package Entities.Card;

public class Card {
    public CardFace cardFace;
    public Suite suite;

    public Card(CardFace cardFace, Suite suite) {
        this.cardFace = cardFace;
        this.suite = suite;
    }

    @Override
    public String toString() {
        return cardFace.getFace() + suite.getSuite();
    }

    public static Card getNullCard() {
        return new Card(CardFace.Default, Suite.Default);
    }
}
