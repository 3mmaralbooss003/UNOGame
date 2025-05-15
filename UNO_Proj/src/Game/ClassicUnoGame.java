package Game;

import Core.*;
import Core.CardType;
import Player.*;
import Rule.*;

import java.util.*;
public class ClassicUnoGame extends Game {
    private int currentPlayerIndex = 0;
    private boolean isClockwise = true;

    public ClassicUnoGame(Rules rule) {
        super(rule);
    }

    @Override
    protected void initializeGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of players: ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter name for player " + i + ": ");
            String name = scanner.nextLine();
            players.add(new Player(name));
        }

        // Deal 7 cards to each player
        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                player.drawCard(deck);
            }
        }

        // Put one card in the discard pile to start
        Card firstCard = null;

        while (true) {
            firstCard = deck.drawCard();
            if (firstCard == null) {
                System.out.println("Deck is empty! Unable to find a valid starting card.");
                return; // or throw an exception or restart the game
            }
            if (firstCard.getCardType() == CardType.NUMBER) {
                break;
            }
        }


        deck.addToDiscardpile(firstCard);
        System.out.println("\nStarting card: " + firstCard);
    }

    @Override
    protected void playTurn() {
        Player currentPlayer = players.get(currentPlayerIndex);
        Card topCard = deck.getTopDiscard();

        System.out.println("\nIt's " + currentPlayer.getPlayerName() + "'s turn.");
        System.out.println("Top card: " + topCard);
        System.out.println("Your hand: " + currentPlayer.getCards());

        List<Card> playableCards = currentPlayer.getPlayableCards(topCard, rule);

        if (playableCards.isEmpty()) {
            System.out.println("No playable cards. Drawing a card...");
            currentPlayer.drawCard(deck);
        } else {
            Card cardToPlay = playableCards.get(0); // simple logic: play the first available
            if (currentPlayer.playCard(cardToPlay, topCard)) {
                deck.addToDiscardpile(cardToPlay);

                // Handle action cards (very basic for now)
                switch (cardToPlay.getCardType()) {
                    case REVERSE -> isClockwise = !isClockwise;
                    case SKIP -> advanceTurn(); // skip one extra
                    case DRAW_TWO -> {
                        Player next = getNextPlayer();
                        next.drawCard(deck);
                        next.drawCard(deck);
                        System.out.println(next.getPlayerName() + " draws 2 cards!");
                    }
                    case WILD_DRAW_FOUR -> {
                        Player next = getNextPlayer();
                        next.drawCard(deck);
                        next.drawCard(deck);
                        next.drawCard(deck);
                        next.drawCard(deck);
                        System.out.println(next.getPlayerName() + " draws 4 cards!");
                    }
                }
            }
        }

        advanceTurn();
    }

    @Override
    protected boolean isGameOver() {
        for (Player player : players) {
            if (player.hasWon()) {
                System.out.println(player.getPlayerName() + " has won the game! 🏆");
                return true;
            }
        }
        return false;
    }

    private void advanceTurn() {
        if (isClockwise) {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        } else {
            currentPlayerIndex = (currentPlayerIndex - 1 + players.size()) % players.size();
        }
    }

    private Player getNextPlayer() {
        int index = isClockwise
                ? (currentPlayerIndex + 1) % players.size()
                : (currentPlayerIndex - 1 + players.size()) % players.size();
        return players.get(index);
    }
}

