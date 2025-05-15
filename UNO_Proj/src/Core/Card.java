package Core;

public class Card {
    private final Color color;
    private final CardType cardType;
    private final int Number; // must be -1 for non numbers cards

    public Card(Color color, CardType cardType, int number) {
        this.color = color;
        this.cardType = cardType;
        this.Number = number;
    }
    public Color getColor() {
        return color;
    }
    public CardType getCardType() {
        return cardType;
    }
    public int getNumber() {
        return Number;
    }
    public boolean isNumber() {
        return cardType == CardType.NUMBER;
    }
    @Override
    public String toString() {
        if (cardType == CardType.NUMBER) {
            return color + " " + Number;
        } else {
            return color + " " + cardType;
        }
    }
}
