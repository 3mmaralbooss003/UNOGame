package Core;
import java.util.*;

public class Deck {
    private Stack<Card> drawpile;
    private Stack<Card> discardpile;

    public Deck() {
        drawpile = new Stack<>();
        discardpile = new Stack<>();

    }
    private void createStandarDeck(){
        Color [] colors = {Color.BLUE,Color.GREEN,Color.RED,Color.YELLOW};
        // create a number for all colors
        // 0 card once
        for (Color c : colors){
            drawpile.add(new Card(c , CardType.NUMBER, 0));
            for (int i = 0; i < 10; i++){
                drawpile.add(new Card(c , CardType.NUMBER, i));
                drawpile.add(new Card(c , CardType.NUMBER, i));
        }
            for (int i = 0; i < 2; i++) {
                drawpile.add(new Card(c, CardType.SKIP, -1));
                drawpile.add(new Card(c, CardType.REVERSE, -1));
                drawpile.add(new Card(c, CardType.DRAW_TWO, -1));
            }
        }
        // Wild and Wild Draw Four — 4 each
        for (int i = 0; i < 4; i++) {
            drawpile.add(new Card(Color.BLACK, CardType.WILD, -1));
            drawpile.add(new Card(Color.BLACK, CardType.WILD_DRAW_FOUR, -1));
        }
    }
    public void shuffle() {
        Collections.shuffle(drawpile);
    }
    public Card drawCard() {
        if (drawpile.isEmpty()) {
            System.out.println("Draw pile is empty! Reshuffling discard pile...");
            if (discardpile.isEmpty()) {
                return null; // no more cards in the game
            }
            Card top = discardpile.pop();
            drawpile.addAll(discardpile);
            discardpile.clear();
            discardpile.push(top);
            shuffle();
    }
        return drawpile.pop();
    }
    public void addToDiscardpile(Card card) {
        discardpile.add(card);
    }
    public Card getTopDiscard() {
        if (discardpile.isEmpty()) {
            return null;
        }
        return discardpile.peek();
    }

}
