package Entities.Card;

public enum Suite {
    Default('*'),
    Spade('\u2660'),
    Club('\u2663'),
    Heart('\u2666'),
    Diamond('\u2665');

    private final char suite;

    Suite(char suite) {
        this.suite = suite;
    }

    public char getSuite() {
        return suite;
    }

    public String toString() {
        return "" + suite;
    }
}
