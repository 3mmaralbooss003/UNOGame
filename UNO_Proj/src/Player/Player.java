package Player;

import Core.*;
import Rule.Rules;

import java.util.*;

public class Player {
    private String playerName;
    private List<Card> cards;

    public Player(String playerName) {
        this.playerName = playerName;
        this.cards = new ArrayList<>() ;

    }
    public String getPlayerName() {
        return playerName;
    }
    public List<Card> getCards() {
        return cards;
    }
    public void drawCard (Deck deck) {
        Card card = deck.drawCard();
        if (card != null) {
            cards.add(card);
            System.out.println(playerName + " draws card " + card);
        }
    }
    private boolean isPlayable(Card card, Card topCard) {
        return card.getColor() == topCard.getColor() ||
                card.getCardType() == topCard.getCardType() ||
                card.getColor() == Color.BLACK; // wild cards
    }
    public boolean playCard(Card card, Card topCard) {
        if (isPlayable(card, topCard)) {
            cards.remove(card);
            System.out.println(playerName + " played: " + card);
            return true;
        }
        System.out.println("Illegal move by " + playerName + ": " + card);
        return false;
    }
    public boolean hasPlayableCard(Card topCard) {
        //.stream() turns the list into a stream (a modern way to process elements in Java using functional programming).
        return cards.stream().anyMatch(card -> isPlayable(card, topCard));
    }
    public List<Card> getPlayableCards(Card topCard, Rules rule) {
        List<Card> playableCards = new ArrayList<>() ;
        for (Card card : cards) {
            if (isPlayable(card, topCard)) {
                playableCards.add(card);
            }
        }
        return playableCards;
    }


    public boolean hasWon() {
        return cards.isEmpty();
    }

}
