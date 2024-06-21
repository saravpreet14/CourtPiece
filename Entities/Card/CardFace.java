package Entities.Card;

public enum CardFace {
    Default("0", 0),
    Two("2", 2),
    Three("3", 3),
    Four("4", 4),
    Five("5", 5),
    Six("6", 6),
    Seven("7", 7),
    Eight("8", 8),
    Nine("9", 9),
    Ten("10", 10),
    Jack("J", 11),
    Queen("Q", 12),
    King("K", 13),
    Ace("A", 14);

    private final String face;
    private final int priority;

    CardFace(String face, int priority) {
        this.face = face;
        this.priority = priority;
    }

    public String getFace() {
        return face;
    }

    public int getPriority() {
        return priority;
    }
}
